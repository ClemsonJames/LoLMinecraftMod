package com.accuracy.lol_mod.init;

import com.accuracy.lol_mod.blocks.NoxiousTrapBlock;
import com.accuracy.lol_mod.blocks.TShroudBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block NOXIOUS_TRAP = new NoxiousTrapBlock("noxious_trap", Material.PLANTS);
    public static final Block TWILIGHT_SHROUD = new TShroudBlock("twilight_shroud", Material.GLASS);
}
