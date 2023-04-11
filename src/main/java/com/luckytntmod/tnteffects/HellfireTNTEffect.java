package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HellfireTNTEffect extends PrimedTNTEffect {
    private final int strength;
    private final int ghastCount;

    public HellfireTNTEffect(int strength, int ghastCount) {
        this.strength = strength;
        this.ghastCount = ghastCount;
    }
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity) entity, entity.pos().x, entity.pos().y + 0.5f, entity.pos().z, strength);
        explosion.doEntityExplosion(2f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.5f, false, false);
        ImprovedExplosion netherExplosion = new ImprovedExplosion(entity.world(), entity.pos().add(0, 0.5f, 0), strength * 1.5f);
        netherExplosion.doBlockExplosion(1f, 1f, 1f, 1.5f, false, (level, pos, state, distance) -> {
            if(distance <= 25) {
                if(Math.random() < 0.9f) {
                    state.getBlock().onDestroyedByExplosion(level, pos, netherExplosion);
                    level.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
                    if(Math.random() < 0.1f) {
                        if(level.getBlockState(pos.up()).isAir()) {
                            level.setBlockState(pos.up(), FireBlock.getState(level, pos.up()));
                        }
                    }
                }
                else if(Math.random() < 0.3f) {
                    state.getBlock().onDestroyedByExplosion(level, pos, netherExplosion);
                    level.setBlockState(pos, Blocks.LAVA.getDefaultState());
                }
            }
        });
        for(int i = 0; i < ghastCount; i++) {
            GhastEntity ghast = new GhastEntity(EntityType.GHAST, entity.world());
            Vec3d vec = entity.pos().add(0, 20 + Math.random() * 20, 0);
            ghast.setPos(vec.x, vec.y, vec.z);
            entity.world().playSound(ghast, ghast.getBlockPos(), SoundEvents.ENTITY_GHAST_HURT, SoundCategory.HOSTILE, 3f, 1f);
            entity.world().spawnEntity(ghast);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        World level = entity.world();
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0.1f, 0);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0.05f, 0.1f, 0);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), -0.05f, 0.1f, 0);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0.1f, 0.05f);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0.1f, -0.05f);

        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0.2f, 0, 0);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), -0.2f, 0, 0);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0, 0.2f);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0, 0, -0.2f);

        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0.1f, 0, 0.1f);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), -0.1f, 0, -0.1f);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), 0.1f, 0, -0.1f);
        level.addParticle(ParticleTypes.FLAME, entity.x(), entity.y() + 0.5f, entity.z(), -0.1f, 0, 0.1f);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 160;
    }
}
