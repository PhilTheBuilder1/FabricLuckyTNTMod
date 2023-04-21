package com.luckytntmod.tnteffects;

import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class NuclearWasteTNTEffect extends PrimedTNTEffect {
    private final int radius;

    public NuclearWasteTNTEffect(int radius) {
        this.radius = radius;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doTopBlockExplosionForAll(entity.world(), entity.pos(), radius, (level, pos, state, distance) -> {
            state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
            level.setBlockState(pos, BlockRegistry.NUCLEAR_WASTE.getDefaultState());
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.9f, 1f, 0f), 1), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }
}
