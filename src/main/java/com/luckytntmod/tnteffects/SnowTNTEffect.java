package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
public class SnowTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public SnowTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doTopBlockExplosionForAll(entity.world(), entity.pos(), strength, (level, pos, state, distance) -> {
            state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
            level.setBlockState(pos, Blocks.SNOW.getDefaultState());
        });
    }
}
