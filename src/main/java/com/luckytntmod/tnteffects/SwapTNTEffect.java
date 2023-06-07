package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

import java.util.List;
import java.util.Random;

public class SwapTNTEffect extends PrimedTNTEffect {
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        int[] ids = entity.getPersistentData().getIntArray("entities");
        if(entity.getTNTFuse() < 40 && ids.length == 0) {
            List<Entity> entList = entity.level().getOtherEntities((Entity)entity, new Box(entity.x() - 70, entity.y() - 70, entity.z() - 70, entity.x() + 70, entity.y() + 70, entity.z() + 70));
            ids = new int[entList.size()];
            entity.setTNTFuse(0);
            for(int i = 0; i < entList.size(); i++) {
                entity.setTNTFuse(entity.getTNTFuse() + 2);
                ids[i] = entList.get(i).getId();
            }
            entity.getPersistentData().putIntArray("entities", ids);
        }
        if(ids.length != 0 && entity.getTNTFuse() % 2 == 0) {
            if(entity.getPersistentData().getInt("count") < ids.length) {
                Entity ent1 = entity.level().getEntityById(ids[entity.getPersistentData().getInt("count")]);
                Entity ent2 = entity.level().getEntityById(ids[new Random().nextInt(ids.length)]);
                if(ent1 != null && ent2 != null) {
                    Vec3d pos1 = ent1.getPos();
                    Vec3d pos2 = ent2.getPos();

                    ent1.setPosition(pos2);
                    entity.level().playSound(null, new BlockPos(pos2), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 2, 1);
                    for(int count = 0; count < 40; count++) {
                        entity.level().addParticle(new DustParticleEffect(new Vector3f(1f, 0f, 1f), 1f), pos2.x + Math.random() * ent1.getWidth() - Math.random() * ent1.getWidth(), pos2.y + Math.random() * ent1.getHeight(), pos2.z + Math.random() * ent1.getHeight() - Math.random() * ent1.getHeight(), 0, 0, 0);
                    }

                    ent2.setPosition(pos1);
                    entity.level().playSound(null, new BlockPos(pos1), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 2, 1);
                    for(int count = 0; count < 40; count++) {
                        entity.level().addParticle(new DustParticleEffect(new Vector3f(1f, 0f, 1f), 1f), pos1.x + Math.random() * ent2.getWidth() - Math.random() * ent2.getWidth(), pos1.y + Math.random() * ent2.getHeight(), pos1.z + Math.random() * ent2.getHeight() - Math.random() * ent2.getHeight(), 0, 0, 0);
                    }
                }
                entity.getPersistentData().putInt("count", entity.getPersistentData().getInt("count") + 1);
            }
            else {
                entity.getPersistentData().putInt("fuse", 0);
            }
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 120;
    }
}
