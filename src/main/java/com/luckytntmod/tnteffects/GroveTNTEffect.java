package com.luckytntmod.tnteffects;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.util.Explosions.ImprovedExplosion;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;

public class GroveTNTEffect extends PrimedTNTEffect {

    private final int strength;

    public GroveTNTEffect(int strength) {
        this.strength = strength;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ImprovedExplosion explosion = new ImprovedExplosion(entity.world(), entity.pos(), strength);
        explosion.doBlockExplosion((level, pos, state, distance) -> {
            if(state.isSideSolidFullSquare(level, pos, Direction.UP) && state.getBlock().getBlastResistance() < 100 && !state.isAir() && (level.getBlockState(pos.up()).isAir() || level.getBlockState(pos.up()).getBlock().getHardness() <= 0.2f)) {
                level.setBlockState(pos, Blocks.GRASS_BLOCK.getDefaultState());
                if(Math.random() < 0.2f) {
                    int random = level.random.nextInt(6);
                    String string = switch (random) {
                        case 0 -> "acaciatree";
                        case 1 -> "sprucetree";
                        case 2 -> "oaktree";
                        case 3 -> "darkoaktree";
                        case 4 -> "birchtree";
                        case 5 -> "jungletree";
                        default -> "";
                    };
                    StructureTemplate template = ((ServerWorld) entity.world()).getStructureTemplateManager().getTemplateOrBlank(new Identifier(LuckyTNTMod.NAMESPACE, string));
                    if(template != null) {
                        template.place((ServerWorldAccess) entity.world(), pos.add(-1, 0, -1), pos.add(-1, 0, -1), new StructurePlacementData(), entity.world().random, 3);
                    }
                }
            }
        });
    }
}
