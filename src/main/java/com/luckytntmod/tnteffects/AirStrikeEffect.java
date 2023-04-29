package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

public class AirStrikeEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity.getTNTFuse() <= 320 && entity.getTNTFuse() % 5 == 0) {
            for(int count = 0; count <= 5; count++) {
                LExplosiveProjectile bomb = EntityRegistry.BOMB.create(entity.level());
                bomb.setPosition(entity.pos().add(Math.random() * 100 - 50, 100 + Math.random() * 50, Math.random() * 100 - 50));
                entity.level().spawnEntity(bomb);
            }
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 360;
    }
}
