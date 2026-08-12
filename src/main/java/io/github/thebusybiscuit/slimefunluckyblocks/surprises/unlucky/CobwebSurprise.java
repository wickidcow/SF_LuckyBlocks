package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.util.RegionTasks;

public final class CobwebSurprise implements Surprise {

    @Override
    public String getName() {
        return "Cobwebs";
    }

    @Override
    public void activate(Random random, Player player, Location location) {
        Location feet = player.getLocation().getBlock().getLocation();
        Location eyes = player.getEyeLocation().getBlock().getLocation();

        RegionTasks.execute(feet, () -> feet.getBlock().setType(Material.COBWEB));
        RegionTasks.execute(eyes, () -> eyes.getBlock().setType(Material.COBWEB));
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
