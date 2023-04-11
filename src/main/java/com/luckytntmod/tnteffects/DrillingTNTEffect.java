package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DrillingTNTEffect extends PrimedTNTEffect {
    private static final float VEC_LENGTH = 100f;
    private static final float MAX_RESISTANCE = 100f;
    private final ExplosionBehavior damageCalculator = new ExplosionBehavior();

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        Set<BlockPos> blocks = new HashSet<>();
        ImprovedExplosion dummyExplosion = new ImprovedExplosion(entity.world(), entity.pos(), 4);
        for(int x = -3; x <= 3; x++) {
            for(int z = -3; z <= 3; z++) {
                double distance = Math.sqrt(x * x + z * z);
                if(distance <= 3) {
                    for(float y = 0; y < VEC_LENGTH; y += 0.3f) {
                        BlockPos pos = new BlockPos(entity.pos()).add(x, -y, z);
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
                }
            }
        }
        for(BlockPos pos : blocks) {
            entity.world().getBlockState(pos).getBlock().onDestroyedByExplosion(entity.world(), pos, dummyExplosion);
            entity.world().setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x(), entity.y() + 1.6f, entity.z(), 0, -0.1f, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 0.4f, entity.y() + 1.4f, entity.z() + 0.4f, 0, -0.1f, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 0.4f, entity.y() + 1.4f, entity.z() - 0.4f, 0, -0.1f, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 0.4f, entity.y() + 1.4f, entity.z() + 0.4f, 0, -0.1f, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 0.4f, entity.y() + 1.4f, entity.z() - 0.4f, 0, -0.1f, 0);
    }
}
