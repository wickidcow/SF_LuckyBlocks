package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.util.RegionTasks;

public final class GiantSlimeSurprise implements Surprise {

    @Override
    public String getName() {
        return "Giant Slime";
    }

    @Override
    public void activate(Random random, Player player, Location location) {
        Location spawn = location.clone();
        RegionTasks.execute(spawn, () -> {
            Slime slime = (Slime) spawn.getWorld().spawnEntity(spawn, EntityType.SLIME);
            slime.setSize(7);
            slime.setTarget(player);
        });
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
