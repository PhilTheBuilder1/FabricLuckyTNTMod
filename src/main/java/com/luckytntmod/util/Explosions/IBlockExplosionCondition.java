package com.luckytntmod.util.Explosions;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@FunctionalInterface
interface IBlockExplosionCondition {

    boolean conditionMet(World level, BlockPos pos, BlockState state, double distance);

}