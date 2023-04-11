package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.IForEachBlockExplosionEffect;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class OceanTNTEffect extends PrimedTNTEffect {
    private final int radius;
    private final int radiusY;
    private final int squidCound;

    public OceanTNTEffect(int radius, int radiusY, int squidCount) {
        this.radius = radius;
        this.radiusY = radiusY;
        this.squidCound = squidCount;
    }


    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion dummyExplosion = ImprovedExplosion.dummyExplosion();
        ExplosionHelper.doCylindricalExplosion(entity.world(), entity.pos(), radius, radiusY, (level, pos, state, distance) -> {
            if(pos.getY() <= entity.pos().y) {
                if((!state.isSideSolidFullSquare(level, pos, Direction.UP) && state.getBlock().getBlastResistance() < 100) || state.getBlock().getBlastResistance() < 4) {
                    state.getBlock().onDestroyedByExplosion(level, pos, dummyExplosion);
                    level.setBlockState(pos, Blocks.WATER.getDefaultState());
                }
            }
        });

        for(int i = 0; i < squidCound; i++) {
            SquidEntity squid = new SquidEntity(EntityType.SQUID, entity.world());
            squid.setPos(entity.x() + (Math.random() * radius * 2 - radius), entity.y(), entity.z() + (Math.random() * radius * 2 - radius));
            entity.world().spawnEntity(squid);
        }
    }

    @Override
    public void spawnParticles(IExplosiveEntity ent) {
        ent.world().addParticle(ParticleTypes.SPLASH, ent.x(), ent.y() + 0.7f, ent.z(), 0, 0, 0);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 160;
    }
}
