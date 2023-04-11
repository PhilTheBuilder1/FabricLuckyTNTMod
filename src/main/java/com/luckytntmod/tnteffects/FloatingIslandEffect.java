package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

public class FloatingIslandEffect extends PrimedTNTEffect {
    private final int strength;

    public FloatingIslandEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), strength, (level, pos, state, distance) -> {
            if(distance <= 20 && Math.abs(pos.getY() - entity.pos().y) <= 15 && state.getBlock().getBlastResistance() <= 100) {
                if(level.getBlockState(pos.add(0, 40, 0)).getBlock().getBlastResistance() <= 100) {
                    level.setBlockState(pos.add(0, 40, 0), state);
                }
            }
        });
    }
}
