package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import org.joml.Vector3f;

public class SmokeTNTEffect extends PrimedTNTEffect {
    /*@Override
    public void explosionTick(IExplosiveEntity entity) {
        spawnParticles(entity);
        if(entity.getTNTFuse() < 460 && entity.world() instanceof ServerWorld sLevel) {
            sLevel.spawnParticles(new DustParticleEffect(new Vector3f(((Entity)entity).getPersistentData().getFloat("r"), ((Entity)entity).getPersistentData().getFloat("g"), ((Entity)entity).getPersistentData().getFloat("b")), 10f), entity.x(), entity.y(), entity.z(), 30, 2.5f, 2.5f, 2.5f, 0);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        if(entity.level() instanceof ServerLevel sLevel) {
            sLevel.sendParticles(new DustParticleOptions(new Vector3f(((Entity)entity).getPersistentData().getFloat("r"), ((Entity)entity).getPersistentData().getFloat("g"), ((Entity)entity).getPersistentData().getFloat("b")), 1f), entity.x(), entity.y() + 1f, entity.z(), 1, 0, 0, 0, 0);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 520;
    }*/
}
