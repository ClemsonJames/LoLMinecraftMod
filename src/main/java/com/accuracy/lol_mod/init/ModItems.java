package com.accuracy.lol_mod.init;

import com.accuracy.lol_mod.items.ItemBase;
import com.accuracy.lol_mod.items.ModArrow.AvaArrow;
import com.accuracy.lol_mod.items.ModBow.AvarosasBow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;

import java.util.ArrayList;
import java.util.List;

public class ModItems
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //items
    public static final Item ICE_CRYSTAL = new ItemBase("ice_crystal");

    //bows
    public static final ItemBow AVAROSAS_BOW = new AvarosasBow("avarosas_bow");

    //arrows
    public static final Item AVA_ARROW = new AvaArrow("avarosas_arrow");
}
