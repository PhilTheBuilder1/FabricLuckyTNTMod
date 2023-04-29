package com.luckytntmod.block;

import com.luckytntmod.registries.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrollTNTMk3Block extends LTNTBlock {
    public TrollTNTMk3Block(Settings settings, int index, String tab) {
        super(settings, index, tab);
    }

    @Override
    public void onBreak(World level, BlockPos pos, BlockState state, PlayerEntity player) {
        if(level.getBlockState(pos.up()).getBlock().getBlastResistance() < 200) {
            level.setBlockState(pos.up(), BlockRegistry.TROLL_TNT_MK3.getDefaultState(), 3);
        }
        if(level.getBlockState(pos.down()).getBlock().getBlastResistance() < 200) {
            level.setBlockState(pos.down(), BlockRegistry.TROLL_TNT_MK3.getDefaultState(), 3);
        }
        if(level.getBlockState(pos.north()).getBlock().getBlastResistance() < 200) {
            level.setBlockState(pos.north(), BlockRegistry.TROLL_TNT_MK3.getDefaultState(), 3);
        }
        if(level.getBlockState(pos.east()).getBlock().getBlastResistance() < 200) {
            level.setBlockState(pos.east(), BlockRegistry.TROLL_TNT_MK3.getDefaultState(), 3);
        }
        if(level.getBlockState(pos.south()).getBlock().getBlastResistance() < 200) {
            level.setBlockState(pos.south(), BlockRegistry.TROLL_TNT_MK3.getDefaultState(), 3);
        }
        if(level.getBlockState(pos.west()).getBlock().getBlastResistance() < 200) {
            level.setBlockState(pos.west(), BlockRegistry.TROLL_TNT_MK3.getDefaultState(), 3);
        }
        super.onBreak(level, pos, state, player);
    }
}
