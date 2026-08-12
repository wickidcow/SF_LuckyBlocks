package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.util.RegionTasks;

public final class AnvilRainSurprise implements Surprise {

    @Override
    public String getName() {
        return "Anvil Rain";
    }

    @Override
    public void activate(Random random, Player player, Location location) {
        Location origin = player.getLocation();
        World world = origin.getWorld();

        int centerX = origin.getBlockX();
        int centerY = origin.getBlockY();
        int centerZ = origin.getBlockZ();
        int floorY = Math.max(centerY - 1, world.getMinHeight());
        int ceilingY = Math.min(centerY + 2, world.getMaxHeight() - 1);

        RegionTasks.executeColumns(
                world,
                centerX - 1,
                centerX + 1,
                centerZ - 1,
                centerZ + 1,
                (x, z) -> {
                    for (int y = floorY; y <= ceilingY; y++) {
                        if (y == floorY) {
                            world.getBlockAt(x, y, z).setType(Material.OBSIDIAN, false);
                        } else if (x != centerX || z != centerZ) {
                            world.getBlockAt(x, y, z).setType(Material.IRON_BARS, false);
                        }
                    }
                });

        Location firstAnvil = new Location(
                world,
                centerX + 0.5D,
                Math.min(centerY + 16, world.getMaxHeight() - 1),
                centerZ + 0.5D);
        Location secondAnvil = new Location(
                world,
                centerX + 0.5D,
                Math.min(centerY + 24, world.getMaxHeight() - 1),
                centerZ + 0.5D);

        RegionTasks.execute(firstAnvil, () -> world.spawnFallingBlock(firstAnvil, Material.ANVIL.createBlockData()));
        RegionTasks.execute(secondAnvil, () -> world.spawnFallingBlock(secondAnvil, Material.ANVIL.createBlockData()));
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
