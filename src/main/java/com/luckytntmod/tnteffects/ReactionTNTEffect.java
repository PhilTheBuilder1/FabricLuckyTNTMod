package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class ReactionTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        World world = entity.world();
        if(!world.isClient() && entity.getTNTFuse() < 100) {
            if(entity.getPersistentData().getInt("nextExplosion") == 0) {
                Vec3d randomPos = new Vec3d(Math.random() * 40 - 20, Math.random() * 20 - 10, Math.random() * 40 - 20);
                float explosionSize = 10 + world.random.nextFloat() * 10;
                ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity)entity, entity.pos().add(randomPos), Math.round(explosionSize));
                explosion.doEntityExplosion(1f + 0.05f * explosionSize, true);
                explosion.doBlockExplosion(1f, 1f, 0.75f, 1.25f, false, false);
                world.playSound((Entity) entity, new BlockPos(entity.pos()).add(randomPos.x, randomPos.y, randomPos.z), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (world.random.nextFloat() - world.random.nextFloat()) * 0.2f) * 0.7f);
                entity.getPersistentData().putInt("nextExplosion", 2 + world.random.nextInt(3));
            }
            entity.getPersistentData().putInt("nextExplosion", entity.getPersistentData().getInt("nextExplosion") - 1);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        if(Math.random() < 0.15f) {
            entity.world().addParticle(new DustParticleEffect(new Vector3f(0.37f, 1f, 1f), 1), entity.x() + Math.random() * 0.5f - Math.random() * 0.5f, entity.y() + 1f + Math.random() * 0.35f, entity.z() + Math.random() * 0.5f - Math.random() * 0.5f, 0, 0, 0);
        }
        if(Math.random() < 0.15f) {
            entity.world().addParticle(new DustParticleEffect(new Vector3f(0.59f, 1f, 0f), 1), entity.x() + Math.random() * 0.5f - Math.random() * 0.5f, entity.y() + 1f + Math.random() * 0.35f, entity.z() + Math.random() * 0.5f - Math.random() * 0.5f, 0, 0, 0);
        }
        if(Math.random() < 0.15f) {
            entity.world().addParticle(new DustParticleEffect(new Vector3f(0.11f, 0.26f, 0.11f), 1), entity.x() + Math.random() * 0.5f - Math.random() * 0.5f, entity.y() + 1f + Math.random() * 0.35f, entity.z() + Math.random() * 0.5f - Math.random() * 0.5f, 0, 0, 0);
        }
        if(Math.random() < 0.15f) {
            entity.world().addParticle(new DustParticleEffect(new Vector3f(0.16f, 0.42f, 0.15f), 1), entity.x() + Math.random() * 0.5f - Math.random() * 0.5f, entity.y() + 1f + Math.random() * 0.35f, entity.z() + Math.random() * 0.5f - Math.random() * 0.5f, 0, 0, 0);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 240;
    }
}
