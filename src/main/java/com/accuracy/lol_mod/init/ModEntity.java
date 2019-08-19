package com.accuracy.lol_mod.init;


import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.entities.EntitySmokeBomb;
import com.accuracy.lol_mod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class ModEntity
{
    @Mod.EventBusSubscriber
    public static class RegistrationHandler
    {
        private static int entityID = 0;

        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityEntry> event)
        {
            final EntityEntry[] entries = {
                    EntityEntryBuilder.create()
                            .name("avarosas_arrow")
                            .entity(EntityAvaArrow.class)
                            .id(new ResourceLocation(Reference.MOD_ID, "avarosas_arrow"), entityID++)
                            .tracker(64, 1, true)
                            .build(),

                    EntityEntryBuilder.create()
                            .name("smoke_bomb")
                            .entity(EntitySmokeBomb.class)
                            .id(new ResourceLocation(Reference.MOD_ID, "smoke_bomb"), entityID++)
                            .tracker(64, 1, true)
                            .build(),

            };

            event.getRegistry().registerAll(entries);
        }
    }
}
