package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class TNTRainSurprise implements Surprise {

    private static final Vector[] VELOCITIES = {
        new Vector(0, 0.5, 0.15),
        new Vector(0.15, 0.5, 0.15),
        new Vector(0.15, 0.5, 0),
        new Vector(0, 0.5, -0.15),
        new Vector(-0.15, 0.5, -0.15),
        new Vector(-0.15, 0.5, 0),
        new Vector(-0.15, 0.5, 0.15),
        new Vector(0.15, 0.5, -0.15)
    };

    @Override
    public String getName() {
        return "TNT Rain";
    }

    @Override
    public void activate(Random random, Player p, Location l) {
        Location spawnOrigin = l.clone().add(0, 2, 0);

        for (Vector velocity : VELOCITIES) {
            l.getWorld().spawnEntity(spawnOrigin.clone(), EntityType.TNT).setVelocity(velocity.clone());
        }
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
