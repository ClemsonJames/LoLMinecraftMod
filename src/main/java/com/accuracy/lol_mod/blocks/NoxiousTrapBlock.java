package com.accuracy.lol_mod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class NoxiousTrapBlock extends BlockBase
{
    public NoxiousTrapBlock(String name, Material material)
    {
        super(name, material);
        setSoundType(SoundType.PLANT);
        setHardness(0.5F);
        setResistance(2.5F);
        setLightOpacity(0);

    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entityIn;
            living.addPotionEffect(new PotionEffect(MobEffects.POISON, 4 * 20, 3, false, true));
            living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 4 * 20, 3, false, true));
            living.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 4 * 20, 3, false, true));
            worldIn.destroyBlock(pos, false);
        }
    }

}
