package com.accuracy114.lolmod.items;

import com.accuracy114.lolmod.LoLMod;
import com.accuracy114.lolmod.entities.VarusArrowEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Varus extends BowItem {
    public Varus() {
        super(new Properties().maxDamage(384).group(LoLMod.TAB));
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation("pull"), Varus::getPullDurationSeconds);
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation("pulling"), Varus::isBeingPulled);
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     * use heart as ammo
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode;
            boolean hpReq = playerentity.getHealth() > 2;

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, hpReq || flag);
            if (i < 0) return;

            if (hpReq || flag) {
                float f = getArrowVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode;
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = new VarusArrowEntity(worldIn, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrowentity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                        }

                        // add piercing effect to arrow
                        abstractarrowentity.setPierceLevel((byte) 4);

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });

                        worldIn.addEntity(abstractarrowentity);
                    }

                    worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    // use heart instead of arrow
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        // takes 1 heart
                        playerentity.setHealth(playerentity.getHealth() - 2);
                        worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    /**
     * make the bow not require arrow and durability fix
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = playerIn.getHealth() > 2;

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    /** How long has the player been pulling the bow back for?
     * @return time spent pulling, in seconds
     */
    public static float getPullDurationSeconds(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity) {
        final float NO_PULL = 0.0F;
        final float TICKS_PER_SECOND = 20.0F;
        if (livingEntity == null) return NO_PULL;
        if (livingEntity.getActiveItemStack() != itemStack) return NO_PULL;

        int pullDurationTicks = itemStack.getUseDuration() - livingEntity.getItemInUseCount();   // getItemInUseCount starts from maximum!
        return pullDurationTicks / TICKS_PER_SECOND;
    }

    /** Is the bow pulled back at all?
     * @return 0.0 = not pulled,  1.0 = yes
     */
    public static float isBeingPulled(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity) {
        final float NOT_PULLED = 0.0F;
        final float IS_PULLED = 1.0F;
        if (livingEntity == null) return NOT_PULLED;
        if (livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack) return IS_PULLED;
        return NOT_PULLED;
    }
}
