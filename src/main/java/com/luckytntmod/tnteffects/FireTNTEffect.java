package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.IForEachBlockExplosionEffect;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public FireTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doTopBlockExplosionForAll(entity.world(), entity.pos(), strength, new IForEachBlockExplosionEffect() {

            @Override
            public void doBlockExplosion(World level, BlockPos pos, BlockState state, double distance) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.setBlockState(pos, FireBlock.getState(level, pos));
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0.2f, 0);
        entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0.05f, 0.15f, 0.05f);
        entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), -0.05f, 0.15f, -0.05f);
        entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), -0.05f, 0.15f, 0.05f);
        entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0.05f, 0.15f, -0.05f);
    }
}
