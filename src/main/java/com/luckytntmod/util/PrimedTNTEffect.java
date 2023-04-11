package com.luckytntmod.util;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.entity.LTNTEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PrimedTNTEffect {
    public void serverExplosion(IExplosiveEntity entity) {

    }

    public void explosionTick(IExplosiveEntity entity) {

    }

    public void spawnParticles(IExplosiveEntity entity) {

    }

    public int getDefaultFuse(IExplosiveEntity entity) {
        return 80;
    }

    public boolean playsSound() {
        return true;
    }

    public boolean explodesOnImpact() {
        return true;
    }

    public boolean airFuse() {
        return false;
    }

    public ItemStack getItemStack() {
        return new ItemStack(getItem());
    }

    public float getSize(IExplosiveEntity entity) {
        return 1f;
    }

    public Item getItem() {
        return Items.AIR;
    }

    public Block getBlock() {
        return Blocks.TNT;
    }

    public BlockState getBlockState(IExplosiveEntity entity) {
        return getBlock().getDefaultState();
    }

    public void baseTick(IExplosiveEntity entity) {
        World level = entity.world();
        if(level == null) return;
        if(entity instanceof LTNTEntity) {
            if(entity.getTNTFuse() <= 0) {
                if(entity.world() instanceof ServerWorld) {
                    if(playsSound()) {
                        level.playSound((Entity)entity, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f) * 0.7f);
                    }
                    serverExplosion(entity);
                }
                entity.destroy();
            }
            explosionTick(entity);
            entity.setTNTFuse(entity.getTNTFuse() - 1);
        }
        else if(entity instanceof LExplosiveProjectile ent) {
            if((ent.inGround() || ent.hitEntity()) && !entity.world().isClient()) {
                if(explodesOnImpact()) {
                    ent.setTNTFuse(0);
                }
                if(ent.getTNTFuse() <= 0) {
                    if(ent.world() instanceof ServerWorld) {
                        if(playsSound()) {
                            level.playSound((Entity)entity, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f) * 0.7f);
                        }
                        serverExplosion(entity);
                    }
                    ent.destroy();
                }
            }
            else if(airFuse() && entity.getTNTFuse() == 0) {
                if(!ent.world().isClient()) {
                    if(playsSound()) {
                        level.playSound((Entity)entity, new BlockPos(entity.pos()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4f, (1f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f) * 0.7f);
                    }
                    serverExplosion(entity);
                }
                ent.destroy();
            }
            if((ent.getTNTFuse() > 0 && airFuse()) || ent.hitEntity() || ent.inGround()) {
                explosionTick(ent);
                ent.setTNTFuse(ent.getTNTFuse() - 1);
            }
        }
        if(level.isClient()) {
            spawnParticles(entity);
        }
    }
}
