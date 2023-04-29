package com.luckytntmod.tnteffects.projectiles;

import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.NuclearBombLike;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.joml.Vector3f;


public class TsarBombBombEffect extends PrimedTNTEffect implements NuclearBombLike {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {

        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), 200f);
        explosion.doEntityExplosion(15f, true);
        explosion.doBlockExplosion(1f, 1f, 0.167f, 0.05f, false, true);

        /*List<LivingEntity> list = entity.world().getEntitiesByClass(LivingEntity.class, new Box(entity.x() - 90, entity.y() - 65, entity.z() - 90, entity.x() + 90, entity.y() + 65, entity.z() + 90), EntityRegistry.PREDICATE);
        for(LivingEntity living : list) {
            living.addStatusEffect(new St(EffectRegistry.CONTAMINATED_EFFECT.get(), 3600, 0, true, true, true));
        }*/

        for(int offX = -300; offX <= 300; offX++) {
            for(int offY = -300 / 3; offY <= 300 / 3; offY++) {
                for(int offZ = -300; offZ <= 300; offZ++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    BlockPos pos = new BlockPos(entity.x() + offX, entity.y() + offY, entity.z() + offZ);
                    BlockState state = entity.world().getBlockState(pos);
                    if(distance <= 300 && state.getBlock().getBlastResistance() <= 200) {
                        if(distance <= 150 && entity.world().getBlockState(pos.down()).isSideSolidFullSquare(entity.world(), pos.down(), Direction.UP) && Math.random() < 0.2D && (state.isAir() || state.getBlock().getHardness() <= 0.2f)) {
                            entity.world().setBlockState(pos, BlockRegistry.NUCLEAR_WASTE.getDefaultState(), 3);
                        }
                        if(state.isIn(BlockTags.LEAVES)) {
                            entity.world().setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }
                }
            }
        }

        entity.destroy();
    }

    @Override
    public void displayMushroomCloud(IExplosiveEntity ent) {
        for(int count = 0; count < 1500; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 60 - Math.random() * 60, ent.y() + Math.random() * 3 - Math.random() * 3, ent.z() + Math.random() * 60 - Math.random() * 60, 0, 0, 0);
        }
        for(int count = 0; count < 1000; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 20 - Math.random() * 20, ent.y() + 3 + Math.random() * 3 - Math.random() * 3, ent.z() + Math.random() * 20 - Math.random() * 20, 0, 0, 0);
        }
        for(int count = 0; count < 800; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 10 - Math.random() * 10, ent.y() + Math.random() * 3 - Math.random() * 3, ent.z() + Math.random() * 10 - Math.random() * 10, 0, 0, 0);
        }
        for(int count = 0; count < 600; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 6 - Math.random() * 6, ent.y() + 4 + Math.random() * 3 - Math.random() * 3, ent.z() + Math.random() * 6 - Math.random() * 6, 0, 0, 0);
        }
        for(int count = 0; count < 600; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 2 - Math.random() * 2, ent.y() + 15 + Math.random() * 12 - Math.random() * 12, ent.z() + Math.random() * 2 - Math.random() * 2, 0, 0, 0);
        }
        for(int count = 0; count < 600; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 6 - Math.random() * 6, ent.y() + 22 + Math.random() * 3 - Math.random() * 3, ent.z() + Math.random() * 6 - Math.random() * 6, 0, 0, 0);
        }
        for(int count = 0; count < 600; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 6 - Math.random() * 6, ent.y() + 29 + Math.random() * 3 - Math.random() * 3, ent.z() + Math.random() * 6 - Math.random() * 6, 0, 0, 0);
        }
        for(int count = 0; count < 2000; count++) {
            ent.world().addParticle(new DustParticleEffect(new Vector3f(1f, 2f, 0f), 10f), true, ent.x() + Math.random() * 12 - Math.random() * 12, ent.y() + 24 + Math.random() * 6 - Math.random() * 6, ent.z() + Math.random() * 12 - Math.random() * 12, 0, 0, 0);
        }
        for(int count = 0; count < 2000; count++) {
            ent.world().addParticle(ParticleTypes.LARGE_SMOKE, true, ent.x() + Math.random() * 2 - Math.random() * 2, ent.y() + 22 + Math.random() * 2 - Math.random() * 2, ent.z() + Math.random() * 2 - Math.random() * 2, Math.random() * 2 - Math.random() * 2, Math.random() * 2 - Math.random() * 2, Math.random() * 2 - Math.random() * 2);
        }
    }
}
