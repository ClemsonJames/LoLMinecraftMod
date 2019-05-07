package com.accuracy.lol_mod.util.handler;

import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.entities.render.RenderAvaArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        //render handler for ava arrow entity
        RenderingRegistry.registerEntityRenderingHandler(EntityAvaArrow.class, new IRenderFactory<EntityAvaArrow>()
        {
            @Override
            public Render<? super EntityAvaArrow> createRenderFor(RenderManager manager)
            {
                return new RenderAvaArrow(manager);
            }
        });
    }
}
