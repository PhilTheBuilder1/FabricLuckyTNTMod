package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleTypes;

public class TNTFireworkEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        Entity ent = (Entity)entity;
        ent.setVelocity(ent.getVelocity().x, 0.8f, ent.getVelocity().z);
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count <= 300; count++) {
            TntEntity TNT = new TntEntity(EntityType.TNT, entity.world());
            TNT.setPos(entity.pos().x, entity.pos().y, entity.pos().z);
            TNT.setVelocity((Math.random() - Math.random()) * 1.5f, (Math.random() - Math.random()) * 1.5f, (Math.random() - Math.random()) * 1.5f);
            TNT.setFuse(TNT.getFuse() / 2 + (int)(TNT.getFuse() * (Math.random() + 0.2f)));
            entity.world().spawnEntity(TNT);
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
