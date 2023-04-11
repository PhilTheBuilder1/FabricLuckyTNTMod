package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class TimerTNTEffect extends PrimedTNTEffect {
    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        float r = entity.getTNTFuse() < 400 ? 1f : 2f - 0.0025f * entity.getTNTFuse();
        float g = entity.getTNTFuse() >= 400 ? 1f : 0.0025f * entity.getTNTFuse();
        entity.world().addParticle(new DustParticleEffect(new Vector3f(r, g, 0), 1f), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 800;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        TNTXStrengthEffect effect = new TNTXStrengthEffect().strength(10f).randomVecLength(1.25f).knockbackStrength(1.5f);
        effect.serverExplosion(entity);
    }
}
