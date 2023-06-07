package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Material;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class GlobalDisasterEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.level(), entity.pos(), 50, (level, pos, state, distance) -> {
            if(state.getBlock().getBlastResistance() < 200 && state.getMaterial() != Material.AIR) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.removeBlock(pos, false);
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity ent) {
        for(double angle = 0; angle < 360; angle += 6D) {
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0.2f, 0.2f, 0.2f), 0.75f), ent.x() + 2 * Math.cos(angle * Math.PI / 180), ent.y() + 0.5f, ent.z() + 2 * Math.sin(angle * Math.PI / 180), 0, 0, 0);
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0.2f, 0.2f, 0.2f), 0.75f), ent.x() + 2 * Math.cos(angle * Math.PI / 180), ent.y() + 0.5f + 2 * Math.sin(angle * Math.PI / 180), ent.z(), 0, 0, 0);
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0.2f, 0.2f, 0.2f), 0.75f), ent.x(), ent.y() + 0.5f + 2 * Math.cos(angle * Math.PI / 180), ent.z() + 2 * Math.sin(angle * Math.PI / 180), 0, 0, 0);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 240;
    }
}
