package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;

public class PhysicsTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public PhysicsTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), (Entity)entity, entity.pos(), strength);
        explosion.doBlockExplosion((level, pos, state, distance) -> {
            if(state.getBlock().getBlastResistance() < 100F) {
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
                FallingBlockEntity sand = FallingBlockEntity.spawnFromBlock(level, pos.add(0.5F, 0, 0.5F), state);
                sand.setVelocity(Math.random() * 2f - 1f, 0.5f + Math.random() * 2, Math.random() * 2f - 1f);
                level.spawnEntity(sand);
            }
        });
    }
}
