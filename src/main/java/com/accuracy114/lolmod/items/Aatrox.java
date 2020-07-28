package com.accuracy114.lolmod.items;

import com.accuracy114.lolmod.LoLMod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.lang.reflect.Field;

public class Aatrox extends SwordItem {
    public Aatrox() {
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
            target.addPotionEffect(new EffectInstance(Effects.LEVITATION, 5, 5));
        }
        catch
        (Exception e) {
            e.printStackTrace();
        }
        return true;
    }



}
