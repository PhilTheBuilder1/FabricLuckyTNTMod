package com.luckytntmod.block;

import com.luckytntmod.registries.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrollTNTMk2Block extends LTNTBlock {

    public TrollTNTMk2Block(Settings settings, int index, String tab) {
        super(settings, index, tab);
    }

    @Override
    public ActionResult onUse(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(Items.FLINT_AND_STEEL) && !itemStack.isOf(Items.FIRE_CHARGE)) {
            return super.onUse(state, level, pos, player, hand, hit);
        } else {
            level.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            Item item = itemStack.getItem();
            if (!player.isCreative()) {
                if (itemStack.isOf(Items.FLINT_AND_STEEL)) {
                    itemStack.damage(1, player, (playerx) -> playerx.sendToolBreakStatus(hand));
                } else {
                    itemStack.decrement(1);
                }
            }
            if(level.getBlockState(pos.up()).getBlock().getBlastResistance() < 200) {
                level.setBlockState(pos.up(), BlockRegistry.TROLL_TNT_MK2.getDefaultState(), 3);
            }
            if(level.getBlockState(pos.down()).getBlock().getBlastResistance() < 200) {
                level.setBlockState(pos.down(), BlockRegistry.TROLL_TNT_MK2.getDefaultState(), 3);
            }
            if(level.getBlockState(pos.north()).getBlock().getBlastResistance() < 200) {
                level.setBlockState(pos.north(), BlockRegistry.TROLL_TNT_MK2.getDefaultState(), 3);
            }
            if(level.getBlockState(pos.east()).getBlock().getBlastResistance() < 200) {
                level.setBlockState(pos.east(), BlockRegistry.TROLL_TNT_MK2.getDefaultState(), 3);
            }
            if(level.getBlockState(pos.south()).getBlock().getBlastResistance() < 200) {
                level.setBlockState(pos.south(), BlockRegistry.TROLL_TNT_MK2.getDefaultState(), 3);
            }
            if(level.getBlockState(pos.west()).getBlock().getBlastResistance() < 200) {
                level.setBlockState(pos.west(), BlockRegistry.TROLL_TNT_MK2.getDefaultState(), 3);
            }
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(level.isClient);
        }
    }

    @Override
    public void onBreak(World level, BlockPos pos, BlockState state, PlayerEntity player) {
        LTNTBlock.primeTnt(level, pos, player);
        super.onBreak(level, pos, state, player);
    }
}
