package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;

public class XRayTNTEffect extends PrimedTNTEffect {
    private final int radius;

    public XRayTNTEffect(int radius) {
        this.radius = radius;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.level(), entity.pos(), radius, (level, pos, state, distance) -> {
            if(!(state.isIn(BlockTags.STONE_ORE_REPLACEABLES) || state.isIn(BlockTags.DEEPSLATE_ORE_REPLACEABLES)) && !state.isAir() && state.getBlock().getBlastResistance() <= 100) {
                state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                level.setBlockState(pos, Blocks.GLASS.getDefaultState());
            }
        });
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 160;
    }
}
