package com.luckytntmod.tnteffects;

import com.luckytntmod.block.TunnelingTNTBlock;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.registries.BlockRegistry;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.explosion.Explosion;

public class TunnelingTNTEffect extends PrimedTNTEffect {
    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        Direction direction = Direction.byName(entity.getPersistentData().getString("direction")) != null ? Direction.byName(entity.getPersistentData().getString("direction")) : Direction.EAST;
        switch(direction) {
            case NORTH: for(double offX = -4; offX <= 4; offX++) {
                for(double offY = -4; offY <= 4; offY++) {
                    for(double offZ = -90; offZ <= 0; offZ++) {
                        double distance = Math.sqrt(offX * offX + offY * offY);
                        BlockPos pos = new BlockPos(entity.x() + offX, entity.y() + offY, entity.z() + offZ);
                        BlockState state = entity.level().getBlockState(pos);
                        if(distance < 4 && state.getBlock().getBlastResistance() < 100) {
                            Block block = state.getBlock();
                            block.onDestroyedByExplosion(entity.level(), pos, new Explosion(entity.level(), (Entity) entity, null, null, entity.x(), entity.y(), entity.z(), 0, false, Explosion.DestructionType.DESTROY_WITH_DECAY));
                            entity.level().setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }
                }
            }
                break;
            case EAST: for(double offX = 0; offX <= 90; offX++) {
                for(double offY = -4; offY <= 4; offY++) {
                    for(double offZ = -4; offZ <= 4; offZ++) {
                        double distance = Math.sqrt(offZ * offZ + offY * offY);
                        BlockPos pos = new BlockPos(entity.x() + offX, entity.y() + offY, entity.z() + offZ);
                        BlockState state = entity.level().getBlockState(pos);
                        if(distance < 4 && state.getBlock().getBlastResistance() < 100) {
                            Block block = state.getBlock();
                            block.onDestroyedByExplosion(entity.level(), pos, new Explosion(entity.level(), (Entity) entity, null, null, entity.x(), entity.y(), entity.z(), 0, false, Explosion.DestructionType.DESTROY_WITH_DECAY));
                            entity.level().setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }
                }
            }
                break;
            case SOUTH: for(double offX = -4; offX <= 4; offX++) {
                for(double offY = -4; offY <= 4; offY++) {
                    for(double offZ = 0; offZ <= 90; offZ++) {
                        double distance = Math.sqrt(offX * offX + offY * offY);
                        BlockPos pos = new BlockPos(entity.x() + offX, entity.y() + offY, entity.z() + offZ);
                        BlockState state = entity.level().getBlockState(pos);
                        if(distance < 4 && state.getBlock().getBlastResistance() < 100) {
                            Block block = state.getBlock();
                            block.onDestroyedByExplosion(entity.level(), pos, new Explosion(entity.level(), (Entity) entity, null, null, entity.x(), entity.y(), entity.z(), 0, false, Explosion.DestructionType.DESTROY_WITH_DECAY));
                            entity.level().setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }
                }
            }
                break;
            case WEST: for(double offX = -90; offX <= 0; offX++) {
                for(double offY = -4; offY <= 4; offY++) {
                    for(double offZ = -4; offZ <= 4; offZ++) {
                        double distance = Math.sqrt(offZ * offZ + offY * offY);
                        BlockPos pos = new BlockPos(entity.x() + offX, entity.y() + offY, entity.z() + offZ);
                        BlockState state = entity.level().getBlockState(pos);
                        if(distance < 4 && state.getBlock().getBlastResistance() < 100) {
                            Block block = state.getBlock();
                            block.onDestroyedByExplosion(entity.level(), pos, new Explosion(entity.level(), (Entity) entity, null, null, entity.x(), entity.y(), entity.z(), 0, false, Explosion.DestructionType.DESTROY_WITH_DECAY));
                            entity.level().setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }
                }
            }
                break;
            default: break;
        }
    }

    public BlockState setState(IExplosiveEntity entity) {
        //System.out.println("Thing2: " + ((Entity) entity).getPersistentData().getString("direction"));
        ((LTNTEntity) entity).state = BlockRegistry.TUNNELING_TNT.getDefaultState().with(TunnelingTNTBlock.FACING, Direction.byName(((Entity) entity).getPersistentData().getString("direction")) != null ? Direction.byName(((Entity) entity).getPersistentData().getString("direction")) : Direction.EAST);
        return ((LTNTEntity) entity).state;
    }
}
