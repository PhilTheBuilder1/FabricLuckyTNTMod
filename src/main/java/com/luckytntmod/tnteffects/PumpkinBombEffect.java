package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;

import java.util.Random;

public class PumpkinBombEffect extends PrimedTNTEffect {
    /*
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), 10f);
        explosion.doEntityExplosion(1.5f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
        Random random = new Random();
        for(int count = 0; count < 30 + random.nextInt(11); count++) {
            ItemEntity candy = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(ItemRegistry.RED_CANDY.get()));
            candy.setDeltaMovement(Math.random() - 0.5f, Math.random() - 0.5f, Math.random() - 0.5f);
            entity.level().addFreshEntity(candy);
        }
        for(int count = 0; count < 60 + random.nextInt(21); count++) {
            ItemEntity candy = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(ItemRegistry.GREEN_CANDY.get()));
            candy.setDeltaMovement(Math.random() - 0.5f, Math.random() - 0.5f, Math.random() - 0.5f);
            entity.level().addFreshEntity(candy);
        }
        for(int count = 0; count < 40 + random.nextInt(16); count++) {
            ItemEntity candy = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(ItemRegistry.BLUE_CANDY.get()));
            candy.setDeltaMovement(Math.random() - 0.5f, Math.random() - 0.5f, Math.random() - 0.5f);
            entity.level().addFreshEntity(candy);
        }
        for(int count = 0; count < 20 + random.nextInt(6); count++) {
            ItemEntity candy = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(ItemRegistry.PURPLE_CANDY.get()));
            candy.setDeltaMovement(Math.random() - 0.5f, Math.random() - 0.5f, Math.random() - 0.5f);
            entity.level().addFreshEntity(candy);
        }
        for(int count = 0; count < 70 + random.nextInt(31); count++) {
            ItemEntity candy = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(ItemRegistry.YELLOW_CANDY.get()));
            candy.setDeltaMovement(Math.random() - 0.5f, Math.random() - 0.5f, Math.random() - 0.5f);
            entity.level().addFreshEntity(candy);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.level().addParticle(new DustParticleOptions(new Vector3f(1f, 0.5f, 0f), 1f), entity.x(), entity.y() + 1f, entity.z(), 0f, 0f, 0f);
    }
    */
}
