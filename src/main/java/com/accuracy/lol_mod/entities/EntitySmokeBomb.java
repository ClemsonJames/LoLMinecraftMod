package com.accuracy.lol_mod.entities;

import com.accuracy.lol_mod.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;

public class EntitySmokeBomb extends EntityThrowable
{
    public EntitySmokeBomb(World worldIn)
    {
        super(worldIn);
    }

    public EntitySmokeBomb(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntitySmokeBomb(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            BlockPos blockpos1 = new BlockPos(this.posX-1, this.posY, this.posZ);
            BlockPos blockpos2 = new BlockPos(this.posX+1, this.posY, this.posZ);
            BlockPos blockpos3 = new BlockPos(this.posX, this.posY, this.posZ-1);
            BlockPos blockpos4 = new BlockPos(this.posX, this.posY, this.posZ+1);
            BlockPos blockpos5 = new BlockPos(this.posX-1, this.posY, this.posZ+1);
            BlockPos blockpos6 = new BlockPos(this.posX-1, this.posY, this.posZ-1);
            BlockPos blockpos7 = new BlockPos(this.posX+1, this.posY, this.posZ+1);
            BlockPos blockpos8 = new BlockPos(this.posX+1, this.posY, this.posZ-1);
            List<BlockPos> shroudPos = new ArrayList<BlockPos>();
            shroudPos.add(blockpos1);
            shroudPos.add(blockpos2);
            shroudPos.add(blockpos3);
            shroudPos.add(blockpos4);
            shroudPos.add(blockpos5);
            shroudPos.add(blockpos6);
            shroudPos.add(blockpos7);
            shroudPos.add(blockpos8);
            for (int i=0 ; i < shroudPos.size(); i++)
            {
                if (this.world.getBlockState(shroudPos.get(i)).getMaterial() == Material.AIR
                        || this.world.getBlockState(shroudPos.get(i)).getMaterial() == Material.PLANTS)
                {
                    this.world.setBlockState(shroudPos.get(i), ModBlocks.TWILIGHT_SHROUD.getDefaultState());
                }
            }
        }
    }
}
