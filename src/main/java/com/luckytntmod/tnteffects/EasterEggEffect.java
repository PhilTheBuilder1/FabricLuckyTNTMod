package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class EasterEggEffect extends PrimedTNTEffect {
    @Override
    public void baseTick(IExplosiveEntity entity) {
        super.baseTick(entity);
        if(((Entity)entity).isOnGround() && entity.getPersistentData().getInt("world") > 0) {
            serverExplosion(entity);
            World world = entity.world();
            entity.world().playSound((Entity)entity, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (world.random.nextFloat() - world.random.nextFloat()) * 0.2f) * 0.7f);
            entity.destroy();
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        int world = entity.getPersistentData().getInt("world");
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), 15);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
        explosion.doBlockExplosion((world1, pos, state, distance) -> {
            if(Math.random() < 0.66f && !state.isAir()) {
                state.getBlock().onDestroyedByExplosion(world1, pos, explosion);
                world1.setBlockState(pos, Blocks.AIR.getDefaultState());
                if(Math.random() < 0.625f) {
                    entity.world().setBlockState(pos, Blocks.MELON.getDefaultState());
                }
                else {
                    entity.world().setBlockState(pos, Blocks.PUMPKIN.getDefaultState());
                }
            }
        });
        if(world + 1 == 4) {
            entity.destroy();
        }
        else {
            for(int count = 0; count < 4; count++) {
                LTNTEntity tnt = EntityRegistry.EASTER_EGG.create(entity.world());
                if(tnt == null) return;
                tnt.setPosition(entity.pos());
                tnt.setVelocity(Math.random() * 2 - 1, 1 + Math.random(), Math.random() * 2 - 1);
                tnt.getPersistentData().putInt("world", world + 1);
                entity.world().spawnEntity(tnt);
            }
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0f, 0.5f, 0f), 1), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(1f, 0.5f, 0f), 1), entity.x() + 0.2f, entity.y() + 1f, entity.z() + 0.2f, 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(1f, 0.5f, 0f), 1), entity.x() - 0.2f, entity.y() + 1f, entity.z() - 0.2f, 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(1f, 0.5f, 0f), 1), entity.x() + 0.2f, entity.y() + 1f, entity.z() - 0.2f, 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(1f, 0.5f, 0f), 1), entity.x() - 0.2f, entity.y() + 1f, entity.z() + 0.2f, 0, 0, 0);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 120;
    }
}
