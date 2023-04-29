package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class BouncingTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), entity.pos(), 10f);
        explosion.doEntityExplosion(1.25f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
    }

    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(((Entity)entity).isOnGround()) {
            entity.getPersistentData().putInt("bounces", entity.getPersistentData().getInt("bounces") + 1);
            ((Entity) entity).setVelocity(Math.random() - Math.random(), Math.random() * 1.5f, Math.random() - Math.random());
            entity.level().playSound(null, entity.x(), entity.y(), entity.z(), SoundEvents.ENTITY_SLIME_JUMP, SoundCategory.MASTER, 1, 1);
        }
        if((entity.getPersistentData().getInt("bounces") >= 12 && entity.level() instanceof ServerWorld)) {
            serverExplosion(entity);
            entity.destroy();
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 100000;
    }
}
