package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

/**
 * OP-only distribution of registered Slimefun Lucky Blocks.
 * Target inventories must be accessed on their own entity scheduler.
 */
public final class LuckyGiveCommand implements CommandExecutor, TabCompleter {

    private static final String PERMISSION = "slimefunluckyblocks.give";
    private static final String USAGE = "Usage: /luckygive <player> <normal|lucky|unlucky|pandora> [amount]";
    private static final int MAX_AMOUNT = 2304;
    private static final List<String> TYPES = List.of("normal", "lucky", "unlucky", "pandora");

    private final SlimefunLuckyBlocks plugin;

    public LuckyGiveCommand(SlimefunLuckyBlocks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(PERMISSION)) {
            sender.sendMessage("[LuckyBlocks] You do not have permission to give Lucky Blocks.");
            return true;
        }

        if (args.length < 2 || args.length > 3) {
            sender.sendMessage(USAGE);
            return true;
        }

        String itemId = resolveItemId(args[1]);
        if (itemId == null) {
            sender.sendMessage("[LuckyBlocks] Invalid type. Use normal, lucky, unlucky, or pandora.");
            return true;
        }

        int amount = 1;
        if (args.length == 3) {
            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex) {
                sender.sendMessage("[LuckyBlocks] Amount must be a whole number between 1 and " + MAX_AMOUNT + ".");
                return true;
            }
        }
        if (amount < 1 || amount > MAX_AMOUNT) {
            sender.sendMessage("[LuckyBlocks] Amount must be between 1 and " + MAX_AMOUNT + ".");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("[LuckyBlocks] Player '" + args[0] + "' is not online.");
            return true;
        }

        final int requested = amount;
        final String adminName = sender.getName();
        final String targetName = target.getName();

        // Console commands and commands from another region cannot modify
        // this player's inventory directly on Folia.
        target.getScheduler().run(plugin,
                task -> giveOnTargetThread(sender, adminName, target, targetName, itemId, requested),
                () -> reply(sender, "[LuckyBlocks] " + targetName + " disconnected before delivery."));
        return true;
    }

    private void giveOnTargetThread(
            CommandSender sender, String adminName, Player target, String targetName, String itemId, int amount) {
        if (!target.isOnline()) {
            reply(sender, "[LuckyBlocks] " + targetName + " is no longer online.");
            return;
        }

        // Preserves all original Slimefun identity and item metadata.
        SlimefunItem registered = SlimefunItem.getById(itemId);
        if (registered == null || registered.getItem() == null) {
            reply(sender, "[LuckyBlocks] Item " + itemId + " is not registered. Nothing was given.");
            return;
        }

        ItemStack prototype = registered.getItem().clone();
        int remaining = amount;
        int given = 0;
        while (remaining > 0) {
            int batch = Math.min(remaining, Math.max(1, prototype.getMaxStackSize()));
            ItemStack stack = prototype.clone();
            stack.setAmount(batch);

            Map<Integer, ItemStack> leftovers = target.getInventory().addItem(stack);
            int notInserted = 0;
            for (ItemStack leftover : leftovers.values()) {
                notInserted += leftover.getAmount();
            }

            given += batch - notInserted;
            remaining -= batch;
            if (notInserted > 0) {
                // Do not drop or silently lose excess items if inventory fills.
                break;
            }
        }

        String itemName = displayName(itemId);
        int notGiven = amount - given;
        String inventoryNote = notGiven > 0 ? " (" + notGiven + " not given: inventory full)" : "";

        if (!(sender instanceof Player admin && admin.getUniqueId().equals(target.getUniqueId()))) {
            if (given > 0) {
                target.sendMessage("[LuckyBlocks] You received " + given + " x " + itemName + "." + inventoryNote);
            } else {
                target.sendMessage("[LuckyBlocks] Your inventory is full. No " + itemName + " items were added.");
            }
        }

        reply(sender, "[LuckyBlocks] Gave " + given + "/" + amount + " x " + itemName
                + " to " + targetName + "." + inventoryNote);
        plugin.getLogger().info("[AdminGive] " + adminName + " -> " + targetName
                + ": " + given + "/" + amount + " x " + itemId);
    }

    private void reply(CommandSender sender, String message) {
        if (sender instanceof Player player) {
            player.getScheduler().run(plugin, task -> player.sendMessage(message), null);
        } else {
            Bukkit.getGlobalRegionScheduler().execute(plugin, () -> sender.sendMessage(message));
        }
    }

    private static String resolveItemId(String raw) {
        return switch (raw.toLowerCase(Locale.ROOT).replace('-', '_')) {
            case "normal", "basic", "block", "lucky_block" -> "LUCKY_BLOCK";
            case "lucky", "verylucky", "very_lucky", "lucky_block_lucky" -> "LUCKY_BLOCK_LUCKY";
            case "unlucky", "veryunlucky", "very_unlucky", "lucky_block_unlucky" -> "LUCKY_BLOCK_UNLUCKY";
            case "pandora", "pandorasbox", "pandoras_box" -> "PANDORAS_BOX";
            default -> null;
        };
    }

    private static String displayName(String itemId) {
        return switch (itemId) {
            case "LUCKY_BLOCK" -> "Lucky Block";
            case "LUCKY_BLOCK_LUCKY" -> "Very Lucky Block";
            case "LUCKY_BLOCK_UNLUCKY" -> "Very Unlucky Block";
            case "PANDORAS_BOX" -> "Pandora's Box";
            default -> itemId;
        };
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(PERMISSION)) {
            return List.of();
        }

        if (args.length == 1) {
            String prefix = args[0].toLowerCase(Locale.ROOT);
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase(Locale.ROOT).startsWith(prefix))
                    .sorted()
                    .toList();
        }
        if (args.length == 2) {
            return suggestions(TYPES, args[1]);
        }
        if (args.length == 3) {
            return suggestions(List.of("1", "5", "16", "64"), args[2]);
        }
        return List.of();
    }

    private static List<String> suggestions(List<String> choices, String prefix) {
        String normalized = prefix.toLowerCase(Locale.ROOT);
        return choices.stream().filter(value -> value.startsWith(normalized)).toList();
    }
}
