package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.util.RegionTasks;

public final class VoidHoleSurprise implements Surprise {

    @Override
    public String getName() {
        return "Void Hole";
    }

    @Override
    public void activate(Random random, Player player, Location location) {
        Location origin = player.getLocation();
        World world = origin.getWorld();

        int centerX = origin.getBlockX();
        int centerZ = origin.getBlockZ();
        int topY = Math.min(origin.getBlockY() + 1, world.getMaxHeight() - 1);
        int minY = world.getMinHeight();

        RegionTasks.executeColumns(
                world,
                centerX - 1,
                centerX + 1,
                centerZ - 1,
                centerZ + 1,
                (x, z) -> {
                    for (int y = topY; y >= minY; y--) {
                        world.getBlockAt(x, y, z).setType(Material.AIR, false);
                    }
                });
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
