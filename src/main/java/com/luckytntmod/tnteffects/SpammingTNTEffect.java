package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SpammingTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count <= 400; count++) {
            ItemEntity dirt = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(Items.DIRT));
            dirt.setVelocity(Math.random() * 6 - 3, 3 + Math.random() * 3, Math.random() * 6 - 3);
            entity.level().spawnEntity(dirt);
        }
    }

    @Override
    public void explosionTick(IExplosiveEntity entity) {
        for(int count = 0; count <= 20; count++) {
            ItemEntity dirt = new ItemEntity(entity.level(), entity.x(), entity.y(), entity.z(), new ItemStack(Items.DIRT));
            dirt.setVelocity(Math.random() * 4 - 2, 2 + Math.random() * 2, Math.random() * 4 - 2);
            entity.level().spawnEntity(dirt);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 160;
    }
}
