package com.luckytntmod.tnteffects;

import com.luckytntmod.block.TrollTNTMk3Block;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

public class TrollTNTMk3Effect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), 10);
        explosion.doBlockExplosion((level, pos, state, distance) -> state.getBlock() instanceof TrollTNTMk3Block, (level, pos, state, distance) -> {
            state.getBlock().onDestroyedByExplosion(level, pos, explosion);
            level.removeBlock(pos, false);
        });
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 1;
    }
}
