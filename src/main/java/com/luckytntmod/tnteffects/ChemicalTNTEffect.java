package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.IForEachBlockExplosionEffect;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class ChemicalTNTEffect extends PrimedTNTEffect {
    @Override
    public void baseTick(IExplosiveEntity entity) {
        if(entity instanceof LExplosiveProjectile) {
            if(!entity.world().isClient()) {
                explosionTick(entity);
            }
            else {
                spawnParticles(entity);
            }
            entity.setTNTFuse(entity.getTNTFuse() - 1);
            if(entity.getTNTFuse() <= 0) {
                entity.destroy();
            }
        }
        else {
            super.baseTick(entity);
        }
    }

    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity instanceof LExplosiveProjectile) {
            ImprovedExplosion dummyExplosion = new ImprovedExplosion(entity.world(), entity.pos(), 4);
            ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), 4, (world, pos, state, distance) -> {
                if(distance + Math.random() < 4f && state.getBlock().getBlastResistance() < 100) {
                    state.getBlock().onDestroyedByExplosion(world, pos, dummyExplosion);
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            });
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        for(int count = 0; count < 30; count++) {
            LExplosiveProjectile projectile = EntityRegistry.CHEMICAL_PROJECTILE.create(entity.world());
            if(projectile == null) return;
            projectile.setPosition(entity.pos());
            projectile.setOwner(entity.owner());
            projectile.setVelocity(Math.random() * 1.5f - Math.random() * 1.5f, 0.2f, Math.random() * 1.5f - Math.random() * 1.5f);
            entity.world().spawnEntity(projectile);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.1f, 1f, 0.6f), 1), entity.x() + 0.2f, entity.y() + 1f, entity.z(), 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.6f, 0.8f, 0.4f), 1), entity.x() - 0.2f, entity.y() + 1f, entity.z(), 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.8f, 1f, 0.8f), 1), entity.x(),+ entity.y() + 1f, entity.z() + 0.2f, 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.1f, 1f, 0.2f), 1), entity.x(),+ entity.y() + 1f, entity.z() - 0.2f, 0, 0, 0);
    }
}
