package com.luckytntmod.tnteffects;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class WitheringTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public WitheringTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), strength);
        explosion.doEntityExplosion(2f, true);
        explosion.doBlockExplosion(1f, 1f, 1f, 1.5f, false, false);
        explosion.doBlockExplosion((level, pos, state, distance) -> {
            if(state.isSolidBlock(level, pos)) {
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
                level.setBlockState(pos, Math.random() < 0.5f ? Blocks.SOUL_SAND.getDefaultState() : Blocks.SOUL_SOIL.getDefaultState());
            }
        });
        for(int i = 0; i < strength * 2f; i++) {
            int offX = (int)Math.round(Math.random() * strength * 2f - strength);
            int offZ = (int)Math.round(Math.random() * strength * 2f - strength);
            WitherSkeletonEntity skeleton = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, entity.world());
            if(entity.world() instanceof ServerWorld sl) {
                skeleton.initialize(sl, entity.world().getLocalDifficulty(new BlockPos(entity.pos())), SpawnReason.MOB_SUMMONED, null, null);
            }
            for(int y = entity.world().getTopY(); y >= entity.world().getBottomY(); y--) {
                BlockPos pos = new BlockPos(entity.x() + offX, y, entity.z() + offZ);
                BlockState state = entity.world().getBlockState(pos);
                if(!Block.isFaceFullSquare(state.getCollisionShape(entity.world(), pos), Direction.UP) && Block.isFaceFullSquare(entity.world().getBlockState(pos.down()).getCollisionShape(entity.world(), pos.down()), Direction.UP)) {
                    skeleton.setPos(pos.getX() + 0.5f, y, pos.getZ() + 0.5f);
                    break;
                }
            }
            entity.world().spawnEntity(skeleton);
        }
    }

    @Override
    public int getDefaultFuse(IExplosiveEntity entity) {
        return 160;
    }
}
