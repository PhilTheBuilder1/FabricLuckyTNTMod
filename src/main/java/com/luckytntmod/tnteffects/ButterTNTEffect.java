package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;

public class ButterTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.level(), entity.pos(), 9, (level, pos, state, distance) -> {
            if(state.getBlock().getBlastResistance() < 100 && !state.isAir()) {
                level.setBlockState(pos, Blocks.GOLD_BLOCK.getDefaultState());
            }
        });
    }
}
