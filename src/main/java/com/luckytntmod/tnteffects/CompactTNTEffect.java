package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.block.TntBlock;

public class CompactTNTEffect extends PrimedTNTEffect {
    private final double chance;
    private final float size;
    private final TntBlock place;

    public CompactTNTEffect(double chance, float size, TntBlock place) {
        this.chance = chance;
        this.size = size;
        this.place = place;
    }

    public void serverExplosion(IExplosiveEntity entity) {
        TNTXStrengthEffect effect = new TNTXStrengthEffect().strength(10f).randomVecLength(1.25f).knockbackStrength(1.5f).createsFire();
        effect.serverExplosion(entity);
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), size);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.5f, false, (level, pos, state, distance) -> {
            if(Math.random() < chance && !state.isAir() && state.getBlock().getBlastResistance() < 100) {
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
                level.setBlockState(pos, place.getDefaultState());
            }
        });
    }
}
