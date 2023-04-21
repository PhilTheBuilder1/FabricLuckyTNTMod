package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class WoolTNTEffect extends PrimedTNTEffect {
    private final int strength;

    public WoolTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), strength);
        explosion.doBlockExplosion((level, pos, state, distance) -> {
            if(!state.isSolidBlock(level, pos)){
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
                level.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            else if(state.getMaterial() != Material.AIR) {
                state.getBlock().onDestroyedByExplosion(level, pos, explosion);
                if(state.getMaterial().getColor() != null) {
                    MapColor color = state.getMapColor(level, pos);

                    if(color == MapColor.WHITE || color == MapColor.TERRACOTTA_WHITE) {
                        level.setBlockState(pos, Blocks.WHITE_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.DEEPSLATE_GRAY || color == MapColor.TERRACOTTA_CYAN || color == MapColor.GRAY) {
                        level.setBlockState(pos, Blocks.GRAY_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.LIGHT_GRAY || color == MapColor.TERRACOTTA_LIGHT_GRAY || color == MapColor.STONE_GRAY) {
                        level.setBlockState(pos, Blocks.LIGHT_GRAY_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.BLACK || color == MapColor.TERRACOTTA_BLACK) {
                        level.setBlockState(pos, Blocks.BLACK_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.LIME || color == MapColor.EMERALD_GREEN) {
                        level.setBlockState(pos, Blocks.LIME_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.GREEN || color == MapColor.TERRACOTTA_LIME || color == MapColor.TERRACOTTA_GREEN || color == MapColor.LICHEN_GREEN || color == MapColor.PALE_GREEN) {
                        level.setBlockState(pos, Blocks.GREEN_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.YELLOW || color == MapColor.TERRACOTTA_YELLOW || color == MapColor.GOLD) {
                        level.setBlockState(pos, Blocks.YELLOW_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.ORANGE || color == MapColor.TERRACOTTA_ORANGE) {
                        level.setBlockState(pos, Blocks.ORANGE_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.RED || color == MapColor.TERRACOTTA_RED) {
                        level.setBlockState(pos, Blocks.RED_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.PINK || color == MapColor.TERRACOTTA_PINK) {
                        level.setBlockState(pos, Blocks.PINK_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.MAGENTA || color == MapColor.TERRACOTTA_MAGENTA) {
                        level.setBlockState(pos, Blocks.MAGENTA_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.PURPLE || color == MapColor.TERRACOTTA_PURPLE) {
                        level.setBlockState(pos, Blocks.PURPLE_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.LIGHT_BLUE || color == MapColor.TERRACOTTA_LIGHT_BLUE || color == MapColor.DIAMOND_BLUE) {
                        level.setBlockState(pos, Blocks.LIGHT_BLUE_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.CYAN || color == MapColor.DARK_AQUA) {
                        level.setBlockState(pos, Blocks.CYAN_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.BLUE || color == MapColor.TERRACOTTA_BLUE || color == MapColor.WATER_BLUE || color == MapColor.LAPIS_BLUE) {
                        level.setBlockState(pos, Blocks.BLUE_WOOL.getDefaultState(), 3);
                    }
                    else if(color == MapColor.BROWN || color == MapColor.TERRACOTTA_BROWN || color == MapColor.DIRT_BROWN || color == MapColor.TERRACOTTA_GRAY) {
                        level.setBlockState(pos, Blocks.BROWN_WOOL.getDefaultState(), 3);
                    }
                }
            }
        });
    }
}
