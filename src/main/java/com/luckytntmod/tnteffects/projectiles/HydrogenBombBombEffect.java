package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.NuclearBombLike;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class HydrogenBombBombEffect extends PrimedTNTEffect implements NuclearBombLike {
    @Override
    public void serverExplosion(IExplosiveEntity ent) {

        ImprovedExplosion explosion = new ImprovedExplosion(ent.world(), ent.pos(), 360f);
        explosion.doEntityExplosion(25f, true);
        explosion.doBlockExplosion(1f, 1f, 0.167f, 0.05f, false, true);

        ExplosionHelper.doModifiedSphericalExplosion(ent.world(), ent.pos(), 250, new Vec3d(1f, (2f/3f), 1f), (world, pos, state, distance) -> {
            BlockPos posTop = pos.add(0, 1, 0);
            BlockState stateTop = world.getBlockState(posTop);
            if(distance <= 250) {
                if(Math.random() < 0.25f) {
                    if(Block.isFaceFullSquare(state.getCollisionShape(world, pos), Direction.UP) && state.getMaterial() != Material.AIR && !Block.isFaceFullSquare(stateTop.getCollisionShape(world, posTop), Direction.UP) && stateTop.getBlock().getBlastResistance() < 200) {
                        world.setBlockState(posTop, BlockRegistry.NUCLEAR_WASTE.getDefaultState(), 3);
                    }
                }
            }
        });

        /*List<LivingEntity> list = ent.world().getEntitiesOfClass(LivingEntity.class, new AABB(ent.x() - 90, ent.y() - 65, ent.z() - 90, ent.x() + 90, ent.y() + 65, ent.z() + 90));
        for(LivingEntity living : list) {
            living.addStatusEffect(new StatusEffectInstance(EffectRegistry.CONTAMINATED_EFFECT.get(), 4800, 0, true, true, true));
        }*/

        ent.destroy();
    }

    @Override
    public void displayMushroomCloud(IExplosiveEntity ent) {
        for(int count = 0; count < 3000; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 120 - Math.random() * 120, ent.y() + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 120 - Math.random() * 120, 0, 0, 0);
        }
        for(int count = 0; count < 2000; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 40 - Math.random() * 40, ent.y() + 6 + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 40 - Math.random() * 40, 0, 0, 0);
        }
        for(int count = 0; count < 1600; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 20 - Math.random() * 20, ent.y() + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 20 - Math.random() * 20, 0, 0, 0);
        }
        for(int count = 0; count < 1200; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 12 - Math.random() * 12, ent.y() + 8 + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 12 - Math.random() * 12, 0, 0, 0);
        }
        for(int count = 0; count < 1200; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 4 - Math.random() * 4, ent.y() + 30 + Math.random() * 24 - Math.random() * 24, ent.z() + Math.random() * 4 - Math.random() * 4, 0, 0, 0);
        }
        for(int count = 0; count < 1200; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 12 - Math.random() * 12, ent.y() + 44 + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 12 - Math.random() * 12, 0, 0, 0);
        }
        for(int count = 0; count < 1200; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 12 - Math.random() * 12, ent.y() + 58 + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 12 - Math.random() * 12, 0, 0, 0);
        }
        for(int count = 0; count < 4000; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 24 - Math.random() * 24, ent.y() + 48 + Math.random() * 12 - Math.random() * 12, ent.z() + Math.random() * 24 - Math.random() * 24, 0, 0, 0);
        }
        for(int count = 0; count < 4000; count++) {
            ent.world().addParticle(ParticleTypes.LARGE_SMOKE, true, ent.x() + Math.random() * 4 - Math.random() * 4, ent.y() + 44 + Math.random() * 4 - Math.random() * 4, ent.z() + Math.random() * 4 - Math.random() * 4, Math.random() * 4 - Math.random() * 4, Math.random() * 4 - Math.random() * 4, Math.random() * 4 - Math.random() * 4);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity ent) {

    }

    @Override
    public float getSize(IExplosiveEntity ent) {
        return 1.5f;
    }
}
