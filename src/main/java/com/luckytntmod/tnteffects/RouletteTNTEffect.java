package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

public class RouletteTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public RouletteTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        if(Math.random() < 0.2f) {
            ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), entity.pos(), strength);
            explosion.doEntityExplosion(1.25f, true);
            explosion.doBlockExplosion(1f, 1f, 1f, 1f, false, false);
        }
    }
}
