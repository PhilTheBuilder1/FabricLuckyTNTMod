package com.luckytntmod.util.Explosions;

import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;

public class ExplosionHelper {
    public static HashMap<BlockPos, BlockState> getBlocksInSphere(World level, Vec3d position, int radius) {
        HashMap<BlockPos, BlockState> blocks = new HashMap<>();
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offY = radius; offY >= -radius; offY--) {
                for(int offZ = -radius; offZ <= radius; offZ++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        blocks.put(pos, state);
                    }
                }
            }
        }
        return blocks;
    }

    public static HashMap<BlockPos, BlockState> getBlocksInCuboid(World level, Vec3d position, Vec3d radii) {
        HashMap<BlockPos, BlockState> blocks = new HashMap<>();
        for(int offX = (int)-radii.x; offX <= (int)radii.x; offX++) {
            for(int offY = (int)radii.y; offY >= (int)-radii.y; offY--) {
                for(int offZ = (int)-radii.z; offZ <= (int)radii.z; offZ++) {
                    BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                    BlockState state = level.getBlockState(pos);
                    blocks.put(pos, state);
                }
            }
        }
        return blocks;
    }

    public static HashMap<BlockPos, BlockState> getBlocksInCylinder(World level, Vec3d position, int radius, int radiusY) {
        HashMap<BlockPos, BlockState> blocks = new HashMap<>();
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offY = radiusY; offY >= -radiusY; offY--) {
                for(int offZ = -radius; offZ <= radius; offZ++) {
                    double distance = Math.sqrt(offX * offX + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        blocks.put(pos, state);
                    }
                }
            }
        }
        return blocks;
    }

    public static void doSphericalExplosion(World level, Vec3d position, int radius, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offY = radius; offY >= -radius; offY--) {
                for(int offZ = -radius; offZ <= radius; offZ++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        blockEffect.doBlockExplosion(level, pos, state, distance);
                    }
                }
            }
        }
    }

    public static void doModifiedSphericalExplosion(World level, Vec3d position, int radius, Vec3d scaling, IForEachBlockExplosionEffect blockEffect) {
        for(double offX = -radius * scaling.x; offX <= radius * scaling.x; offX++) {
            for(double offY = radius * scaling.y; offY >= -radius * scaling.y; offY--) {
                for(double offZ = -radius * scaling.z; offZ <= radius * scaling.z; offZ++) {
                    double distance = Math.sqrt(offX * offX / scaling.x + offY * offY / scaling.y + offZ * offZ / scaling.z);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        blockEffect.doBlockExplosion(level, pos, state, distance);
                    }
                }
            }
        }
    }

    public static void doCubicalExplosion(World level, Vec3d position, int radius, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offY = -radius; offY <= radius; offY++) {
                for(int offZ = -radius; offZ <= radius; offZ++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                    BlockState state = level.getBlockState(pos);
                    blockEffect.doBlockExplosion(level, pos, state, distance);
                }
            }
        }
    }

    public static void doCuboidExplosion(World level, Vec3d position, Vec3d radii, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = (int)-radii.x; offX <= (int)radii.x; offX++) {
            for(int offY = (int)-radii.y; offY <= (int)radii.y; offY++) {
                for(int offZ = (int)-radii.z; offZ <= (int)radii.z; offZ++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                    BlockState state = level.getBlockState(pos);
                    blockEffect.doBlockExplosion(level, pos, state, distance);
                }
            }
        }
    }

    public static void doCylindricalExplosion(World level, Vec3d position, int radius, int radiusY, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offY = -radiusY; offY <= radiusY; offY++) {
                for(int offZ = -radius; offZ <= radius; offZ++) {
                    double distance = Math.sqrt(offX * offX + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        blockEffect.doBlockExplosion(level, pos, state, distance);
                    }
                }
            }
        }
    }

    public static void doTopBlockExplosion(World level, Vec3d position, int radius, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offZ = -radius; offZ <= radius; offZ++) {
                for(int offY = radius; offY >= -radius; offY--) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        if((level.getBlockState(pos.down()).isFullCube(level, pos.down()) || level.getBlockState(pos.down()).isSideSolidFullSquare(level, pos.down(), Direction.UP)) && (state.isAir() || state.canPlaceAt(level, pos)  || !state.isSolidBlock(level, pos) || state.isIn(BlockTags.FLOWERS))) {
                            blockEffect.doBlockExplosion(level, pos, state, distance);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void doTopBlockExplosion(World level, Vec3d position, int radius, IBlockExplosionCondition condition, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offZ = -radius; offZ <= radius; offZ++) {
                topToBottom: for(int offY = radius; offY >= -radius; offY--) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        if(!level.getBlockState(pos.down()).isAir()) {
                            if(condition.conditionMet(level, pos.down(), level.getBlockState(pos.down()), Math.sqrt(offX * offX + (offY-1) * (offY-1) + offZ * offZ))) {
                                blockEffect.doBlockExplosion(level, pos, state, distance);
                                break topToBottom;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void doTopBlockExplosionForAll(World level, Vec3d position, int radius, IForEachBlockExplosionEffect blockEffect) {
        for(int offX = -radius; offX <= radius; offX++) {
            for(int offZ = -radius; offZ <= radius; offZ++) {
                for(int offY = radius; offY >= -radius; offY--) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if(distance <= radius) {
                        BlockPos pos = new BlockPos(position).add(offX, offY, offZ);
                        BlockState state = level.getBlockState(pos);
                        if(state.isAir() && !level.getBlockState(pos.down()).isAir()) {
                            blockEffect.doBlockExplosion(level, pos, state, distance);
                        }
                    }
                }
            }
        }
    }
}
