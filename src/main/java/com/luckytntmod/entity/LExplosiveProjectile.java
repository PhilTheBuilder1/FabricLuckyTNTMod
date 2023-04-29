package com.luckytntmod.entity;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.ItemSupplier;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LExplosiveProjectile extends ArrowEntity implements IExplosiveEntity, ItemSupplier {

    @Nullable
    private LivingEntity thrower;
    public boolean hitEntity = false;
    private final PrimedTNTEffect effect;
    public int tickCount;
    private final Block block;

    private static final TrackedData<Integer> FUSE = DataTracker.registerData(LExplosiveProjectile.class, TrackedDataHandlerRegistry.INTEGER);
    private final NbtCompound compound;


    public LExplosiveProjectile(EntityType<LExplosiveProjectile> type, World level, PrimedTNTEffect effect) {
        super(type, level);
        this.effect = effect;
        this.dataTracker.set(FUSE, this.effect.getDefaultFuse(this));
        this.setTNTFuse(effect.getDefaultFuse(this));
        this.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
        this.block = null;
        this.compound = new NbtCompound();
    }

    public LExplosiveProjectile(EntityType<LExplosiveProjectile> type, World level, PrimedTNTEffect effect, Block block) {
        super(type, level);
        this.effect = effect;
        this.dataTracker.set(FUSE, this.effect.getDefaultFuse(this));
        this.setTNTFuse(effect.getDefaultFuse(this));
        this.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
        this.block = block;
        this.compound = new NbtCompound();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE, 1000);
    }

    @Override
    public void initFromStack(ItemStack stack) {
        super.initFromStack(stack);
        this.dataTracker.set(FUSE, this.effect.getDefaultFuse(this));
    }

    @Override
    public void onBlockHit(BlockHitResult hitResult) {
        Vec3d pos = hitResult.getPos().subtract(this.getX(), this.getY(), this.getZ());
        this.setVelocity(pos);
        Vec3d pos2 = pos.normalize().multiply(0.05F);
        this.setPos(this.getX() - pos2.x, this.getY() - pos2.y, this.getZ() - pos2.z);
        inGround = true;
        //this.effect.baseTick(this);
    }

    @Override
    public void onEntityHit(EntityHitResult hitResult) {
        if(hitResult.getEntity() instanceof PlayerEntity player) {
            if(!(player.isCreative() || player.isSpectator())) {
                hitEntity = true;
            }
        }
        else {
            hitEntity = true;
        }
        //this.effect.baseTick(this);
    }

    @Override
    protected void onHit(LivingEntity target) {
        if(target instanceof PlayerEntity player) {
            if(!(player.isCreative() || player.isSpectator())) {
                hitEntity = true;
            }
        }
        else {
            hitEntity = true;
        }
        //this.effect.baseTick(this);
    }

    @Override
    public void tick() {
        super.tick();
        this.effect.baseTick(this);
        ++tickCount;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        if(thrower != null) {
            tag.putInt("throwerID", thrower.getId());
        }
        tag.putShort("Fuse", (short) getTNTFuse());
        super.writeCustomDataToNbt(tag);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        if(world.getEntityById(tag.getInt("throwerID")) instanceof LivingEntity lEnt) {
            thrower = lEnt;
        }
        setTNTFuse(tag.getShort("Fuse"));
        super.readCustomDataFromNbt(tag);
    }

    public PrimedTNTEffect getEffect() {
        return effect;
    }

    @Override
    public NbtCompound getPersistentData() {
        return compound;
    }

    public boolean inGround() {
        return inGround;
    }

    public boolean hitEntity() {
        return hitEntity;
    }

    @Override
    public void setTNTFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public void setOwner(@Nullable LivingEntity thrower) {
        this.thrower = thrower;
    }

    @Override
    public void setOwner(Entity entity) {
        thrower = entity instanceof LivingEntity ? (LivingEntity) entity : thrower;
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        return thrower;
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
        return null;
    }

    @Override
    public void destroy() {
        discard();
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
    public ItemStack getItem() {
        return effect.getItemStack();
    }

    @Override
    public LivingEntity owner() {
        return getOwner();
    }
}