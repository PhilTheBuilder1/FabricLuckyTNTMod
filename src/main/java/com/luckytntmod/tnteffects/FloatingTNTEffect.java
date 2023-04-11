package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.entity.Entity;

public class FloatingTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        Entity ent = (Entity) entity;
        ent.setVelocity(ent.getVelocity().x, 0.15f, ent.getVelocity().z);
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        TNTXStrengthEffect effect = new TNTXStrengthEffect().strength(50f).yStrength(1.3f).knockbackStrength(3f);
        effect.serverExplosion(entity);
    }
}