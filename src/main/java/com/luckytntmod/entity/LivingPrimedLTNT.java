package com.luckytntmod.entity;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LivingPrimedLTNT extends PathAwareEntity implements IExplosiveEntity {

    @Nullable
    private LivingEntity igniter;
    public Block block;
    private NbtCompound compound;
    private final PrimedTNTEffect effect;
    private static final TrackedData<Integer> FUSE = DataTracker.registerData(LivingPrimedLTNT.class, TrackedDataHandlerRegistry.INTEGER);

    public LivingPrimedLTNT(EntityType<? extends PathAwareEntity> type, World level, Block block, PrimedTNTEffect effect) {
        super(type, level);
        this.effect = effect;
        if(effect == null) return;
        this.setTNTFuse(effect.getDefaultFuse(this));
        this.block = block;
        this.compound = new NbtCompound();
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2f, -Math.cos(d) * 0.02);
    }

    @Override
    public void tick() {
        super.tick();
        effect.baseTick(this);
        if(this.world.isClient) {
            this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE, 1000);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        if(igniter != null) {
            tag.putInt("throwerID", igniter.getId());
        }
        tag.putShort("Fuse", (short) getTNTFuse());
        super.writeCustomDataToNbt(tag);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        if(world.getEntityById(tag.getInt("throwerID")) instanceof LivingEntity lEnt) {
            igniter = lEnt;
        }
        setTNTFuse(tag.getShort("Fuse"));
        super.readCustomDataFromNbt(tag);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void pushOutOfBlocks(double x, double y, double z) {
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public int getTNTFuse() {
        return this.dataTracker.get(FUSE);
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
    public void setTNTFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }
    @Override
    public double x() {
        return getX();
    }

    @Override
    public double y() {
        return getY();
    }

    @Override
    public double z() {
        return getZ();
    }

    @Override
    public Vec3d pos() {
        return this.getPos();
    }

    @Override
    public void destroy() {
        discard();
    }

    @Override
    public PrimedTNTEffect getEffect() {
        return effect;
    }

    @Override
    public NbtCompound getPersistentData() {
        return compound;
    }

    @Override
    public LivingEntity owner() {
        return igniter;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.569f);
    }
}