package com.accuracy.lol_mod.util.handler;

import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.entities.EntitySmokeBomb;
import com.accuracy.lol_mod.entities.render.RenderAvaArrow;
import com.accuracy.lol_mod.entities.render.RenderSmokeBomb;
import com.accuracy.lol_mod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        //render handler for ava arrow entity
        RenderingRegistry.registerEntityRenderingHandler(EntityAvaArrow.class, renderManager ->
                new RenderAvaArrow(renderManager));

        //render handler for smoke bomb entity
        RenderingRegistry.registerEntityRenderingHandler(EntitySmokeBomb.class, renderManager ->
                new RenderSmokeBomb(renderManager,ModItems.SMOKE_BOMB, Minecraft.getMinecraft().getRenderItem()));
    }
}
