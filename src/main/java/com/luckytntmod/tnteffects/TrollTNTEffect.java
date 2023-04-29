package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;

public class TrollTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity)entity, entity.pos(), 4);
        explosion.doEntityExplosion(1f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1f, false, false);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 1;
    }
}
