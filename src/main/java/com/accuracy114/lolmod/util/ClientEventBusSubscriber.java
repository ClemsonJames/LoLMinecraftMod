package com.accuracy114.lolmod.util;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.client.entity.render.VarusArrowRender;
import com.accuracy114.lolmod.init.ModBlocks;
import com.accuracy114.lolmod.init.ModEntityType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LoLMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // block render
        RenderTypeLookup.setRenderLayer(ModBlocks.RHAAST_ABILITY.get(), RenderType.getCutout());

        // entity render
        RenderingRegistry.registerEntityRenderingHandler(ModEntityType.VARUS_ARROW.get(), VarusArrowRender::new);

    }
}
