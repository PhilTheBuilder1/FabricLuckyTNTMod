package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieHorseEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class ZombieApocalypseEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count <= 30 + Math.random() * 15; count++) {
            ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, entity.world());
            zombie.setPosition(entity.pos());
            entity.world().spawnEntity(zombie);
        }
        for(int count = 0; count <= 10 + Math.random() * 5; count++) {
            ZombieHorseEntity zombie = new ZombieHorseEntity(EntityType.ZOMBIE_HORSE, entity.world());
            zombie.setPosition(entity.pos());
            entity.world().spawnEntity(zombie);
        }
        for(int count = 0; count <= 15 + Math.random() * 10; count++) {
            ZombieVillagerEntity zombie = new ZombieVillagerEntity(EntityType.ZOMBIE_VILLAGER, entity.world());
            zombie.setPosition(entity.pos());
            entity.world().spawnEntity(zombie);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0, 0.7f, 0), 1), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }
}
