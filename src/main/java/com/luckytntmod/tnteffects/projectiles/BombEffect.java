package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.particle.ParticleTypes;

public class BombEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), entity.pos(), 12f);
        explosion.doEntityExplosion(1.25f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1f, false, false);
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.level().addParticle(ParticleTypes.CLOUD, true, entity.x(), entity.y(), entity.z(), 0f, 0f, 0f);
    }
}