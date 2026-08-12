package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public final class WorldGenerator implements Listener {

    private final SlimefunLuckyBlocks plugin;
    private final List<String> blacklist;
    private final int chance;

    public WorldGenerator(SlimefunLuckyBlocks plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        blacklist = List.copyOf(plugin.getCfg().getStringList("world-blacklist"));

        int configuredChance = plugin.getCfg().getInt("chance");
        chance = Math.max(0, Math.min(100, configuredChance));
        if (configuredChance != chance) {
            plugin.getLogger().warning(
                    "Lucky Block generation chance must be between 0 and 100; using " + chance + " instead of "
                            + configuredChance + '.');
        }
    }

    @EventHandler
    public void onRandomSpawn(ChunkPopulateEvent event) {
        World world = event.getWorld();
        if (chance <= 0 || blacklist.contains(world.getName())) {
            return;
        }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextInt(100) >= chance) {
            return;
        }

        int chunkX = event.getChunk().getX();
        int chunkZ = event.getChunk().getZ();
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);

        // ChunkPopulateEvent is only used as the trigger. The actual world access is
        // moved onto the region that owns this chunk so the same code is safe on Folia.
        Bukkit.getRegionScheduler().execute(plugin, world, chunkX, chunkZ, () -> spawnOnSurface(world, x, z));
    }

    private void spawnOnSurface(World world, int x, int z) {
        Block surface = world.getHighestBlockAt(x, z);
        if (!surface.getType().isSolid() || surface.getY() + 1 >= world.getMaxHeight()) {
            return;
        }

        Block target = surface.getRelative(BlockFace.UP);
        if (target.isEmpty()) {
            plugin.spawnLuckyBlock(target);
        }
    }
}
