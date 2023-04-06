package com.luckytntmod.registryHelper;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.block.LTNTBlock;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.util.PrimedTNTEffect;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;

public class RegistryHelper {
    private static final String NAMESPACE = LuckyTNTMod.NAMESPACE;
    public final ArrayList<LTNTBlock> registeredBlocks = new ArrayList<>();
    public final ArrayList<EntityType<LTNTEntity>> registeredEntities = new ArrayList<>();

    public LTNTBlock registerTNTBlock(String registryName, int index, String tab) {
        LTNTBlock block = new LTNTBlock(AbstractBlock.Settings.of(Material.TNT, DyeColor.BLACK).dropsLike(Blocks.TNT).sounds(Blocks.TNT.getSoundGroup(Blocks.TNT.getDefaultState())), index, tab);
        Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, registryName), block);
        Registry.register(Registries.ITEM, new Identifier(NAMESPACE, registryName), new BlockItem(block, new FabricItemSettings()));
        registeredBlocks.add(block);
        return block;
    }

    public EntityType<LTNTEntity> registerTNTEntity(String registryName, LTNTBlock block, PrimedTNTEffect effect) {
        EntityType<LTNTEntity> entity = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(NAMESPACE, registryName),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<LTNTEntity> type, World world) -> new LTNTEntity(type, world, block, effect)).requires().dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build()
        );
        registeredEntities.add(entity);
        return entity;
    }
}
