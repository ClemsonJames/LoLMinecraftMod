package com.accuracy114.lolmod.client.entity.render;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.entities.VarusArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class VarusArrowRender extends ArrowRenderer<VarusArrowEntity> {
    public VarusArrowRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(VarusArrowEntity entity) {
        return new ResourceLocation(LoLMod.MOD_ID, "textures/entity/varus_arrow.png");
    }
}
