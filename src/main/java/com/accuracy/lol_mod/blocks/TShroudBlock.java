package com.accuracy.lol_mod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class TShroudBlock extends BlockBase
{
    public TShroudBlock(String name, Material material)
    {
        super(name, material);
        setHardness(0.5F);
        setResistance(2.5F);
        setLightOpacity(0);
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
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
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            worldIn.destroyBlock(pos, false);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, 60);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (!worldIn.isRemote)
        {
            if (entityIn instanceof EntityLivingBase)
            {
                EntityLivingBase living = (EntityLivingBase) entityIn;
                living.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 1 * 20, 3, false, true));
                living.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1 * 20, 3, false, true));
            }
        }
    }
}
