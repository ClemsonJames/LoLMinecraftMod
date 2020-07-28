package com.accuracy114.lolmod.init;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.entities.VarusArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, LoLMod.MOD_ID);

    // Entity Types
    public static final RegistryObject<EntityType<VarusArrowEntity>> VARUS_ARROW = ENTITY_TYPES.register("varus_arrow",
            () -> EntityType.Builder.<VarusArrowEntity>create(VarusArrowEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F)
                    .build(new ResourceLocation(LoLMod.MOD_ID, "varus_arrow").toString()));
}