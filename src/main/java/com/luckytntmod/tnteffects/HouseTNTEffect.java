package com.luckytntmod.tnteffects;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.util.IExplosiveEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class HouseTNTEffect extends PrimedTNTEffect {
    private final String house;
    private final int offX;
    private final int offZ;

    public HouseTNTEffect(String house, int offX, int offZ) {
        this.house = house;
        this.offX = offX;
        this.offZ = offZ;
    }

    @Override
    public void serverExplosion(IExplosiveEntity entity) {
        ServerWorld world = (ServerWorld) entity.world();
        StructureTemplate template = world.getStructureTemplateManager().getTemplateOrBlank(new Identifier(LuckyTNTMod.NAMESPACE, house));
        if(template != null) {
            template.place(world, new BlockPos(entity.pos()).add(offX, 0, offZ), new BlockPos(entity.pos()).add(offX, 0, offZ), new StructurePlacementData(), entity.world().random, 3);
        }
    }
}
