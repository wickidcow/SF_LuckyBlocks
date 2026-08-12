package io.github.thebusybiscuit.slimefunluckyblocks.surprises;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomItemSurprise implements Surprise {

    private final String name;
    private final LuckLevel luckLevel;
    private final List<ItemStack> items;
    private final List<String> commands;

    public CustomItemSurprise(String name, List<ItemStack> items, List<String> commands, LuckLevel luckLevel) {
        this.name = name;
        this.luckLevel = luckLevel;
        this.items = items;
        this.commands = commands;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        if (!items.isEmpty()) {
            for (ItemStack item : items) {
                l.getWorld().dropItemNaturally(l, item);
            }
        }

        if (!commands.isEmpty()) {
            // Resolve region-owned values before switching execution contexts.
            List<String> commandLines = new ArrayList<>(commands.size());
            for (String command : commands) {
                commandLines.add(applyPlaceholders(command, p, l));
            }

            // On Folia, console commands belong to the global region. Paper also
            // exposes the same scheduler API, so this is safe for all 26.2 targets.
            JavaPlugin plugin = JavaPlugin.getProvidingPlugin(CustomItemSurprise.class);
            Bukkit.getGlobalRegionScheduler().execute(plugin, () -> {
                for (String commandLine : commandLines) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandLine);
                }
            });
        }
    }

    @Override
    public LuckLevel getLuckLevel() {
        return luckLevel;
    }

    private String applyPlaceholders(String str, Player p, Location l) {
        return str.replace("{player}", p.getName())
                .replace("{world}", l.getWorld().getName())
                .replace("{x}", Integer.toString(l.getBlockX()))
                .replace("{y}", Integer.toString(l.getBlockY()))
                .replace("{z}", Integer.toString(l.getBlockZ()));
    }
}
