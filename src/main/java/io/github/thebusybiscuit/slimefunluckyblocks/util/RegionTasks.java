package io.github.thebusybiscuit.slimefunluckyblocks.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Small helpers for scheduling work on the owning Paper/Folia region.
 */
public final class RegionTasks {

    private RegionTasks() {}

    public static void execute(Location location, Runnable task) {
        Bukkit.getRegionScheduler().execute(
                JavaPlugin.getProvidingPlugin(RegionTasks.class), location, task);
    }

    public static void executeColumns(
            World world,
            int minX,
            int maxX,
            int minZ,
            int maxZ,
            BiConsumer<Integer, Integer> columnTask) {
        Map<Long, List<int[]>> columnsByChunk = new HashMap<>();

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                int chunkX = x >> 4;
                int chunkZ = z >> 4;
                long key = (((long) chunkX) << 32) ^ (chunkZ & 0xffffffffL);
                columnsByChunk.computeIfAbsent(key, ignored -> new ArrayList<>()).add(new int[] {x, z});
            }
        }

        JavaPlugin plugin = JavaPlugin.getProvidingPlugin(RegionTasks.class);
        for (List<int[]> columns : columnsByChunk.values()) {
            int chunkX = columns.get(0)[0] >> 4;
            int chunkZ = columns.get(0)[1] >> 4;

            Bukkit.getRegionScheduler().execute(plugin, world, chunkX, chunkZ, () -> {
                for (int[] column : columns) {
                    columnTask.accept(column[0], column[1]);
                }
            });
        }
    }
}
