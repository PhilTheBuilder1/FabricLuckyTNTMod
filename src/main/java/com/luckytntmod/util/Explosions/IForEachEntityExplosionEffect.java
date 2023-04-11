package com.luckytntmod.util.Explosions;

import net.minecraft.entity.Entity;

@FunctionalInterface
public interface IForEachEntityExplosionEffect {

    void doEntityExplosion(Entity entity, double distance);

}