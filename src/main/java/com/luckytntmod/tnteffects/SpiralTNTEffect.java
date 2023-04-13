package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class SpiralTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity instanceof LTNTEntity) {
            Entity ent = (Entity)entity;
            ent.setVelocity(ent.getVelocity().x, 0.15f, ent.getVelocity().z);
            if(entity.getTNTFuse() < 60 && entity.getTNTFuse() % 3 == 0) {
                entity.getPersistentData().putFloat("spiral_power", MathHelper.clamp(entity.getPersistentData().getFloat("spiral_power") + 0.06f, 0.2f, Float.MAX_VALUE));
                LExplosiveProjectile spiral_tnt = EntityRegistry.SPIRAL_PROJECTILE.create(entity.world());
                if(spiral_tnt == null) return;
                spiral_tnt.setPos(entity.x(), entity.y(), entity.z());
                spiral_tnt.setOwner(entity.owner());
                spiral_tnt.setVelocity((Entity) entity, (float) ent.getRotationVecClient().x, (float) ent.getRotationVecClient().y+(getDefaultFuse(entity)-entity.getTNTFuse())*10, (float) ent.getRotationVecClient().z, entity.getPersistentData().getFloat("spiral_power"), 0);
                entity.world().playSound(null, new BlockPos(entity.pos()), SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.MASTER, 3, 1);
                entity.world().spawnEntity(spiral_tnt);
            }
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        if(entity instanceof LExplosiveProjectile) {
            TNTXStrengthEffect effect = new TNTXStrengthEffect().strength(10f).randomVecLength(1.25f).knockbackStrength(1.5f);
            effect.serverExplosion(entity);
        }
    }

    @Override
    public boolean airFuse() {
        return true;
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return entity instanceof LTNTEntity ? 140 : 10000;
    }
}
