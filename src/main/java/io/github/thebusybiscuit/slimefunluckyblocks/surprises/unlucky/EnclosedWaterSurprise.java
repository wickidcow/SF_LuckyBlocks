package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.util.RegionTasks;

public final class EnclosedWaterSurprise implements Surprise {

    @Override
    public String getName() {
        return "Enclosed Water Pool";
    }

    @Override
    public void activate(Random random, Player player, Location location) {
        Location origin = player.getLocation();
        World world = origin.getWorld();

        int centerX = origin.getBlockX();
        int centerY = origin.getBlockY();
        int centerZ = origin.getBlockZ();
        int floorY = Math.max(centerY - 1, world.getMinHeight());
        int roofY = Math.min(centerY + 2, world.getMaxHeight() - 1);

        RegionTasks.executeColumns(
                world,
                centerX - 1,
                centerX + 1,
                centerZ - 1,
                centerZ + 1,
                (x, z) -> {
                    for (int y = floorY; y <= roofY; y++) {
                        Material type;
                        if (y == floorY || y == roofY) {
                            type = Material.OBSIDIAN;
                        } else if (x == centerX && z == centerZ) {
                            type = Material.WATER;
                        } else {
                            type = Material.OBSIDIAN;
                        }

                        world.getBlockAt(x, y, z).setType(type, false);
                    }
                });
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
