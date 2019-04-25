package com.accuracy.lol_mod;

import com.accuracy.lol_mod.proxy.CommonProxy;
import com.accuracy.lol_mod.util.Reference;
import com.accuracy.lol_mod.util.handler.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class Main {

    @Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {

    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {

    }
}
