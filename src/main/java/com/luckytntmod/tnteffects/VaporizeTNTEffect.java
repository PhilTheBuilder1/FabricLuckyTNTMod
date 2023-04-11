package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;

public class VaporizeTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public VaporizeTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), strength, (level, pos, state, distance) -> {
            if(state.getBlock() instanceof FluidBlock || state.getBlock() instanceof KelpPlantBlock || state.getBlock() instanceof SeagrassBlock || state.getBlock() instanceof TallSeagrassBlock || state.getBlock() instanceof CoralFanBlock) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 1.3f, entity.y() + 0.5f, entity.z(), -0.1f, 0, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 1.3f, entity.y() + 0.5f, entity.z(), 0.1f, 0, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x(), entity.y() + 0.5f, entity.z() + 1.3f, 0, 0, -0.1f);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x(), entity.y() + 0.5f, entity.z() - 1.3f, 0, 0, 0.1f);
    }
}
