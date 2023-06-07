package com.luckytntmod.util;

import net.minecraft.nbt.NbtCompound;

public interface ITNTEntityInterface {
    default NbtCompound getPersistentData() {
        return null;
    }
}
