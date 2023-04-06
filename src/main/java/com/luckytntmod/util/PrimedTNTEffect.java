package com.luckytntmod.util;

public abstract class PrimedTNTEffect {
    public void serverExplosion(IExplosiveEntity entity) {

    }

    public void explosionTick(IExplosiveEntity entity) {

    }

    public int getDefaultFuse(IExplosiveEntity entity) {
        return 80;
    }
}
