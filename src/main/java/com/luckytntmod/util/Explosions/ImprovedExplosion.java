package com.luckytntmod.util.Explosions;

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
    List<BlockPos> affectedBlocks = new ArrayList<>();
    private static final ImprovedExplosion dummyExplosion = new ImprovedExplosion(null, new Vec3d(0, 0, 0), 0);
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
    
    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean fire, boolean isStrongExplosion) {
        Set<BlockPos> blocks = new HashSet<>();
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
                                    blocks.add(pos);
                                }
                            }
                            else {
                                blocks.add(pos);
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(BlockPos pos : blocks) {
            world.getBlockState(pos).getBlock().onDestroyedByExplosion(world, pos, this);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
        if(fire) {
            for(BlockPos pos : blocks) {
                if(Math.random() > 0.75f && world.getBlockState(pos).isAir() && world.getBlockState(pos.down()).isSolidBlock(world, pos)) {
                    world.setBlockState(pos, FireBlock.getState(world, pos));
                }
            }
        }
    }

    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean isStrongExplosion, IForEachBlockExplosionEffect blockEffect) {
        Set<BlockPos> blocks = new HashSet<>();
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
                                    blocks.add(pos);
                                }
                            }
                            else {
                                blocks.add(pos);
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(BlockPos pos : blocks) {
            double distance = Math.sqrt(pos.getSquaredDistance(posX, posY, posZ));
            blockEffect.doBlockExplosion(world, pos, world.getBlockState(pos), distance);
        }
    }

    public void doBlockExplosion(float xzStrength, float yStrength, float resistanceImpact, float randomVecLength, boolean isStrongExplosion, IBlockExplosionCondition condition, IForEachBlockExplosionEffect blockEffect) {
        Set<BlockPos> blocks = new HashSet<>();
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
                                        blocks.add(pos);
                                    }
                                }
                            }
                            else {
                                if(condition.conditionMet(world, pos, blockState, distance)) {
                                    blocks.add(pos);
                                }
                            }
                        }
                    }
                }
            }
        }
        affectedBlocks.addAll(blocks);
        for(BlockPos pos : blocks) {
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

    /*public void doEntityExplosion(IForEachEntityExplosionEffect entityEffect) {
        List<Entity> entities = level.getEntities(getExploder(), new AABB(posX - size * 2, posY - size * 2, posZ - size * 2, posX + size * 2, posY + size * 2, posZ + size * 2));
        ForgeEventFactory.onExplosionDetonate(level, this, entities, size * 2);
        for(Entity entity : entities) {
            if(!entity.ignoreExplosion()) {
                double distance = Math.sqrt(entity.distanceToSqr(getPosition())) / (size * 2);
                if(distance < 1f && distance != 0) {
                    entityEffect.doEntityExplosion(entity, distance);
                }
            }
        }
    }*/

    public Vec3d getPosition() {
        return new Vec3d(posX, posY, posZ);
    }

    public static ImprovedExplosion dummyExplosion() {
        return dummyExplosion;
    }
}
