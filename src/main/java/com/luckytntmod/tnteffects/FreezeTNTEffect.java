package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.FluidBlock;

public class FreezeTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public FreezeTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), strength, (level, pos, state, distance) -> {
            if((state.getBlock().getBlastResistance() < 100 || state.getBlock() instanceof FluidBlock) && !(state.getBlock() instanceof DeadBushBlock) && !state.isAir()) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.setBlockState(pos, Blocks.ICE.getDefaultState());
            }
        });
    }
}
