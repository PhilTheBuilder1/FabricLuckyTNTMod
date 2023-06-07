package com.luckytntmod.entity;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LTNTEntity extends TntEntity implements IExplosiveEntity {
    public Block block;

    public BlockState state;

    public PrimedTNTEffect effect;
    private final World levelWorld;
    private LivingEntity igniter;
    private int ticks = 0;

    public LTNTEntity(EntityType<? extends TntEntity> entityType, World world, Block block, PrimedTNTEffect effect) {
        super(entityType, world);
        this.levelWorld = world;
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2f, -Math.cos(d) * 0.02);
        this.block = block;
        this.effect = effect;
        this.setFuse(this.effect.getDefaultFuse(this));
        this.igniter = null;
        this.state = null;
    }

    public LTNTEntity(EntityType<? extends TntEntity> entityType, World world, Block block, PrimedTNTEffect effect, @Nullable LivingEntity igniter) {
        super(entityType, world);
        this.levelWorld = world;
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2f, -Math.cos(d) * 0.02);
        this.block = block;
        this.effect = effect;
        this.setFuse(this.effect.getDefaultFuse(this));
        this.igniter = igniter;

    }

    @Override
    public void tick() {
        ticks++;
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }
        this.effect.baseTick(this);
        if(this.getWorld().isClient()) {
            this.effect.spawnParticles(this);
        }
        if (this.getFuse() <= 0) {
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
        return this.levelWorld;
    }

    @Override
    public World level() {
        return IExplosiveEntity.super.level();
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

    @Override
    public LivingEntity owner() {
        return this.igniter;
    }

    public void setOwner(LivingEntity igniter) {
        this.igniter = igniter;
    }

    @Override
    public void destroy() {
        this.discard();
    }

    @Override
    public void setTNTFuse(int fuse) {
        this.setFuse(fuse);
    }
    

    private void explode() {
        world.playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.MASTER, 1.0F, 1.0F);
        if(!this.getWorld().isClient())
            effect.serverExplosion(this);
    }


    public PrimedTNTEffect getEffect() {
        return this.effect;
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public NbtCompound getPersistentData() {
        return super.getPersistentData();
    }
}
