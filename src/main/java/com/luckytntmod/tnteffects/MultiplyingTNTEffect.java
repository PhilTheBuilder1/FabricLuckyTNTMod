package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiplyingTNTEffect extends PrimedTNTEffect {
    @Override
    public void baseTick(IExplosiveEntity entity) {
        super.baseTick(entity);
        if(((Entity)entity).isOnGround() && entity.getPersistentData().getInt("level") > 0) {
            serverExplosion(entity);
            if(entity.getPersistentData().getInt("level") == 4) {
                World level = entity.level();
                entity.level().playSound((Entity)entity, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f) * 0.7f);
            }
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        int level = entity.getPersistentData().getInt("level");
        if (level == 4) {
            ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), entity.pos(), 10f);
            explosion.doEntityExplosion(1f, true);
            explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
        } else if (level == 0) {
            for (int count = 0; count < 4; count++) {
                LTNTEntity tnt = EntityRegistry.MULTIPLYING_TNT.create(entity.level());
                if(tnt == null) continue;
                tnt.setPosition(entity.pos());
                tnt.setVelocity(Math.random() * 2 - 1, 1 + Math.random(), Math.random() * 2 - 1);
                tnt.getPersistentData().putInt("level", level + 1);
                entity.level().spawnEntity(tnt);
            }
        } else {
            for (int count = 0; count < level * 2; count++) {
                LTNTEntity tnt = EntityRegistry.MULTIPLYING_TNT.create(entity.level());
                if(tnt == null) continue;
                tnt.setPosition(entity.pos());
                tnt.setVelocity(Math.random() * 2 - 1, 1 + Math.random(), Math.random() * 2 - 1);
                tnt.getPersistentData().putInt("level", level + 1);
                entity.level().spawnEntity(tnt);
            }
        }
        entity.destroy();
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return entity.getPersistentData().getInt("level") == 4 ? 100000 : 120;
    }
}
