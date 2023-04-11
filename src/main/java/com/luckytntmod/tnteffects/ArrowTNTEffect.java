package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;

public class ArrowTNTEffect extends PrimedTNTEffect {
    private final int arrowCount;

    public ArrowTNTEffect(int arrowCount) {
        this.arrowCount = arrowCount;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count < arrowCount; count++) {
            ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, entity.world());
            arrow.setPos(entity.x(), entity.y() + (entity instanceof LTNTEntity ? 0.5f : 0f), entity.z());
            arrow.setVelocity(Math.random() * 3 - Math.random() * 3, Math.random() * 2 - Math.random(), Math.random() * 3 - Math.random() * 3);
            arrow.setDamage(16);
            entity.world().spawnEntity(arrow);
        }
    }
}