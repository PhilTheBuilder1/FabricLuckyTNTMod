package com.luckytntmod.util;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.EntityExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ExplosionPreGenerationThread extends Thread {
    private final int size;
    private double posX, posY, posZ;
    private final World world;
    public final ExplosionBehavior damageCalculator;
    public Set<Integer> blocks = new HashSet<>();
    private float xz;
    private float y;
    private float resImpact;
    private float rVecLength;
    private boolean strong;
    public boolean hasStopped = false;
    private ImprovedExplosion explosion;

    public ExplosionPreGenerationThread(int size, World world, @Nullable Entity explodingEntity) {
        this.size = size;
        this.posX = 0;
        this.posY = 0;
        this.posZ = 0;
        this.world = world;
        damageCalculator = explodingEntity == null ? new ExplosionBehavior() : new EntityExplosionBehavior(explodingEntity);
    }
    @Override
    public void run() {
        System.out.println("start------------------------------------------------------------------");
        this.explosion = new ImprovedExplosion(world, null, posX, posY, posZ, size);
        this.doBlockExplosion(xz, y, resImpact, rVecLength, strong);
        System.out.println("stop-------------------------------------------------------------------");
    }

    public void setParameters(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean isStrongExplosion) {
        this.xz = xzStrength;
        this.y = yStrength;
        this.resImpact = resistanceImpact;
        this.rVecLength = randomVecLength;
        this.strong = isStrongExplosion;
    }

    public void setPosition(double x, double y, double z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    protected int encodeBlockPos(int x, int y, int z) {
        int x0 = Integer.signum(x);
        x = Math.min(Math.abs(x), 511);
        x0 = x0 == -1 ? 0b1000000000 : 0;
        x += x0;

        x = x << 20;

        int y0 = Integer.signum(y);
        y = Math.min(Math.abs(y), 511);
        y0 = y0 == -1 ? 0b1000000000 : 0;
        y += y0;

        y = y << 10;

        int z0 = Integer.signum(z);
        z = Math.min(Math.abs(z), 511);
        z0 = z0 == -1 ? 0b1000000000 : 0;
        z += z0;

        return (x + y + z);
    }

    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean isStrongExplosion) {
        System.out.println("started block explosion");
        for(short offX = (short)-size; offX <= size; offX++) {
            System.out.println(1);
            for(short offY = (short)-size; offY <= size; offY++) {
                System.out.println(2);
                for(short offZ = (short)-size; offZ <= size; offZ++) {
                    System.out.println(3);
                    System.out.println(offZ + " | " + size);
                    if(offX == -size || offX == size || offY == -size || offY == size || offZ == -size || offZ == size) {
                        double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                        double xStep = offX / distance;
                        double yStep = offY / distance;
                        double zStep = offZ / distance;
                        float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                        double blockX = posX;
                        double blockY = posY;
                        double blockZ = posZ;
                        System.out.println("got here");
                        for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                            System.out.println("got here 1");
                            blockX += xStep * 0.3f * xzStrength;
                            blockY += yStep * 0.3f * yStrength;
                            blockZ += zStep * 0.3f * xzStrength;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            System.out.println("got here 2");
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            System.out.println("got here 3");
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(explosion, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(explosion, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                                System.out.println("got here 4|1");
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                System.out.println("got here 4|2");
                            }
                            System.out.println("got here aswell");
                        }
                        System.out.println("block done");
                    }
                }
            }
        }
        System.out.println("has stopped");
        this.hasStopped = true;
    }
}
