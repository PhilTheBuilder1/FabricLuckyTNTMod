package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EruptingTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        World world = entity.world();
        if(entity instanceof LTNTEntity) {
            if(entity.getTNTFuse() < 60) {
                if(entity.getTNTFuse() % 3 == 0) {
                    LExplosiveProjectile erupting_tnt = EntityRegistry.ERUPTING_PROJECTILE.create(world);
                    if(erupting_tnt == null) return;
                    erupting_tnt.setPosition(entity.pos());
                    erupting_tnt.setOwner(entity.owner());
                    erupting_tnt.setVelocity((float) ((Math.random() * 2D - 1D) * 0.1f), (float) (0.6f + Math.random() * 0.4f), (float) ((Math.random() * 2D - 1D) * 0.1f), 3f + world.random.nextFloat() * 2f, 0f);
                    erupting_tnt.setOnFireFor(1000);
                    world.spawnEntity(erupting_tnt);
                    world.playSound(null, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.MASTER, 3, 1);
                }
            }
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        if(entity instanceof LExplosiveProjectile) {
            TNTXStrengthEffect effect = new TNTXStrengthEffect().strength(10f).randomVecLength(1.25f).knockbackStrength(1.5f).createsFire();
            effect.serverExplosion(entity);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        if(entity instanceof LTNTEntity) {
            entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 0.5f, entity.y() + 1f, entity.z() + 0.5f, 0.05f, 0.2f, 0.05f);
            entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 0.5f, entity.y() + 1f, entity.z() - 0.5f, -0.05f, 0.2f, -0.05f);
            entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 0.5f, entity.y() + 1f, entity.z() - 0.5f, 0.05f, 0.2f, -0.05f);
            entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 0.5f, entity.y() + 1f, entity.z() + 0.5f, -0.05f, 0.2f, 0.05f);
        }
        else {
            entity.world().addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0, 0);
        }
    }

    @Override
    public boolean airFuse() {
        return true;
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return entity instanceof LTNTEntity ? 140 : 100000;
    }
}
