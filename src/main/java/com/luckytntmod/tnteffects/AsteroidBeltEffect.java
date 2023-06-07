package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.particle.ParticleTypes;

public class AsteroidBeltEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity ent) {
        for(double angle = 0; angle < 360; angle += 12) {
            LTNTEntity tnt = EntityRegistry.GLOBAL_DISASTER.create(ent.level());
            tnt.setTNTFuse(100);
            tnt.setOwner(ent.owner());
            double x = ent.x() + 160 * Math.cos(angle * Math.PI / 180);
            double z = ent.z() + 160 * Math.sin(angle * Math.PI / 180);
            double y = RingTNTEffect.getFirstMotionBlockingBlock(ent.level(), x, z);
            tnt.setPosition(x, y + 1D, z);
            ent.level().spawnEntity(tnt);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity ent) {
        for(double angle = 0; angle < 360; angle += 4D) {
            ent.level().addParticle(ParticleTypes.FLAME, ent.x() + 2 * Math.cos(angle * Math.PI / 180), ent.y() + 0.5d, ent.z() + 2 * Math.sin(angle * Math.PI / 180), 0d, 0d, 0d);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity ent) {
        return 180;
    }
}
