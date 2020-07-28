package com.accuracy114.lolmod.init;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.items.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LoLMod.MOD_ID);

    // material items
    public static final RegistryObject<Item> DARKIN_SOUL = ITEMS.register("darkin_soul",
            () -> new Item(new Item.Properties().group(LoLMod.TAB)));

    // weapons
    public static final RegistryObject<SwordItem> AATROX = ITEMS.register("aatrox", Aatrox::new);
    public static final RegistryObject<SwordItem> RHASST = ITEMS.register("rhasst", Rhasst::new);
    public static final RegistryObject<BowItem> VARUS = ITEMS.register("varus", Varus::new);
}
