package com.luckytntmod.tnteffects;

import com.luckytntmod.block.LTNTBlock;
import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;

public class IgniterTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public IgniterTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.level(), entity.pos(), strength, (level, pos, state, distance) -> {
            if(state.getBlock() instanceof LTNTBlock) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        });
    }
}
