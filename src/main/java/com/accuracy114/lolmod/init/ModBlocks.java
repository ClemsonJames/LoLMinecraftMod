package com.accuracy114.lolmod.init;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.blocks.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LoLMod.MOD_ID);

    // Blocks
    public static final RegistryObject<Block> RHAAST_ABILITY = BLOCKS.register("rhasst_ability", RhasstAbility::new);
}
