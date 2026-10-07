package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Conservative, opt-in legacy item-name restoration. Only identifies exact
 * LuckyBlocks enchantment signatures and old names with Han characters.
 * Leaves all other item meta, durability and enchantments intact.
 */
public final class LuckyItemRestoreCommand implements CommandExecutor {
    private record Signature(String name, Map<Enchantment, Integer> enchants) {
    }

    private static final Map<Enchantment, Integer> ARMOR = Map.of(
            Enchantment.PROTECTION, 10,
            Enchantment.PROJECTILE_PROTECTION, 10,
            Enchantment.BLAST_PROTECTION, 5,
            Enchantment.THORNS, 10,
            Enchantment.UNBREAKING, 10);

    private static final Map<Material, Signature> KNOWN = Map.of(
            Material.DIAMOND_HELMET, new Signature("Lucky Helmet", ARMOR),
            Material.DIAMOND_CHESTPLATE, new Signature("Lucky Chestplate", ARMOR),
            Material.DIAMOND_LEGGINGS, new Signature("Lucky Leggings", ARMOR),
            Material.DIAMOND_BOOTS, new Signature("Lucky Boots", ARMOR),
            Material.GOLDEN_SWORD, new Signature("Lucky Sword", Map.of(
                    Enchantment.SHARPNESS, 10, Enchantment.LOOTING, 10,
                    Enchantment.UNBREAKING, 10, Enchantment.FIRE_ASPECT, 5)),
            Material.GOLDEN_PICKAXE, new Signature("Lucky Pickaxe", Map.of(
                    Enchantment.EFFICIENCY, 10, Enchantment.FORTUNE, 10,
                    Enchantment.UNBREAKING, 10)),
            Material.GOLDEN_AXE, new Signature("Lucky Axe", Map.of(
                    Enchantment.EFFICIENCY, 10, Enchantment.FORTUNE, 10,
                    Enchantment.UNBREAKING, 10)));

    private static boolean containsChinese(String name) {
        return name.codePoints().anyMatch(cp -> Character.UnicodeScript.of(cp) == Character.UnicodeScript.HAN);
    }

    private static Signature identify(ItemStack item) {
        if (item == null || item.getType().isAir() || !item.hasItemMeta()) {
            return null;
        }
        Signature match = KNOWN.get(item.getType());
        if (match == null || !item.getEnchantments().equals(match.enchants())) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        Component display = meta.displayName();
        if (display == null
                || !containsChinese(PlainTextComponentSerializer.plainText().serialize(display))) {
            return null;
        }
        return match;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Run this command as a player.");
            return true;
        }
        if (!player.hasPermission("slimefunluckyblocks.restore")) {
            player.sendMessage("You do not have permission to restore Lucky items.");
            return true;
        }
        if (args.length != 1 || (!args[0].equalsIgnoreCase("check") && !args[0].equalsIgnoreCase("hand"))) {
            player.sendMessage("Usage: /luckyrestore check | hand");
            return true;
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        Signature match = identify(item);
        if (match == null) {
            player.sendMessage("No exact legacy Chinese-named Lucky item matched in your main hand. Nothing changed.");
            return true;
        }
        if (args[0].equalsIgnoreCase("check")) {
            player.sendMessage("Matched: " + match.name() + ". Use /luckyrestore hand to restore its English name.");
            return true;
        }
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(match.name(), NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)
                .decoration(TextDecoration.ITALIC, false));
        item.setItemMeta(meta);
        player.sendMessage("Restored English name: " + match.name() + ". Other item data was preserved.");
        return true;
    }
}
