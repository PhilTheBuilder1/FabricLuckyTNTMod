package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Direction;
import org.joml.Vector3f;

public class LavaOceanTNTEffect extends PrimedTNTEffect {
    private final int radius;
    private final int radiusY;

    public LavaOceanTNTEffect(int radius, int radiusY) {
        this.radius = radius;
        this.radiusY = radiusY;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doCylindricalExplosion(entity.world(), entity.pos(), radius, radiusY, (level, pos, state, distance) -> {
            if(pos.getY() <= entity.pos().y) {
                if((!state.isSideSolidFullSquare(level, pos, Direction.UP) && state.getBlock().getBlastResistance() < 100) || state.getBlock().getBlastResistance() < 4) {
                    state.getBlock().onDestroyedByExplosion(level, pos, ImprovedExplosion.dummyExplosion());
                    level.setBlockState(pos, Blocks.LAVA.getDefaultState());
                }
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(1f, 0.5f, 0.1f), 1f), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 160;
    }
}
