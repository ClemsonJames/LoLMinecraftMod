package com.accuracy.lol_mod.items.ModArrow;

import com.accuracy.lol_mod.Main;
import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.init.ModItems;
import com.accuracy.lol_mod.items.ItemBase;
import com.accuracy.lol_mod.util.Model;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AvaArrow extends Item implements Model
{
    public AvaArrow(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        maxStackSize = 64;
        setCreativeTab(CreativeTabs.COMBAT);

        ModItems.ITEMS.add(this);
    }

    // arrow entity
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntityAvaArrow entityAvaArrow = new EntityAvaArrow(worldIn, shooter);
        return entityAvaArrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant <= 0 ? false : this.getClass() == AvaArrow.class;
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
