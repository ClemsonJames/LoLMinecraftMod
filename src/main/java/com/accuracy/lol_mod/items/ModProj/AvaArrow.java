package com.accuracy.lol_mod.items.ModProj;

import com.accuracy.lol_mod.entities.EntityAvaArrow;
import com.accuracy.lol_mod.items.ItemBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AvaArrow extends ItemBase
{
    public AvaArrow(String name)
    {
        super(name);
        maxStackSize = 64;
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

}
