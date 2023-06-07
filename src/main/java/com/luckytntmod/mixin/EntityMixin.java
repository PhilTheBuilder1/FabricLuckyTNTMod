package com.luckytntmod.mixin;

import com.luckytntmod.util.ITNTEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public class EntityMixin implements ITNTEntityInterface {
    NbtCompound persistentData;

    @Override
    @Unique
    public NbtCompound getPersistentData() {
        if(persistentData == null) persistentData = new NbtCompound();
        return persistentData;
    }
}