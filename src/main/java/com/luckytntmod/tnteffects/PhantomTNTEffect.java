package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class PhantomTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), entity.pos(), 20);
        explosion.doEntityExplosion(2f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.5f, false, false);
    }

    public void explosionTick(IExplosiveEntity entity) {
        if(entity.getTNTFuse() == 5) {
            double offX = Math.random() * 90 - 45;
            double offZ = Math.random() * 90 - 45;
            boolean foundBlock = false;
            for(int offY = 320; offY > -64; offY--) {
                BlockPos pos = new BlockPos(entity.x() + offX, offY, entity.z() + offZ);
                BlockState state = entity.level().getBlockState(pos);
                if(state.isSolidBlock(entity.level(), pos) && state.getMaterial() != Material.AIR && !foundBlock) {
                    ((Entity)entity).setPos(entity.x() + offX, offY + 1, entity.z() + offZ);
                    foundBlock = true;
                }
            }
        }
    }
}
