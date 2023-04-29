package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;

public class ClusterBombEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity)entity, entity.pos(), 10);
        explosion.doEntityExplosion(1f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
        for(int count = 0; count < 80; count++) {
            LExplosiveProjectile shrapnel = EntityRegistry.SHRAPNEL.create(entity.world());
            if(shrapnel == null) return;
            shrapnel.setPosition(entity.pos());
            shrapnel.setVelocity(Math.random() - 0.5f, Math.random() * 1.5f, Math.random() - 0.5f);
            entity.world().spawnEntity(shrapnel);
        }
    }
}
