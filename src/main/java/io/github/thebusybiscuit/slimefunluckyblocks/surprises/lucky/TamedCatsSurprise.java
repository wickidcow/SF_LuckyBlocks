package io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;

public final class TamedCatsSurprise implements Surprise {

    private static final List<Cat.Type> CAT_TYPES = RegistryAccess.registryAccess()
            .getRegistry(RegistryKey.CAT_VARIANT)
            .stream()
            .toList();
	
	@Override
	public String getName() {
		return "Tamed Cats";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		for (int i = 0; i < 8; i++) {
			Cat cat = (Cat) l.getWorld().spawnEntity(l.add(random.nextInt(4) - (double) random.nextInt(8), 1, random.nextInt(4) - (double) random.nextInt(8)), EntityType.CAT);
			cat.setAdult();
			cat.setOwner(p);
			cat.setCatType(CAT_TYPES.get(random.nextInt(CAT_TYPES.size())));
		}
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.LUCKY;
	}

}
