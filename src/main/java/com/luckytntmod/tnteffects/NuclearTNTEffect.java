package com.luckytntmod.tnteffects;

import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.IForEachBlockExplosionEffect;
import com.luckytntmod.util.Explosions.IForEachEntityExplosionEffect;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class NuclearTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public NuclearTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), (Entity) entity, entity.pos(), strength);
        explosion.doEntityExplosion(strength / 10f, true);
        /*explosion.doEntityExplosion((IForEachEntityExplosionEffect) (entity1, distance) -> {
            if(entity1 instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(EffectRegistry.CONTAMINATED_EFFECT.get(), 48 * strength));
            }
        });*/
        explosion.doBlockExplosion(1f, 1.3f, 1f, 1f, false, false);
        ExplosionHelper.doSphericalExplosion(entity.world(), entity.pos(), strength * 3, (IForEachBlockExplosionEffect) (level, pos, state, distance) -> {
            if(state.getBlock() instanceof DeadBushBlock || state.getBlock() instanceof LeavesBlock) {
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
            }
        });
        explosion.doBlockExplosion((level, pos, state, distance) -> {
            BlockState stateAbove = level.getBlockState(pos.up());
            if(stateAbove.isAir() && !state.isAir() && Math.random() < 0.33f) {
                level.setBlockState(pos.up(), BlockRegistry.NUCLEAR_WASTE.getDefaultState());
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.world().addParticle(new DustParticleEffect(new Vector3f(0.9f, 1f, 0f), 1), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 200;
    }
}
