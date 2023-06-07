package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

public class MeteorTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        LExplosiveProjectile meteor = EntityRegistry.METEOR.create(entity.world());
        if(meteor == null) return;
        meteor.setPos(entity.x(), entity.y() + 128, entity.z());
        meteor.setOwner(entity.owner());
        entity.world().spawnEntity(meteor);
    }

    @Override
    public boolean playsSound() {
        return false;
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 0;
    }
}
