package com.luckytntmod.entity;

import com.luckytntmod.util.PrimedTNTEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PrimedOreTNT extends LTNTEntity {
    public PrimedOreTNT(EntityType<? extends TntEntity> entityType, World world, Block block, PrimedTNTEffect effect) {
        super(entityType, world, block, effect);
    }

    public List<BlockPos> availablePos = new ArrayList<>();
}
