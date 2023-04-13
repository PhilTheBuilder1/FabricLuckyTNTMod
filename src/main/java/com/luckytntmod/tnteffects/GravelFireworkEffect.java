package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class GravelFireworkEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        Entity ent = (Entity) entity;
        ent.setVelocity(ent.getVelocity().x, 0.8f, ent.getVelocity().z);
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count <= 300; count++) {
            FallingBlockEntity sand = FallingBlockEntity.spawnFromBlock(entity.world(), new BlockPos(entity.pos()), Blocks.GRAVEL.getDefaultState());
            sand.setVelocity((Math.random() - Math.random()) * 1.5f, (Math.random() - Math.random()) * 1.5f, (Math.random() - Math.random()) * 1.5f);
            entity.world().spawnEntity(sand);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0, 0);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 40;
    }
}
