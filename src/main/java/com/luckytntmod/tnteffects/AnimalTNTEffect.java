package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.List;

public class AnimalTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        List<EntityType<?>> entities = List.of(EntityType.BAT, EntityType.SPIDER, EntityType.SKELETON, EntityType.ZOMBIE, EntityType.CREEPER, EntityType.PILLAGER, EntityType.VILLAGER, EntityType.ENDERMAN, EntityType.EVOKER, EntityType.IRON_GOLEM,
                EntityType.WITHER_SKELETON, EntityType.SHEEP, EntityType.COW, EntityType.PIG, EntityType.CHICKEN, EntityType.GIANT, EntityType.AXOLOTL, EntityType.WOLF, EntityType.WITCH, EntityType.SLIME, EntityType.MAGMA_CUBE,
                EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN, EntityType.CAT, EntityType.STRIDER);
        for(EntityType<?> entType : entities) {
            for(int count = 0; count < 2; count++){
                Entity ent = entType.create(entity.world());
                if(ent == null) continue;
                ent.setPos(entity.pos().x, entity.pos().y, entity.pos().z);
                entity.world().spawnEntity(ent);
            }
        }
    }
}
