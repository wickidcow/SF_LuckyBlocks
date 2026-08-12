package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public final class FakeDiamondBlock implements Surprise {

    @Override
    public String getName() {
        return "Normal and Fake Diamond Block";
    }

    @Override
    public void activate(Random random, Player player, Location location) {
        Block lowerDiamond = location.getBlock().getRelative(BlockFace.UP);
        Block upperDiamond = lowerDiamond.getRelative(BlockFace.UP);

        lowerDiamond.setType(Material.DIAMOND_BLOCK);
        upperDiamond.setType(Material.DIAMOND_BLOCK);

        player.sendTitle(
                "",
                ChatColor.translateAlternateColorCodes('&', "&7&oOne is real, one is not..."),
                10,
                20,
                10);

        // Exactly one of the two visible diamond blocks is the disguised unlucky block.
        BlockStorage.store(random.nextBoolean() ? lowerDiamond : upperDiamond, "LUCKY_BLOCK_UNLUCKY");
    }

    @Override
    public LuckLevel getLuckLevel() {
        return LuckLevel.UNLUCKY;
    }
}
