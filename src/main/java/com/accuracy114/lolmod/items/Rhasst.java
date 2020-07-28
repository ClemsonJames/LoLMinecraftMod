package com.accuracy114.lolmod.items;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.blocks.RhasstAbility;
import com.accuracy114.lolmod.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Field;

public class Rhasst extends SwordItem {

    public Rhasst() {
        super(ItemTier.NETHERITE, 3, -1.4F, new Item.Properties().group(LoLMod.TAB));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(1, attacker, (p_220045_0_) -> {
            p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        // reflection to get lastdamage var
        try {
            Field lastDamage = LivingEntity.class.getDeclaredField("lastDamage");
            lastDamage.setAccessible(true);
            float damage = (Float) lastDamage.get(target);
            // heal player for 20% of damage dealt
            float healAmount = damage / 5F;
            attacker.heal(healAmount);
        }
        catch
        (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        PlayerEntity playerentity = context.getPlayer();
        if (playerentity != null) {
            if (playerentity.isSneaking()) {
                if (world.isAirBlock(blockpos.up())) {
                    if (!world.isRemote) {
                        context.getItem().damageItem(5, playerentity, (p_220045_0_) -> {
                            p_220045_0_.sendBreakAnimation(context.getHand());
                        });
                        spawnArea(world, playerentity, blockpos.up());
                    }
                    return ActionResultType.func_233537_a_(world.isRemote);
                }
            }
        }

        return ActionResultType.PASS;
    }

    // determine the spawn area for spawning block
    private void spawnArea(World world, PlayerEntity player, BlockPos blockpos) {
        switch (player.getHorizontalFacing()) {
            case NORTH:
                spawnBlock(world, player, blockpos, false);
                spawnBlock(world, player, blockpos.north(), false);
                spawnBlock(world, player, blockpos.north(2), true);
                break;
            case SOUTH:
                spawnBlock(world, player, blockpos, false);
                spawnBlock(world, player, blockpos.south(), false);
                spawnBlock(world, player, blockpos.south(2), true);
                break;
            case WEST:
                spawnBlock(world, player, blockpos, false);
                spawnBlock(world, player, blockpos.west(), false);
                spawnBlock(world, player, blockpos.west(2), true);
                break;
            case EAST:
                spawnBlock(world, player, blockpos, false);
                spawnBlock(world, player, blockpos.east(), false);
                spawnBlock(world, player, blockpos.east(2), true);
                break;
            default:
                break;

        }
    }

    // check is block is air or plant then spawn block
    private void spawnBlock(World world, PlayerEntity player, BlockPos blockpos, boolean edge) {
        if (world.getBlockState(blockpos).getMaterial() == Material.AIR
                || world.getBlockState(blockpos).getMaterial() == Material.PLANTS) {
            if (edge) {
                world.setBlockState(blockpos, ModBlocks.RHAAST_ABILITY.get().getStateContainer().getBaseState()
                        .with(RhasstAbility.getEdge(), true)
                        .with(RhasstAbility.getFacing(), player.getHorizontalFacing())
                );
            }
            else  {
                world.setBlockState(blockpos, ModBlocks.RHAAST_ABILITY.get().getDefaultState()
                        .with(RhasstAbility.getFacing(), player.getHorizontalFacing())
                );
            }
        }
    }
}
