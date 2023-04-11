package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;

public class FlatTNTEffect extends PrimedTNTEffect {
    private final int radius;
    private final int radiusY;

    public FlatTNTEffect(int radius, int radiusY) {
        this.radius = radius;
        this.radiusY = radiusY;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion dummyExplosion = ImprovedExplosion.dummyExplosion();
        ExplosionHelper.doCylindricalExplosion(entity.world(), entity.pos(), radius, radiusY, (level, pos, state, distance) -> {
            if(pos.getY() >= entity.y() - 0.5f) {
                if(state.getBlock().getBlastResistance() <= 100) {
                    state.getBlock().onDestroyedByExplosion(level, pos, dummyExplosion);
                    level.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        });
    }
}
