package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import org.joml.Vector3f;

public class NightTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        if(entity.world() instanceof ServerWorld sLevel) {
            sLevel.setTimeOfDay(18000);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 1f), entity.x(), entity.y() + 1f, entity.z(), 0, -0.1f, 0);
    }
}
