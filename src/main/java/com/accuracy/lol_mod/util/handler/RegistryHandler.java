package com.accuracy.lol_mod.util.handler;

import com.accuracy.lol_mod.init.ModBlocks;
import com.accuracy.lol_mod.init.ModEntity;
import com.accuracy.lol_mod.init.ModItems;
import com.accuracy.lol_mod.items.ModBow.AvarosasBow;
import com.accuracy.lol_mod.util.Model;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ModItems.ITEMS)
        {
            if (item instanceof Model)
            {
                ((Model)item).registerModels();
            }
        }

        for(Block block : ModBlocks.BLOCKS)
        {
            if (block instanceof Model)
            {
                ((Model)block).registerModels();
            }
        }
    }

    //zoom for Avarosa's bow
    @SubscribeEvent
    public void zoom(FOVUpdateEvent event )
    {
        if(event.getEntity().getActiveItemStack() != null)
            if(event.getEntity().getActiveItemStack().getItem() == ModItems.AVAROSAS_BOW)
            {
                event.setNewfov(event.getFov()*(((AvarosasBow)event.getEntity().getActiveItemStack().getItem()).getZoom(event.getEntity())));

            }
    }

    // pre init for main
    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
        RenderHandler.registerEntityRenders();
    }


}
