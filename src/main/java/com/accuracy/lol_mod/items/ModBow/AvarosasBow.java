package com.accuracy.lol_mod.items.ModBow;

import com.accuracy.lol_mod.Main;
import com.accuracy.lol_mod.init.ModItems;
import com.accuracy.lol_mod.items.ModProj.AvaArrow;
import com.accuracy.lol_mod.util.Model;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import javax.annotation.Nullable;

public class AvarosasBow extends ItemBow implements Model
{
    public AvarosasBow(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        maxStackSize = 1;
        setMaxDamage(500);
        setCreativeTab(CreativeTabs.COMBAT);
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    return entityIn.getActiveItemStack().getItem() != ModItems.AVAROSAS_BOW ? 0.0F : (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });

        addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });

        ModItems.ITEMS.add(this);
    }

    // change arrow type
    @Override
    protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() instanceof AvaArrow;
    }

    private ItemStack findAmmo(EntityPlayer player)
    {

        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack)) {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    public float getZoom(EntityLivingBase entity)
    {
        return 1 - MathHelper.clamp(this.getMaxItemUseDuration(null) - entity.getItemInUseCount(), 0, 20) / 60;
    }

    //charge: ticks that right click has been held down
    public static float getArrowVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }

    // Edit this to change damage, velocity, charge time, and etc
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;//flag is true if no arrows are to be consumed
            ItemStack itemstack = this.findAmmo(entityplayer);//looks for ammo (see above)

            int i = this.getMaxItemUseDuration(stack) - timeLeft;//the time that it has been used for
            i = ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);

                if ((double)f >= 0.1D)//if the velocity is lower than this, it does not fire
                {
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

                    if (!worldIn.isRemote)
                    {
                        AvaArrow itemarrow = (AvaArrow)(itemstack.getItem() instanceof AvaArrow ? itemstack.getItem() : ModItems.AVA_ARROW);
                        EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);//creates the arrow entity
                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F/*velocity*/, 1.0F/*inaccuracy*/);

                        if (f == 1.0F)
                        {
                            entityarrow.setIsCritical(true);//arrow entity does more damage
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);//adds damage to arrow entity
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityarrow.setKnockbackStrength(k);//adds knockback
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityarrow.setFire(100);//adds fire
                        }

                        stack.damageItem(1, entityplayer);//damages bow

                        if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                        {
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;//prevents survival players from picking up the arrow
                        }

                        worldIn.spawnEntity(entityarrow);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);//plays the shooting sound

                    if (!flag1 && !entityplayer.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);//removes the arrow

                        if (itemstack.isEmpty())
                        {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.getObjectUseStats(this));//adds stat
                }
            }
        }
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
