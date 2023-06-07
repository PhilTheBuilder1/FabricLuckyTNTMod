package com.luckytntmod.util.Explosions;

import com.luckytntmod.LuckyTNTMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.Material;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.EntityExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ImprovedExplosion extends Explosion {
    public final World world;
    public final double posX, posY, posZ;
    public final float size;
    public final ExplosionBehavior damageCalculator;
    List<Integer> affectedBlocks = new ArrayList<>();
    private static final ImprovedExplosion dummyExplosion = new ImprovedExplosion(LuckyTNTMod.accessWorld, new Vec3d(0, 0, 0), 0);
    public ImprovedExplosion(World world, Vec3d position, float size) {
        this(world, null, null, position, size);
    }

    public ImprovedExplosion(World world, @Nullable DamageSource source, Vec3d position, float size) {
        this(world, null, source, position, size);
    }

    public ImprovedExplosion(World world, @Nullable Entity explodingEntity, Vec3d position, float size) {
        this(world, explodingEntity, null, position.x, position.y, position.z, size);
    }

    public ImprovedExplosion(World world, @Nullable Entity explodingEntity, @Nullable DamageSource source, Vec3d position, float size) {
        this(world, explodingEntity, source, position.x, position.y, position.z, size);
    }

    public ImprovedExplosion(World world, @Nullable Entity explodingEntity, double x, double y, double z, float size) {
        this(world, explodingEntity, null, x, y, z, size);
    }
    public ImprovedExplosion(World world, @Nullable Entity explodingEntity, @Nullable DamageSource source, double x, double y, double z, float power) {
        super(world, explodingEntity, source, null, x, y, z, power, false, DestructionType.KEEP);
        this.world = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.size = power;
        damageCalculator = explodingEntity == null ? new ExplosionBehavior() : new EntityExplosionBehavior(explodingEntity);
    }

    public void doBlockExplosion(IForEachBlockExplosionEffect effect) {
        this.doBlockExplosion(1f, 1f, 1f, 1f, false, effect);
    }

    public void doBlockExplosion(IBlockExplosionCondition condition, IForEachBlockExplosionEffect effect) {
        this.doBlockExplosion(1f, 1f, 1f, 1f, false, condition, effect);
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

    protected Vec3d decodeBlockPos(int encodedVal) {
        int zRaw = (encodedVal & 0b00000000000000000000000111111111);
        int zNeg = (encodedVal & 0b00000000000000000000001000000000) >> 9;
        int yRaw = (encodedVal & 0b00000000000001111111110000000000) >> 10;
        int yNeg = (encodedVal & 0b00000000000010000000000000000000) >> 19;
        int xRaw = (encodedVal & 0b00011111111100000000000000000000) >> 20;
        int xNeg = (encodedVal & 0b00100000000000000000000000000000) >> 29;
        int xVal = xNeg == 1 ? -xRaw : xRaw;
        int yVal = yNeg == 1 ? -yRaw : yRaw;
        int zVal = zNeg == 1 ? -zRaw : zRaw;
        return new Vec3d(xVal, yVal, zVal);
    }

    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, boolean isStrongExplosion) {
        Set<Integer> blocks = new HashSet<>();
        long time = System.currentTimeMillis();
        for(short offX = (short)-size; offX <= size; offX++) {
            for(short offY = (short)-size; offY <= size; offY++) {
                for(short offZ = (short)-size; offZ <= size; offZ++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if((int) distance == (int) size) {
                        double xStep = offX / distance;
                        double yStep = offY / distance;
                        double zStep = offZ / distance;
                        float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                        double blockX = posX;
                        double blockY = posY;
                        double blockZ = posZ;
                        for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xStep * 0.3f * xzStrength;
                            blockY += yStep * 0.3f * yStrength;
                            blockZ += zStep * 0.3f * xzStrength;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(int pos : blocks) {
            BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
            world.getBlockState(pos2).getBlock().onDestroyedByExplosion(world, pos2, this);
            world.setBlockState(pos2, Blocks.AIR.getDefaultState());
        }
        if(fire) {
            for(int pos : blocks) {
                BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
                if(Math.random() > 0.75f && world.getBlockState(pos2).isAir() && world.getBlockState(pos2.down()).isSolidBlock(world, pos2)) {
                    world.setBlockState(pos2, FireBlock.getState(world, pos2));
                }
            }
        }
        System.out.println(System.currentTimeMillis()-time);
    }

    public void doOnionBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, boolean isStrongExplosion) {
        Set<Integer> blocks = new HashSet<>();
        long time1 = System.currentTimeMillis();
        int time = 0;
        float lastVecLength = 0;
        for(short offX = (short)-size; offX <= size; offX++) {
            for(short offZ = (short)-size; offZ <= size; offZ++) {
                for(short offY = (short)-size; offY <= size; offY++) {
                    double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    if((int) distance == (int) size) {
                        ++time;
                        double xStep = offX / distance;
                        double yStep = offY / distance;
                        double zStep = offZ / distance;
                        float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                        float beginningLength = vecLength;
                        double blockX = posX;
                        double blockY = posY;
                        double blockZ = posZ;
                        double xFinalStep = xStep * 0.3f * xzStrength;
                        double yFinalStep = yStep * 0.3f * yStrength;
                        double zFinalStep = zStep * 0.3f * xzStrength;
                        if(time % 2 == 1) {
                            for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                                blockX += xFinalStep;
                                blockY += yFinalStep;
                                blockZ += zFinalStep;
                                BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                                if(!world.isInBuildLimit(pos)) {
                                    break;
                                }
                                BlockState blockState = world.getBlockState(pos);
                                FluidState fluidState = world.getFluidState(pos);
                                if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                    Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                    if(explosionResistance.isPresent()) {
                                        vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                    }
                                    if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                        blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                    }
                                }
                                else {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                                if((int) vecStep == (int) (vecLength/2)) lastVecLength = beginningLength-vecLength;
                            }
                        }
                        else {
                            float init = vecLength/2f;
                            vecLength-=lastVecLength;
                            blockX += xStep * size / 2.5;
                            blockY += yStep * size / 2.5;
                            blockZ += zStep * size / 2.5;
                            for(float vecStep = init; vecStep < vecLength; vecStep += 0.225f) {
                                blockX += xStep * 0.3f * xzStrength;
                                blockY += yStep * 0.3f * yStrength;
                                blockZ += zStep * 0.3f * xzStrength;
                                BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                                if(!world.isInBuildLimit(pos)) {
                                    break;
                                }
                                BlockState blockState = world.getBlockState(pos);
                                FluidState fluidState = world.getFluidState(pos);
                                if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                    Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                    if(explosionResistance.isPresent()) {
                                        vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                    }
                                    if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                        blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                    }
                                }
                                else {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(int pos : blocks) {
            BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
            world.getBlockState(pos2).getBlock().onDestroyedByExplosion(world, pos2, this);
            world.setBlockState(pos2, Blocks.AIR.getDefaultState());
        }
        if(fire) {
            for(int pos : blocks) {
                BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
                if(Math.random() > 0.75f && world.getBlockState(pos2).isAir() && world.getBlockState(pos2.down()).isSolidBlock(world, pos2)) {
                    world.setBlockState(pos2, FireBlock.getState(world, pos2));
                }
            }
        }
        System.out.println(System.currentTimeMillis()-time1);
    }

    public void doFastOnionBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, boolean isStrongExplosion) {
        Set<Integer> blocks = new HashSet<>();
        long time1 = System.currentTimeMillis();
        int time = 0;
        int blockade = 0;
        float lastVecLength = 0;
        for(int y = (int) (-size*1.1); y<=(int) (size*1.1); y++) {
            for(double alpha = 0; alpha<2*Math.PI; alpha+=1/size) {
                --blockade;
                if(blockade <= 0) {
                    double cos = Math.cos(Math.pow(Math.abs(y), 1.04) / size * 1.169);
                    int offX = (int) (Math.cos(alpha) * size * cos);
                    int offZ = (int) (Math.sin(alpha) * size * cos);
                    ++time;
                    double xStep = offX / size;
                    double yStep = y / size;
                    double zStep = offZ / size;
                    float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                    float beginningLength = vecLength;
                    double blockX = posX;
                    double blockY = posY;
                    double blockZ = posZ;
                    double xFinalStep = xStep * 0.3f * xzStrength;
                    double yFinalStep = yStep * 0.3f * yStrength;
                    double zFinalStep = zStep * 0.3f * xzStrength;
                    if(time % 4 == 1) {
                        for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xFinalStep;
                            blockY += yFinalStep;
                            blockZ += zFinalStep;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                            }
                            if((int) vecStep == (int) (vecLength/2)) lastVecLength = beginningLength-vecLength;
                        }
                    }
                    else {
                        float init = vecLength/2f;
                        vecLength-=lastVecLength;
                        blockX += xStep * size / 2.5;
                        blockY += yStep * size / 2.5;
                        blockZ += zStep * size / 2.5;
                        for(float vecStep = init; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xFinalStep;
                            blockY += yFinalStep;
                            blockZ += zFinalStep;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                            }
                        }
                    }
                    if(beginningLength-vecLength < 12) blockade = 6;
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(int pos : blocks) {
            BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
            world.getBlockState(pos2).getBlock().onDestroyedByExplosion(world, pos2, this);
            world.setBlockState(pos2, Blocks.AIR.getDefaultState());
        }
        if(fire) {
            for(int pos : blocks) {
                BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
                if(Math.random() > 0.75f && world.getBlockState(pos2).isAir() && world.getBlockState(pos2.down()).isSolidBlock(world, pos2)) {
                    world.setBlockState(pos2, FireBlock.getState(world, pos2));
                }
            }
        }
        System.out.println(System.currentTimeMillis()-time1);
    }

    public void doFastBetterOnionBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, boolean isStrongExplosion) {
        Set<Integer> blocks = new HashSet<>();
        long time1 = System.currentTimeMillis();
        int time = 0;
        int blockade = 0;
        float lastVecLength = 0;
        for(double a = -size; a<=size; a+=Math.sqrt(size*size-a*a)/size+ (a <= -size*0.6 ? 0.1 : 0)) {
            System.out.println(a);
            for(double alpha = 0; alpha<2*Math.PI; alpha+=1/size) {
                --blockade;
                if(blockade <= 0) {
                    double radius = Math.sqrt(size * size - a * a);
                    int offX = (int) (Math.cos(alpha) * radius);
                    int offZ = (int) (Math.sin(alpha) * radius);
                    ++time;
                    double xStep = offX / size;
                    double yStep = a / size;
                    double zStep = offZ / size;
                    float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                    float beginningLength = vecLength;
                    double blockX = posX;
                    double blockY = posY;
                    double blockZ = posZ;
                    double xFinalStep = xStep * 0.3f * xzStrength;
                    double yFinalStep = yStep * 0.3f * yStrength;
                    double zFinalStep = zStep * 0.3f * xzStrength;
                    if(time % 4 == 1) {
                        for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xFinalStep;
                            blockY += yFinalStep;
                            blockZ += zFinalStep;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                            }
                            if((int) vecStep == (int) (vecLength/2)) lastVecLength = beginningLength-vecLength;
                        }
                    }
                    else {
                        float init = vecLength/2f;
                        vecLength-=lastVecLength;
                        blockX += xStep * size / 2.5;
                        blockY += yStep * size / 2.5;
                        blockZ += zStep * size / 2.5;
                        for(float vecStep = init; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xFinalStep;
                            blockY += yFinalStep;
                            blockZ += zFinalStep;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                            }
                        }
                    }
                    if(beginningLength-vecLength < 12) blockade = 6;
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(int pos : blocks) {
            BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
            world.getBlockState(pos2).getBlock().onDestroyedByExplosion(world, pos2, this);
            world.setBlockState(pos2, Blocks.AIR.getDefaultState());
        }
        if(fire) {
            for(int pos : blocks) {
                BlockPos pos2 = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(pos)));
                if(Math.random() > 0.75f && world.getBlockState(pos2).isAir() && world.getBlockState(pos2.down()).isSolidBlock(world, pos2)) {
                    world.setBlockState(pos2, FireBlock.getState(world, pos2));
                }
            }
        }
        System.out.println(System.currentTimeMillis()-time1);
    }

    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean isStrongExplosion, IForEachBlockExplosionEffect blockEffect) {
        Set<Integer> blocks = new HashSet<>();
        for(int offX = (int)-size; offX <= (int)size; offX++) {
            for(int offY = (int)-size; offY <= (int)size; offY++) {
                for(int offZ = (int)-size; offZ <= (int)size; offZ++) {
                    if(offX == (int)-size || offX == (int)size || offY == (int)-size || offY == (int)size || offZ == (int)-size || offZ == (int)size) {
                        double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                        double xStep = offX / distance;
                        double yStep = offY / distance;
                        double zStep = offZ / distance;
                        float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                        double blockX = posX;
                        double blockY = posY;
                        double blockZ = posZ;
                        for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xStep * 0.3f * xzStrength;
                            blockY += yStep * 0.3f * yStrength;
                            blockZ += zStep * 0.3f * xzStrength;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                            else {
                                blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(int intPos : blocks) {
            BlockPos pos = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(intPos)));
            double distance = Math.sqrt(pos.getSquaredDistance(posX, posY, posZ));
            blockEffect.doBlockExplosion(world, pos, world.getBlockState(pos), distance);
        }
    }

    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean isStrongExplosion, IBlockExplosionCondition condition, IForEachBlockExplosionEffect blockEffect) {
        Set<Integer> blocks = new HashSet<>();
        for(int offX = (int)-size; offX <= (int)size; offX++) {
            for(int offY = (int)-size; offY <= (int)size; offY++) {
                for(int offZ = (int)-size; offZ <= (int)size; offZ++) {
                    if(offX == (int)-size || offX == (int)size || offY == (int)-size || offY == (int)size || offZ == (int)-size || offZ == (int)size) {
                        double distance = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                        double xStep = offX / distance;
                        double yStep = offY / distance;
                        double zStep = offZ / distance;
                        float vecLength = size * (0.7f + (float)Math.random() * 0.6f * randomVecLength);
                        double blockX = posX;
                        double blockY = posY;
                        double blockZ = posZ;
                        for(float vecStep = 0; vecStep < vecLength; vecStep += 0.225f) {
                            blockX += xStep * 0.3f * xzStrength;
                            blockY += yStep * 0.3f * yStrength;
                            blockZ += zStep * 0.3f * xzStrength;
                            BlockPos pos = new BlockPos(blockX, blockY, blockZ);
                            if(!world.isInBuildLimit(pos)) {
                                break;
                            }
                            BlockState blockState = world.getBlockState(pos);
                            FluidState fluidState = world.getFluidState(pos);
                            if(!(isStrongExplosion && fluidState != Fluids.EMPTY.getDefaultState())) {
                                Optional<Float> explosionResistance = damageCalculator.getBlastResistance(this, world, pos, blockState, fluidState);
                                if(explosionResistance.isPresent()) {
                                    vecLength -= (explosionResistance.get() + 0.3f) * 0.3f * resistanceImpact;
                                }
                                if(vecLength > 0 && damageCalculator.canDestroyBlock(this, world, pos, blockState, vecLength) && blockState.getMaterial() != Material.AIR) {
                                    if(condition.conditionMet(world, pos, blockState, distance)) {
                                        blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                    }
                                }
                            }
                            else {
                                if(condition.conditionMet(world, pos, blockState, distance)) {
                                    blocks.add(encodeBlockPos((int)Math.round(blockX - posX), (int)Math.round(blockY - posY), (int)Math.round(blockZ - posZ)));
                                }
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(int intPos : blocks) {
            BlockPos pos = new BlockPos(new Vec3d(posX, posY, posZ).add(decodeBlockPos(intPos)));
            double distance = Math.sqrt(pos.getSquaredDistance(posX, posY, posZ));
            blockEffect.doBlockExplosion(world, pos, world.getBlockState(pos), distance);
        }
    }

    public void doEntityExplosion(float knockbackStrength, boolean damageEntities) {
        List<Entity> entities = world.getOtherEntities(getCausingEntity(), new Box(posX - size * 2, posY - size * 2, posZ - size * 2, posX + size * 2, posY + size * 2, posZ + size * 2));
        this.world.emitGameEvent(this.getEntity(), GameEvent.EXPLODE, getPosition());
        for(Entity entity : entities) {
            if(!entity.isImmuneToExplosion()) {
                double distance = Math.sqrt(entity.squaredDistanceTo(getPosition())) / size / 2;
                if(distance <= 1f) {
                    double offX = (entity.getX() - posX);
                    double offY = (entity.getEyeY() - posY);
                    double offZ = (entity.getZ() - posZ);
                    double distance2 = Math.sqrt(offX * offX + offY * offY + offZ * offZ);
                    offX /= distance2;
                    offY /= distance2;
                    offZ /= distance2;
                    double seenPercent = getExposure(getPosition(), entity);
                    float damage = (1f - (float)distance) * (float)seenPercent;
                    if(damageEntities) {
                        entity.damage(getDamageSource(), (damage * damage + damage) / 2f * 7 * size + 1f);
                    }
                    double knockback = damage;
                    if(entity instanceof LivingEntity lEnt) {
                        knockback = ProtectionEnchantment.transformExplosionKnockback(lEnt, damage);
                    }
                    entity.setVelocity(entity.getVelocity().add(offX * knockback * knockbackStrength, offY * knockback * knockbackStrength, offZ * knockback * knockbackStrength));
                    if(entity instanceof PlayerEntity player) {
                        player.velocityModified = true;
                        if(!player.isSpectator() && (!player.isCreative() || !player.getAbilities().flying)) {
                            getAffectedPlayers().put(player, new Vec3d(offX * damage, offY * damage, offZ * damage));
                        }
                    }
                }
            }
        }
    }

    public Vec3d getPosition() {
        return new Vec3d(posX, posY, posZ);
    }

    public static ImprovedExplosion dummyExplosion() {
        return dummyExplosion;
    }

    public static ImprovedExplosion dummyExplosion(World world) {
        return new ImprovedExplosion(world, new Vec3d(0, 0, 0), 0);
    }
}
