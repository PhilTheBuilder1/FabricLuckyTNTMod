package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.List;

public class SensorTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        World level = entity.level();
        if(level instanceof ServerWorld) {
            List<PlayerEntity> players = level.getEntitiesByClass(PlayerEntity.class, new Box(entity.pos().add(-10f, -10f, -10f), entity.pos().add(10f, 10f, 10f)), playerEntity -> true);
            for(PlayerEntity player : players) {
               if(!player.equals(entity.owner())) {
                    ImprovedExplosion explosion = new ImprovedExplosion(level, entity.pos(), 10f);
                    explosion.doEntityExplosion(1f, true);
                    explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
                    level.playSound((Entity) entity, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f) * 0.7f);
                    entity.destroy();
               }
            }
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.level().addParticle(new DustParticleEffect(new Vector3f(1f, 0f, 0f), 1f), entity.x(), entity.y() + 1f, entity.z(), 0f, 0f, 0f);
    }

    @Override
    public boolean playsSound() {
        return false;
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 5000;
    }
}
