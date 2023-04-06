package com.luckytntmod.util.ExplosionBehavior;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class ExtendedBehavior extends ExplosionBehavior {
    boolean destroysBlocks;
    public ExtendedBehavior(boolean destroysBlocks) {
        this.destroysBlocks = destroysBlocks;
    }

    @Override
    public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
        return destroysBlocks;
    }
}
