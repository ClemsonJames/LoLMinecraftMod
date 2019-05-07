package com.accuracy.lol_mod.init;


import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class ModEntity
{
    // register entity ava arrow
    public static EntityEntry avaArrow = EntityEntryBuilder.create()
            .entity(EntityAvaArrow.class)
            .id(new ResourceLocation(Reference.MOD_ID, "avarosas_arrow"), 0)
            .name("avarosas_arrow")
            .tracker(64, 1, true)
            .build();
}
