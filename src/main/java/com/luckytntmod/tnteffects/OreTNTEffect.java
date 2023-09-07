package com.luckytntmod.tnteffects;

import com.luckytntmod.entity.PrimedOreTNT;
import com.luckytntmod.registries.TagsRegistry;
import com.luckytntmod.util.Explosions.ExplosionHelper;
import com.luckytntmod.util.Explosions.IForEachBlockExplosionEffect;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class OreTNTEffect extends PrimedTNTEffect {
    @SuppressWarnings("resource")
    @Override
    public void explosionTick(IExplosiveEntity entity) {
        if(!entity.level().isClient()) {
            if(entity instanceof PrimedOreTNT tnt) {
                if(tnt.availablePos.isEmpty()) {
                    fillAvailablePos(tnt);
                }
                Block block = Blocks.COAL_ORE;
                int random = new Random().nextInt(18);
                switch(random) {
                    case 0: block = Blocks.COAL_ORE; break;
                    case 1: block = Blocks.IRON_ORE; break;
                    case 2: block = Blocks.GOLD_ORE; break;
                    case 3: block = Blocks.COPPER_ORE; break;
                    case 4: block = Blocks.DIAMOND_ORE; break;
                    case 5: block = Blocks.EMERALD_ORE; break;
                    case 6: block = Blocks.NETHER_QUARTZ_ORE; break;
                    case 7: block = Blocks.NETHER_GOLD_ORE; break;
                    case 8: block = Blocks.LAPIS_ORE; break;
                    case 9: block = Blocks.REDSTONE_ORE; break;
                    case 10: block = Blocks.DEEPSLATE_COAL_ORE; break;
                    case 11: block = Blocks.DEEPSLATE_IRON_ORE; break;
                    case 12: block = Blocks.DEEPSLATE_COPPER_ORE; break;
                    case 13: block = Blocks.DEEPSLATE_GOLD_ORE; break;
                    case 14: block = Blocks.DEEPSLATE_EMERALD_ORE; break;
                    case 15: block = Blocks.DEEPSLATE_DIAMOND_ORE; break;
                    case 16: block = Blocks.DEEPSLATE_LAPIS_ORE; break;
                    case 17: block = Blocks.DEEPSLATE_REDSTONE_ORE; break;
                    /*case 18: block = BlockRegistry.GUNPOWDER_ORE.get(); break;
                    case 19: block = BlockRegistry.DEEPSLATE_GUNPOWDER_ORE.get(); break;
                    case 20: block = BlockRegistry.URANIUM_ORE.get(); break;
                    case 21: block = BlockRegistry.DEEPSLATE_URANIUM_ORE.get(); break;*/
                }
                for (int count = 0; count < 5; count++) {
                    if(tnt.availablePos.size() > 0) {
                        int rand = new Random().nextInt(tnt.availablePos.size());
                        if(tnt.availablePos.get(rand) != null) {
                            BlockPos pos = tnt.availablePos.get(rand);
                            tnt.availablePos.remove(rand);
                            entity.level().setBlockState(pos, block.getDefaultState());
                            entity.level().playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                        }
                    }
                }
            }
            /*else if(entity instanceof OreTNTMinecart tnt) {
                if(tnt.availablePos.isEmpty()) {
                    fillAvailablePos(tnt);
                }
                Block block = Blocks.COAL_ORE;
                int random = new Random().nextInt(22);
                switch(random) {
                    case 0: block = Blocks.COAL_ORE; break;
                    case 1: block = Blocks.IRON_ORE; break;
                    case 2: block = Blocks.GOLD_ORE; break;
                    case 3: block = Blocks.COPPER_ORE; break;
                    case 4: block = Blocks.DIAMOND_ORE; break;
                    case 5: block = Blocks.EMERALD_ORE; break;
                    case 6: block = Blocks.NETHER_QUARTZ_ORE; break;
                    case 7: block = Blocks.NETHER_GOLD_ORE; break;
                    case 8: block = Blocks.LAPIS_ORE; break;
                    case 9: block = Blocks.REDSTONE_ORE; break;
                    case 10: block = Blocks.DEEPSLATE_COAL_ORE; break;
                    case 11: block = Blocks.DEEPSLATE_IRON_ORE; break;
                    case 12: block = Blocks.DEEPSLATE_COPPER_ORE; break;
                    case 13: block = Blocks.DEEPSLATE_GOLD_ORE; break;
                    case 14: block = Blocks.DEEPSLATE_EMERALD_ORE; break;
                    case 15: block = Blocks.DEEPSLATE_DIAMOND_ORE; break;
                    case 16: block = Blocks.DEEPSLATE_LAPIS_ORE; break;
                    case 17: block = Blocks.DEEPSLATE_REDSTONE_ORE; break;
                    case 18: block = BlockRegistry.GUNPOWDER_ORE.get(); break;
                    case 19: block = BlockRegistry.DEEPSLATE_GUNPOWDER_ORE.get(); break;
                    case 20: block = BlockRegistry.URANIUM_ORE.get(); break;
                    case 21: block = BlockRegistry.DEEPSLATE_URANIUM_ORE.get(); break;
                }
                for (int count = 0; count < 5; count++) {
                    if(tnt.availablePos.size() > 0) {
                        int rand = new Random().nextInt(tnt.availablePos.size());
                        if(tnt.availablePos.get(rand) != null) {
                            BlockPos pos = tnt.availablePos.get(rand);
                            tnt.availablePos.remove(rand);
                            entity.level().setBlockAndUpdate(pos, block.defaultBlockState());
                            entity.level().playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1f, 1f);
                        }
                    }
                }
            }*/
        }
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        if(entity instanceof PrimedOreTNT tnt) {
            if(tnt.availablePos.isEmpty()) {
                fillAvailablePos(tnt);
            }
            for (int count = 0; count < 750; count++) {
                Block block = Blocks.COAL_ORE;
                int random = new Random().nextInt(22);
                switch(random) {
                    case 0: block = Blocks.COAL_ORE; break;
                    case 1: block = Blocks.IRON_ORE; break;
                    case 2: block = Blocks.GOLD_ORE; break;
                    case 3: block = Blocks.COPPER_ORE; break;
                    case 4: block = Blocks.DIAMOND_ORE; break;
                    case 5: block = Blocks.EMERALD_ORE; break;
                    case 6: block = Blocks.NETHER_QUARTZ_ORE; break;
                    case 7: block = Blocks.NETHER_GOLD_ORE; break;
                    case 8: block = Blocks.LAPIS_ORE; break;
                    case 9: block = Blocks.REDSTONE_ORE; break;
                    case 10: block = Blocks.DEEPSLATE_COAL_ORE; break;
                    case 11: block = Blocks.DEEPSLATE_IRON_ORE; break;
                    case 12: block = Blocks.DEEPSLATE_COPPER_ORE; break;
                    case 13: block = Blocks.DEEPSLATE_GOLD_ORE; break;
                    case 14: block = Blocks.DEEPSLATE_EMERALD_ORE; break;
                    case 15: block = Blocks.DEEPSLATE_DIAMOND_ORE; break;
                    case 16: block = Blocks.DEEPSLATE_LAPIS_ORE; break;
                    case 17: block = Blocks.DEEPSLATE_REDSTONE_ORE; break;
                    /*case 18: block = BlockRegistry.GUNPOWDER_ORE.get(); break;
                    case 19: block = BlockRegistry.DEEPSLATE_GUNPOWDER_ORE.get(); break;
                    case 20: block = BlockRegistry.URANIUM_ORE.get(); break;
                    case 21: block = BlockRegistry.DEEPSLATE_URANIUM_ORE.get(); break;*/
                }
                if(tnt.availablePos.size() > 0) {
                    int rand = new Random().nextInt(tnt.availablePos.size());
                    if(tnt.availablePos.get(rand) != null) {
                        BlockPos pos = tnt.availablePos.get(rand);
                        tnt.availablePos.remove(rand);
                        entity.level().setBlockState(pos, block.getDefaultState());
                        entity.level().playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                    }
                }
            }
        }
        /*else if(entity instanceof OreTNTMinecart tnt) {
            if(tnt.availablePos.isEmpty()) {
                fillAvailablePos(tnt);
            }
            for (int count = 0; count < 750; count++) {
                Block block = Blocks.COAL_ORE;
                int random = new Random().nextInt(22);
                switch(random) {
                    case 0: block = Blocks.COAL_ORE; break;
                    case 1: block = Blocks.IRON_ORE; break;
                    case 2: block = Blocks.GOLD_ORE; break;
                    case 3: block = Blocks.COPPER_ORE; break;
                    case 4: block = Blocks.DIAMOND_ORE; break;
                    case 5: block = Blocks.EMERALD_ORE; break;
                    case 6: block = Blocks.NETHER_QUARTZ_ORE; break;
                    case 7: block = Blocks.NETHER_GOLD_ORE; break;
                    case 8: block = Blocks.LAPIS_ORE; break;
                    case 9: block = Blocks.REDSTONE_ORE; break;
                    case 10: block = Blocks.DEEPSLATE_COAL_ORE; break;
                    case 11: block = Blocks.DEEPSLATE_IRON_ORE; break;
                    case 12: block = Blocks.DEEPSLATE_COPPER_ORE; break;
                    case 13: block = Blocks.DEEPSLATE_GOLD_ORE; break;
                    case 14: block = Blocks.DEEPSLATE_EMERALD_ORE; break;
                    case 15: block = Blocks.DEEPSLATE_DIAMOND_ORE; break;
                    case 16: block = Blocks.DEEPSLATE_LAPIS_ORE; break;
                    case 17: block = Blocks.DEEPSLATE_REDSTONE_ORE; break;
                    case 18: block = BlockRegistry.GUNPOWDER_ORE.get(); break;
                    case 19: block = BlockRegistry.DEEPSLATE_GUNPOWDER_ORE.get(); break;
                    case 20: block = BlockRegistry.URANIUM_ORE.get(); break;
                    case 21: block = BlockRegistry.DEEPSLATE_URANIUM_ORE.get(); break;
                }
                if(tnt.availablePos.size() > 0) {
                    int rand = new Random().nextInt(tnt.availablePos.size());
                    if(tnt.availablePos.get(rand) != null) {
                        BlockPos pos = tnt.availablePos.get(rand);
                        tnt.availablePos.remove(rand);
                        entity.level().setBlockAndUpdate(pos, block.defaultBlockState());
                        entity.level().playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1f, 1f);
                    }
                }
            }
        }*/
    }

    public void fillAvailablePos(PrimedOreTNT tnt) {
        ExplosionHelper.doSphericalExplosion(tnt.level(), tnt.getPos(), 12, (level, pos, state, distance) -> {
            if (!state.isAir() && state.getBlock().getBlastResistance() < 100 && state.isSolidBlock(level, pos) && !state.isIn(TagsRegistry.ORES)) {
                tnt.availablePos.add(pos);
            }
        });
    }

    /*public void fillAvailablePos(OreTNTMinecart tnt) {
        ExplosionHelper.doSphericalExplosion(tnt.level(), tnt.getPos(), 12, new IForEachBlockExplosionEffect() {
            @Override
            public void doBlockExplosion(Level level, BlockPos pos, BlockState state, double distance) {
                if (!state.isAir() && state.getExplosionResistance(level, pos, ImprovedExplosion.dummyExplosion(tnt.level())) < 100 && state.isCollisionShapeFullBlock(level, pos) && !state.is(Tags.Blocks.ORES)) {
                    tnt.availablePos.add(pos);
                }
            }
        });
    }*/
}
