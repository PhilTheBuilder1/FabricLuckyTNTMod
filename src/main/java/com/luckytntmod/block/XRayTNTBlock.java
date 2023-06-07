package com.luckytntmod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class XRayTNTBlock extends LTNTBlock {
    public XRayTNTBlock(Settings settings, int index, String registryName) {
        super(settings, index, registryName);
    }
    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0f;
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    /*
    @SuppressWarnings("deprecation")
    @Override
    public boolean isSideInvisible(BlockState state, BlockState state2, Direction direction) {
        return state2.isOf(this) || super.isSideInvisible(state, state2, direction);
    }
     */

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }
}
