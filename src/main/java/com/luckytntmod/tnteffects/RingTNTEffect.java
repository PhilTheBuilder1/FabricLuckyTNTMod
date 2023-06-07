package com.luckytntmod.tnteffects;

import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RingTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity ent) {
        for(double angle = 0; angle < 360; angle += 30D) {
            TntEntity tnt = new TntEntity(ent.world(), ent.x(), ent.y(), ent.z(), ent.owner());
            tnt.setFuse(80);
            double x = ent.x() + 10 * Math.cos(angle * Math.PI / 180);
            double z = ent.z() + 10 * Math.sin(angle * Math.PI / 180);
            double y = getFirstMotionBlockingBlock(ent.level(), x, z);
            tnt.setPos(x, y + 1D, z);
            ent.level().spawnEntity(tnt);
        }
    }

    @SuppressWarnings("deprecation")
    public static int getFirstMotionBlockingBlock(World level, double x, double z) {
        if(!level.isClient) {
            for(int offY = level.getTopY(); offY >= level.getBottomY(); offY--) {
                BlockPos pos = new BlockPos(x, offY, z);
                BlockPos posUp = new BlockPos(x, offY + 1, z);
                BlockState state = level.getBlockState(pos);
                BlockState stateUp = level.getBlockState(posUp);
                if(!state.getBlock().getCollisionShape(state, level, pos, ShapeContext.absent()).isEmpty() && stateUp.getBlock().getCollisionShape(stateUp, level, posUp, ShapeContext.absent()).isEmpty()) {
                    return offY;
                }
            }
        }
        return 0;
    }
}
