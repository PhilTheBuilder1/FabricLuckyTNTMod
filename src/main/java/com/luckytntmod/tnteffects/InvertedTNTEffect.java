package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class InvertedTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(float angleY = 0; angleY <= 360; angleY += 11.25f) {
            for(float angleX = 0; angleX <= 360; angleX += 11.25f) {
                double offX = 30 * ((Entity)entity).getRotationVec(1).x + Math.random() * 4f - Math.random() * 4f;
                double offY = 30 * ((Entity)entity).getRotationVec(1).y + Math.random() * 4f - Math.random() * 4f;
                double offZ = 30 * ((Entity)entity).getRotationVec(1).z + Math.random() * 4f - Math.random() * 4f;
                double magnitude = Math.sqrt(offX * offX + offY * offY + offZ * offZ) + 0.1f;
                for(int j = 1; j < magnitude; j++) {
                    BlockPos pos = new BlockPos(entity.x() + offX * j / magnitude, entity.y() + offY * j / magnitude, entity.z() + offZ * j / magnitude);
                    if(entity.world().getBlockState(pos).getMaterial() == Material.AIR || entity.world().getBlockState(pos).getMaterial() == Material.PLANT || entity.world().getBlockState(pos).getMaterial() == Material.REPLACEABLE_PLANT || entity.world().getBlockState(pos).getMaterial() == Material.REPLACEABLE_PLANT) {
                        if(entity.world().getBlockState(new BlockPos(pos.getX(), pos.getY() - 30 * 2, pos.getZ())).getBlock().getBlastResistance() < 100) {
                            entity.world().setBlockState(pos, entity.world().getBlockState(new BlockPos(pos.getX(), pos.getY() - 30 * 2, pos.getZ())), 3);
                        }
                    }
                }
                ((Entity)entity).setPitch(angleX);
            }
            ((Entity)entity).setYaw(angleY);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 120;
    }
}
