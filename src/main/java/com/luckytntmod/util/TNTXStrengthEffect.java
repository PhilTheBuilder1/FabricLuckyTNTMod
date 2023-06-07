package com.luckytntmod.util;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class TNTXStrengthEffect extends PrimedTNTEffect {
    float strength = 2f;
    boolean createsFire = false;
    public boolean isStrongExplosion = false;
    public boolean damageEntities = true;
    public float xzStrength = 1f;
    public float yStrength = 1f;
    public float randomVecLength = 1f;
    public float knockbackStrength = 1f;
    public float resistanceImpact = 1f;
    int fuse = 80;
    int experiment = 0;
    ExplosionBehavior behavior = null;
    public TNTXStrengthEffect() {

    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return this.fuse;
    }

    public TNTXStrengthEffect strength(float strength) {
        this.strength = strength;
        return this;
    }

    public TNTXStrengthEffect resistanceImpact(float resistanceImpact) {
        this.resistanceImpact = resistanceImpact;
        return this;
    }

    public TNTXStrengthEffect createsFire() {
        this.createsFire = true;
        return this;
    }

    public TNTXStrengthEffect isStrongExplosion() {
        this.isStrongExplosion = true;
        return this;
    }
    public TNTXStrengthEffect randomVecLength(float length) {
        this.randomVecLength = length;
        return this;
    }

    public TNTXStrengthEffect xzStrength(float strength) {
        this.xzStrength = strength;
        return this;
    }

    public TNTXStrengthEffect yStrength(float strength) {
        this.yStrength = strength;
        return this;
    }

    public TNTXStrengthEffect behavior(ExplosionBehavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public TNTXStrengthEffect fuse(int fuse) {
        this.fuse = fuse;
        return this;
    }

    public TNTXStrengthEffect knockbackStrength(float strength) {
        this.knockbackStrength = strength;
        return this;
    }

    public TNTXStrengthEffect notDamageEntities() {
        this.damageEntities = false;
        return this;
    }

    public TNTXStrengthEffect experiment(int i) {
        this.experiment = i;
        return this;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), strength);
        switch(experiment) {
            case 0 -> explosion.doBlockExplosion(xzStrength, yStrength, resistanceImpact, randomVecLength, createsFire, isStrongExplosion);
            case 1 -> explosion.doOnionBlockExplosion(xzStrength, yStrength, resistanceImpact, randomVecLength, createsFire, isStrongExplosion);
            case 2 -> explosion.doFastOnionBlockExplosion(xzStrength, yStrength, resistanceImpact, randomVecLength, createsFire, isStrongExplosion);
            case 3 -> explosion.doFastBetterOnionBlockExplosion(xzStrength, yStrength, resistanceImpact, randomVecLength, createsFire, isStrongExplosion);
        }
        explosion.doEntityExplosion(knockbackStrength, damageEntities);
    }
}
