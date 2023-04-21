package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.entity.Entity;

public class StaticTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity.getTNTFuse() == 79) {
            ((Entity) entity).setNoGravity(true);
            entity.getPersistentData().putDouble("x", (int) entity.x()+0.5);
            entity.getPersistentData().putDouble("y", (int) entity.y());
            entity.getPersistentData().putDouble("z", (int) entity.z()+0.5);
        }
        if(entity.getTNTFuse() < 79)
            ((Entity) entity).setPos(entity.getPersistentData().getDouble("x"), entity.getPersistentData().getDouble("y"), entity.getPersistentData().getDouble("z"));
        ((Entity) entity).setVelocity(0, 0, 0);
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        new TNTXStrengthEffect().serverExplosion(entity);
    }
}
