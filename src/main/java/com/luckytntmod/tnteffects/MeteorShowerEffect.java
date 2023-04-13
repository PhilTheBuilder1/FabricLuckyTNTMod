package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

public class MeteorShowerEffect extends PrimedTNTEffect {

    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity.getTNTFuse() <= 640 && entity.getTNTFuse() % 10 == 0) {
            for(int count = 0; count <= 5; count++) {
                LExplosiveProjectile meteor = EntityRegistry.MINI_METEOR.create(entity.world());
                if(meteor == null) return;
                meteor.setPosition(entity.pos().add(Math.random() * 400 - 200, 196 + Math.random() * 50, Math.random() * 400 - 200));
                entity.world().spawnEntity(meteor);
            }
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 720;
    }
}
