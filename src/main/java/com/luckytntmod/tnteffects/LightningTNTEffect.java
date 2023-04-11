package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class LightningTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        double x = entity.pos().x;
        double z = entity.pos().z;
        if(entity.getTNTFuse() < 120) {
            if(entity.world() instanceof ServerWorld) {
                double offX = Math.random() * 40 - 20;
                double offZ = Math.random() * 40 - 20;
                for(double offY = 320; offY > -64; offY--) {
                    if(entity.world().getBlockState(new BlockPos(x + offX, offY, z + offZ)).getMaterial() != Material.AIR) {
                        Entity lighting = new LightningEntity(EntityType.LIGHTNING_BOLT, entity.world());
                        lighting.setPos(x + offX, offY, z + offZ);
                        entity.world().spawnEntity(lighting);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 200;
    }
}
