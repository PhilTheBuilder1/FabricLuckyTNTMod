package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.particle.ParticleTypes;

public class MiniMeteorEffect extends PrimedTNTEffect {
    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.EXPLOSION, entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        TNTXStrengthEffect effect = new TNTXStrengthEffect().strength(7).randomVecLength(1.25f).createsFire();
        effect.serverExplosion(entity);
        entity.destroy();
    }
}
