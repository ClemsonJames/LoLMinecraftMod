package com.accuracy114.lolmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import java.util.Random;


public class RhasstAbility extends Block {

    private static final BooleanProperty EDGE = BooleanProperty.create("edge");
    private static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);


    public RhasstAbility() {
        super(Block.Properties.create(Material.PLANTS)
                .notSolid()
                .noDrops()
                .doesNotBlockMovement()
                .hardnessAndResistance(0.5f, 2.5f));
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(EDGE, false)
        );
}

    @SuppressWarnings("Deprecated")
    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @SuppressWarnings("Deprecated")
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote) {
            if (entityIn instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entityIn;
                living.addPotionEffect(new EffectInstance(Effects.LEVITATION, 20, 2));
            }
        }
    }

    @SuppressWarnings("Deprecated")
    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isRemote)
        {
            worldIn.destroyBlock(pos, false);
        }
    }

    @SuppressWarnings("Deprecated")
    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 18);
    }

    /******************************************BLOCKSTATE**************************************************************/

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(EDGE);
        builder.add(FACING);
    }

    public static BooleanProperty getEdge() {
        return EDGE;
    }

    public static DirectionProperty getFacing() {
        return FACING;
    }
}
