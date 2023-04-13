package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;

public class VillageDefenseEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count <= 15; count++) {
            IronGolemEntity golem = new IronGolemEntity(EntityType.IRON_GOLEM, entity.world());
            golem.setPosition(entity.pos());
            entity.world().spawnEntity(golem);
        }
    }
}
