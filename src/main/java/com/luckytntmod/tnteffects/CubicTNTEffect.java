package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;

public class CubicTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public CubicTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doCubicalExplosion(entity.world(), entity.pos(), strength, (level, pos, state, distance) -> {
            if(state.getBlock().getBlastResistance() <= 100) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        });
    }
}
