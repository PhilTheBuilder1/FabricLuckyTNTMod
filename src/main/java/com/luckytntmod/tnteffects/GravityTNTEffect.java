package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class GravityTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity.world().isClient()) return;
        double x = entity.pos().x;
        double y = entity.pos().y;
        double z = entity.pos().z;
        if(entity.getTNTFuse() < 200) {
            List<Entity> ents = entity.world().getOtherEntities((Entity)entity, new Box(new BlockPos(x - 25, y - 25, z - 25), new BlockPos(x + 25, y + 25, z + 25)));
            for(Entity ent : ents) {
                if(!(ent instanceof LTNTEntity)) {
                    double lx = ent.getX() - x;
                    double ly = ent.getY() - y;
                    double lz = ent.getZ() - z;
                    double distance = Math.sqrt(lx * lx + ly * ly + lz * lz) + 0.1f;
                    if(ent instanceof PlayerEntity) {
                        if(!((PlayerEntity) ent).isCreative())
                            if(distance > 2 && distance < 25 && ent.getVelocity().y < 5)
                                ent.setVelocity(-lx / distance, -ly / distance + 0.1f, -lz / distance);
                            else if(distance < 2)
                                ent.setVelocity(ent.getVelocity().x, 6, ent.getVelocity().z);
                    }
                    else {
                        if(distance > 2 && distance < 25 && ent.getVelocity().y < 5)
                            ent.setVelocity(-lx / distance, -ly / distance + 0.1f, -lz / distance);
                        else if(distance < 2)
                            ent.setVelocity(ent.getVelocity().x, 6, ent.getVelocity().z);
                    }
                }
            }
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 2f, entity.y() + 0.5f, entity.z(), -0.2f, 0, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 2f, entity.y() + 0.5f, entity.z(), 0.2f, 0, 0);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x(), entity.y() + 0.5f, entity.z() + 2f, 0, 0, -0.2f);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x(), entity.y() + 0.5f, entity.z() - 2f, 0, 0, 0.2f);

        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 1.5f, entity.y() + 0.5f, entity.z() + 1.5f, -0.1f, 0, -0.1f);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 1.5f, entity.y() + 0.5f, entity.z() - 1.5f, 0.1f, 0, 0.1f);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() + 1.5f, entity.y() + 0.5f, entity.z() - 1.5f, -0.1f, 0, 0.1f);
        entity.world().addParticle(ParticleTypes.SMOKE, entity.x() - 1.5f, entity.y() + 0.5f, entity.z() + 1.5f, 0.1f, 0, -0.1f);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 300;
    }
}
