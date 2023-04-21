package com.luckytntmod.block;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.entity.LivingPrimedLTNT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class LTNTBlock extends TntBlock {
    int index;
    public String tab;
    boolean living = false;
    boolean shouldRandomlyFuse = true;
    public LTNTBlock(Settings settings, int index, String tab) {
        super(settings);
        this.index = index;
        this.tab = tab;
    }

    public LTNTBlock(Settings settings, int index, String tab, boolean isLivingTNT) {
        super(settings);
        this.index = index;
        this.tab = tab;
        this.living = isLivingTNT;
    }

    public LTNTBlock(Settings settings, int index, String tab, boolean isLivingTNT, boolean shouldRandomlyFuse) {
        super(settings);
        this.index = index;
        this.tab = tab;
        this.living = isLivingTNT;
        this.shouldRandomlyFuse = shouldRandomlyFuse;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(Items.FLINT_AND_STEEL) && !itemStack.isOf(Items.FIRE_CHARGE)) {
            return super.onUse(state, world, pos, player, hand, hit);
        } else {
            primeTnt(world, pos, player);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            Item item = itemStack.getItem();
            if (!player.isCreative()) {
                if (itemStack.isOf(Items.FLINT_AND_STEEL)) {
                    itemStack.damage(1, player, (playerx) -> playerx.sendToolBreakStatus(hand));
                } else {
                    itemStack.decrement(1);
                }
            }

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(world.isClient);
        }
    }
    public static void primeTnt(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!world.isClient()) {
            LTNTBlock block = (LTNTBlock) world.getBlockState(pos).getBlock();
            if(block.living) {
                LivingPrimedLTNT tnt = LuckyTNTMod.RH.registeredLivingEntities.get(block.index).spawn((ServerWorld) world, pos, SpawnReason.MOB_SUMMONED);
                if(tnt == null) return;
                world.spawnEntity(tnt);
                world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.MASTER, 1.0F, 1.0F);
                world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
                if(!block.shouldRandomlyFuse) {
                    tnt.setVelocity(0, 0, 0);
                }
            }
            else {
                LTNTEntity tnt = LuckyTNTMod.RH.registeredEntities.get(block.index).spawn((ServerWorld) world, pos, SpawnReason.MOB_SUMMONED);
                if(tnt == null) return;
                world.spawnEntity(tnt);
                world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.MASTER, 1.0F, 1.0F);
                world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
                if(!block.shouldRandomlyFuse) {
                    tnt.setVelocity(0, 0, 0);
                }
            }
        }
    }

    public static void primeTnt(World world, BlockPos pos, @Nullable LivingEntity igniter, LTNTBlock block) {
        if (!world.isClient()) {
            if(block.living) {
                LivingPrimedLTNT tnt = LuckyTNTMod.RH.registeredLivingEntities.get(block.index).spawn((ServerWorld) world, pos, SpawnReason.MOB_SUMMONED);
                if(tnt == null) return;
                world.spawnEntity(tnt);
                world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.MASTER, 1.0F, 1.0F);
                world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
                if(!block.shouldRandomlyFuse) {
                    tnt.setVelocity(0, 0, 0);
                }
            }
            else {
                LTNTEntity tnt = LuckyTNTMod.RH.registeredEntities.get(block.index).spawn((ServerWorld) world, pos, SpawnReason.MOB_SUMMONED);
                if(tnt == null) return;
                world.spawnEntity(tnt);
                world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.MASTER, 1.0F, 1.0F);
                world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
                if(!block.shouldRandomlyFuse) {
                    tnt.setVelocity(0, 0, 0);
                }
            }
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (world.isClient) {
            return;
        }
        primeTnt(world, pos, explosion.getCausingEntity(), this);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder lootBuilder) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if(world.isReceivingRedstonePower(pos) && !world.isClient()) {
            primeTnt(world, pos, null);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (oldState.isOf(state.getBlock())) {
            return;
        }
        if (world.isReceivingRedstonePower(pos)) {
            primeTnt(world, pos, null);
            world.removeBlock(pos, false);
        }
    }
}
