package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class EnderTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public EnderTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity) entity, entity.pos(), strength * 1.5f);
        explosion.doEntityExplosion(strength, false);
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.6f, 0f, 0.9f), 1f), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }
}
