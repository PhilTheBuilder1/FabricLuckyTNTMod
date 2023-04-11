package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Direction;

public class MiningflatTNTEffect extends PrimedTNTEffect {
    private final int radius;
    private final int radiusY;

    public MiningflatTNTEffect(int radius, int radiusY) {
        this.radius = radius;
        this.radiusY = radiusY;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doCylindricalExplosion(entity.world(), entity.pos(), radius, radiusY, (level, pos, state, distance) -> {
            if(pos.getY() >= entity.y() - 0.5f) {
                if(state.getBlock().getBlastResistance() < 100) {
                    if(state.isIn(BlockTags.STONE_ORE_REPLACEABLES) || state.isIn(BlockTags.DEEPSLATE_ORE_REPLACEABLES)) {
                        Block.dropStacks(state, level, pos);
                    }
                    state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                    level.setBlockState(pos, Blocks.AIR.getDefaultState());
                    if(pos.getY() - Math.round(entity.y()) == 0) {
                        if(Math.random() < 0.05f && Block.sideCoversSmallSquare(level, pos.down(), Direction.UP)) {
                            level.setBlockState(pos, Blocks.TORCH.getDefaultState());
                        }
                    }
                }
            }
        });
    }
}
