package com.accuracy114.lolmod.entities;

import com.accuracy114.lolmod.init.ModEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.commons.lang3.ObjectUtils;

public class VarusArrowEntity extends AbstractArrowEntity {
    public VarusArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public VarusArrowEntity(World worldIn, double x, double y, double z) {
        super(ModEntityType.VARUS_ARROW.get(), x, y, z, worldIn);
    }

    public VarusArrowEntity(World worldIn, LivingEntity shooter) {
        super(ModEntityType.VARUS_ARROW.get(), shooter, worldIn);    }

    @Override
    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (!this.world.isRemote && (this.inGround || this.getNoClip()) && this.arrowShake <= 0) {
            this.remove();
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public IPacket<?> createSpawnPacket(){

        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
