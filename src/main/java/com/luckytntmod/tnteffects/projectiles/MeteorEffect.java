package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Direction;

public class MeteorEffect extends PrimedTNTEffect {
    private final int strength;
    private final float size;

    public MeteorEffect(int strength, float size) {
        this.strength = strength;
        this.size = size;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), strength);
        explosion.doEntityExplosion(3, true);
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), strength, (level, pos, state, distance) -> {
            if(distance <= (strength - strength / 8) && state.getBlock().getBlastResistance() <= 100) {
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            else if(Math.random() < 0.6f && state.getBlock().getBlastResistance() <= 100) {
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
                if(Math.random() < 0.25f && level.getBlockState(pos.down()).isSideSolidFullSquare(level, pos, Direction.UP)) {
                    level.setBlockState(pos, FireBlock.getState(level, pos));
                }
            }
        });
        entity.destroy();
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.EXPLOSION, entity.x(), entity.y() + size, entity.z(), 0, 0, 0);
    }

    @Override
    public float getSize(IExplosiveEntity entity) {
        return size;
    }
}
