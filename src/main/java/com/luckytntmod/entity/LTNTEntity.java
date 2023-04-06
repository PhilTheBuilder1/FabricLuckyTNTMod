package com.luckytntmod.entity;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.block.LTNTBlock;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LTNTEntity extends TntEntity implements IExplosiveEntity {
    public LTNTBlock block;
    public PrimedTNTEffect effect;

    public LTNTEntity(EntityType<? extends TntEntity> entityType, World world, LTNTBlock block, PrimedTNTEffect effect) {
        super(entityType, world);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2f, -Math.cos(d) * 0.02);
        this.setFuse(80);
        this.block = block;
        this.effect = effect;
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        this.effect.explosionTick(this);
        if (i <= 0) {
            this.discard();
            if (!this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    @Override
    public int getTNTFuse() {
        return this.getFuse();
    }

    @Override
    public World world() {
        return this.getWorld();
    }

    @Override
    public Entity getThis() {
        return this;
    }

    @Override
    public double x() {
        return this.getX();
    }

    @Override
    public double y() {
        return this.getY();
    }

    @Override
    public double z() {
        return this.getZ();
    }

    @Override
    public Vec3d pos() {
        return this.getPos();
    }

    private void explode() {
        world.playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.MASTER, 1.0F, 1.0F);
        effect.serverExplosion(this);
    }


    public PrimedTNTEffect getEffect() {
        return this.effect;
    }
    @Override
    public Block getBlock() {
        return LuckyTNTMod.RH.registeredBlocks.get(0);
    }
}