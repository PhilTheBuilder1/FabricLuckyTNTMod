package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class EyeOfTheSaharaEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity ent) {
        for(double angle = 0; angle < 360; angle += 6D) {
            LTNTEntity tnt = EntityRegistry.TNT_X20.create(ent.level());
            tnt.setTNTFuse(160);
            tnt.setOwner(ent.owner());
            double x = ent.x() + 80 * Math.cos(angle * Math.PI / 180);
            double z = ent.z() + 80 * Math.sin(angle * Math.PI / 180);
            double y = RingTNTEffect.getFirstMotionBlockingBlock(ent.level(), x, z);
            tnt.setPos(x, y + 1D, z);
            ent.level().spawnEntity(tnt);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity ent) {
        for(double angle = 0; angle < 360; angle += 4D) {
            ent.level().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 1f), ent.x() + 2 * Math.cos(angle * Math.PI / 180), ent.y() + 0.5d, ent.z() + 2 * Math.sin(angle * Math.PI / 180), 0d, 0d, 0d);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity ent) {
        return 120;
    }
}