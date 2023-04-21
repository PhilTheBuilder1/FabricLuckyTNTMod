package com.luckytntmod.tnteffects;

import com.luckytntmod.registries.SoundRegistry;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

public class SayGoodbyeEffect extends PrimedTNTEffect {

    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(entity.getTNTFuse() == 30) {
            entity.world().playSound(null, entity.x(), entity.y(), entity.z(), SoundRegistry.SAY_GOODBYE, SoundCategory.HOSTILE, 20, 1);
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        PlayerEntity ent = entity.world().getClosestPlayer((Entity) entity, 60);
        if(ent != null) {
            ImprovedExplosion explosion = new ImprovedExplosion(ent.world, (Entity) entity, new DamageSource("say_goodbye"), ent.getX(), ent.getY(), ent.getZ(), 20f);
            explosion.doEntityExplosion(2f, true);
            explosion.doBlockExplosion(1f, 1f, 1f, 1.5f, false, false);
            if(entity.world() instanceof ServerWorld sLevel) {
                sLevel.spawnParticles(ParticleTypes.EXPLOSION, ent.getX(), ent.getY(), ent.getZ(), 60, 2, 2, 2, 0);
            }
        }
    }
}
