package com.luckytntmod.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface IExplosiveEntity {
    Block getBlock();

    int getTNTFuse();

    World world();

    Entity getThis();
    double x();
    double y();
    double z();

    Vec3d pos();

    LivingEntity owner();

    void destroy();

    void setTNTFuse(int fuse);

    PrimedTNTEffect getEffect();
    NbtCompound getPersistentData();
}
