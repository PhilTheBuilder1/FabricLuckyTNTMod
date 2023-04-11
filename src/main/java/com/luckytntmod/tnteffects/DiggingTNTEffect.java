package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DiggingTNTEffect extends PrimedTNTEffect {
    private static final float VEC_LENGTH = 60f;
    private static final float MAX_RESISTANCE = 100f;
    private final ExplosionBehavior damageCalculator = new ExplosionBehavior();

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        Set<BlockPos> blocks = new HashSet<>();
        ImprovedExplosion dummyExplosion = new ImprovedExplosion(entity.world(), entity.pos(), 4);
        for(float y = 0; y < VEC_LENGTH; y += 0.3f) {
            BlockPos pos = new BlockPos(entity.pos()).add(0, -y, 0);
            BlockState blockState = entity.world().getBlockState(pos);
            FluidState fluidState = entity.world().getFluidState(pos);
            Optional<Float> explosionResistance = damageCalculator.getBlastResistance(dummyExplosion, entity.world(), pos, blockState, fluidState);
            if(explosionResistance.isPresent() && explosionResistance.get() > MAX_RESISTANCE) {
                y += 100f;
            }
            else {
                blocks.add(pos);
            }
        }
        for(BlockPos pos : blocks) {
            entity.world().getBlockState(pos).getBlock().onDestroyedByExplosion(entity.world(), pos, dummyExplosion);
            entity.world().setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
}
