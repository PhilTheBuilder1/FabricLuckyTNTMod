package com.luckytntmod.tnteffects;

import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.Material;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import org.joml.Vector3f;

import java.util.Random;

public class FarmingTNTEffect extends PrimedTNTEffect {
    private final int radius;

    public FarmingTNTEffect(int radius) {
        this.radius = radius;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ExplosionHelper.doTopBlockExplosion(entity.level(), entity.pos(), radius, (level, pos, state, distance) -> (state.isSolidBlock(level, pos) || state.isSideSolidFullSquare(level, pos, Direction.UP)) && (level.getBlockState(pos.up()).isAir() || !level.getBlockState(pos.up()).isSolidBlock(level, pos.up()) || level.getBlockState(pos.up()).isIn(BlockTags.FLOWERS)) && (state.getMaterial() != Material.LEAVES && !state.isIn(BlockTags.LOGS) && state.getBlock().getBlastResistance() < 100), (level, pos, state, distance) -> {
            if (Math.random() < 0.7f) {
                BlockState crop = Blocks.POTATOES.getDefaultState();
                int rand = new Random().nextInt(6);
                switch (rand) {
                    case 0: crop = Blocks.CARROTS.getDefaultState().with(Properties.AGE_7, new Random().nextInt(8)); break;
                    case 1: crop = Blocks.POTATOES.getDefaultState().with(Properties.AGE_7, new Random().nextInt(8)); break;
                    case 2: crop = Blocks.WHEAT.getDefaultState().with(Properties.AGE_7, new Random().nextInt(8)); break;
                    case 3: crop = Blocks.BEETROOTS.getDefaultState().with(Properties.AGE_3, new Random().nextInt(4)); break;
                    case 4: crop = Blocks.PUMPKIN_STEM.getDefaultState().with(Properties.AGE_7, new Random().nextInt(8)); break;
                    case 5: crop = Blocks.MELON_STEM.getDefaultState().with(Properties.AGE_7, new Random().nextInt(8)); break;
                }
                if (Math.random() < 0.8f) {
                    level.setBlockState(pos.down(), Blocks.FARMLAND.getDefaultState().with(Properties.MOISTURE, 7), 3);
                    level.setBlockState(pos, crop, 3);
                } else {
                    if ((level.getBlockState(pos.down().north()).isSolidBlock(level, pos.north()) || level.getBlockState(pos.north()).getBlock() instanceof FarmlandBlock)
                            && (level.getBlockState(pos.down().south()).isSolidBlock(level, pos.south()) || level.getBlockState(pos.south()).getBlock() instanceof FarmlandBlock)
                            && (level.getBlockState(pos.down().east()).isSolidBlock(level, pos.east()) || level.getBlockState(pos.east()).getBlock() instanceof FarmlandBlock)
                            && (level.getBlockState(pos.down().west()).isSolidBlock(level, pos.west()) || level.getBlockState(pos.west()).getBlock() instanceof FarmlandBlock)) {
                        level.setBlockState(pos.down(), Blocks.WATER.getDefaultState(), 3);
                    }
                }
            }
        });
    }

    @Override
    public void spawnParticles(IExplosiveEntity entity) {
        entity.level().addParticle(new DustParticleEffect(new Vector3f(1f, 0.5f, 0.1f), 1f), entity.x(), entity.y() + 1f, entity.z(), 0, 0, 0);
    }
}
