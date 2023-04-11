package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;

public class SphereTNTEffect extends PrimedTNTEffect {
    int radius;

    public SphereTNTEffect(int radius) {
        this.radius = radius;
    }
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), radius, (level, pos, state, distance) -> {
            if(state.getBlock().getBlastResistance() < 100f) {
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        });
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 120;
    }
}
