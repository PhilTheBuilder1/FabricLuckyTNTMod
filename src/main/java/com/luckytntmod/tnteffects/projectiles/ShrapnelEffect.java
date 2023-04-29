package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;

public class ShrapnelEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity) entity, entity.pos(), 3);
        explosion.doEntityExplosion(0.5f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1f, false, false);
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.CLOUD, true, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0, 0);
    }

    @Override
    public float getSize(IExplosiveEntity entity) {
        return 0.25f;
    }
}
