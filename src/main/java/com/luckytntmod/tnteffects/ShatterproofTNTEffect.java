package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.particle.DustParticleEffect;
import org.joml.Vector3f;

public class ShatterproofTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), 10, (level, pos, state, distance) -> {
            if(state.isSolidBlock(level, pos) && state.getBlock().getBlastResistance() < 1200) {
                level.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.1f, 0.1f, 0.1f), 1), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.5f, 0.3f, 0.8f), 1), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }
}
