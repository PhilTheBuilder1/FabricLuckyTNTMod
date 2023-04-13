package com.luckytntmod.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")

public class NuclearWasteBlock extends FallingBlock {
    public NuclearWasteBlock(AbstractBlock.Settings settings) {
        super(settings);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0, 0, 0, 16, 2, 16);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        super.scheduledTick(state, level, pos, random);
        if(Math.random() < 0.4f) {
            if(level.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock().getBlastResistance() < 100) {
                level.setBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), Blocks.AIR.getDefaultState(), 3);
                level.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1, 1);
                if(Math.random() < 0.05f) {
                    level.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    level.spawnParticles(new DustParticleEffect(new Vector3f(1f, 1f, 0.1f), 1), pos.getX(), pos.getY(), pos.getZ(), 40, 0.6f, 0.6f, 0.6f, 0);
                }
                level.spawnParticles(new DustParticleEffect(new Vector3f(1f, 1f, 0.1f), 1), pos.getX(), pos.getY() - 1, pos.getZ(), 40, 0.6f, 0.6f, 0.6f, 0);
            }
        }
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder lootBuilder) {
        return Collections.singletonList(ItemStack.EMPTY);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if(entity instanceof LivingEntity l_Entity) {
            l_Entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 4, false, true));
            //l_Entity.addEffect(new MobEffectInstance(EffectRegistry.CONTAMINATED_EFFECT.get(), 120, 0, false, true));
            l_Entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 0, false, true));
        }
        else if(entity instanceof ItemEntity i_Entity) {
            i_Entity.damage(new Explosion(world, entity, null, null, 0, 0, 0, 0, false, Explosion.DestructionType.DESTROY_WITH_DECAY).getDamageSource(), 100);
        }
    }
}