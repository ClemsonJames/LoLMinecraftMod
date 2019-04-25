package com.accuracy.lol_mod.entities.render;

import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.util.Reference;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAvaArrow extends RenderArrow<EntityAvaArrow>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID +
            ":textures/entity/ava_arrow_entity.png");

    public RenderAvaArrow(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityAvaArrow entity)
    {
        return TEXTURE;
    }
}
