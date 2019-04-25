package com.accuracy.lol_mod.entities;

import com.accuracy.lol_mod.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

public class EntityAvaArrow extends EntityArrow
{
    public EntityAvaArrow(World worldIn)
    {
        super(worldIn);
    }

    public EntityAvaArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityAvaArrow(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(ModItems.AVA_ARROW);
    }

    protected void arrowHit(EntityLivingBase living)
    {
        super.arrowHit(living);

        Random rand = new Random();
        int chance = rand.nextInt(2);
        // 1/60 chance to get super slowness
        if (chance == 0)
        {
            PotionEffect potioneffect = new PotionEffect(MobEffects.SLOWNESS, 4*20, 99, false, true);
            living.addPotionEffect(new PotionEffect(potioneffect.getPotion(), potioneffect.getDuration(), potioneffect.getAmplifier(), potioneffect.getIsAmbient(), potioneffect.doesShowParticles()));
        }
        else
        {
            PotionEffect potioneffect = new PotionEffect(MobEffects.SLOWNESS,2*20, 1, false, true);
            living.addPotionEffect(new PotionEffect(potioneffect.getPotion(), potioneffect.getDuration(), potioneffect.getAmplifier(), potioneffect.getIsAmbient(), potioneffect.doesShowParticles()));
        }
    }
}
