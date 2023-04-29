package com.luckytntmod.registryHelper;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.block.LTNTBlock;
import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.entity.LivingPrimedLTNT;
import com.luckytntmod.util.PrimedTNTEffect;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RegistryHelper {
    private static final String NAMESPACE = LuckyTNTMod.NAMESPACE;
    public final ArrayList<LTNTBlock> registeredBlocks = new ArrayList<>();
    public final ArrayList<EntityType<LTNTEntity>> registeredEntities = new ArrayList<>();
    public final ArrayList<EntityType<LivingPrimedLTNT>> registeredLivingEntities = new ArrayList<>();
    public static final List<Pair<LTNTBlock, Item>> TNT_DISPENSER_REGISTRY_LIST = new ArrayList<>();
    public int index = 0;
    public int livingIndex = 0;

    public LTNTBlock registerTNTBlock(String registryName, String tab) {
        LTNTBlock block = new LTNTBlock(AbstractBlock.Settings.of(Material.TNT, DyeColor.BLACK).dropsLike(Blocks.TNT).sounds(Blocks.TNT.getSoundGroup(Blocks.TNT.getDefaultState())), index, tab);
        Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, registryName), block);
        Item item = Registry.register(Registries.ITEM, new Identifier(NAMESPACE, registryName), new BlockItem(block, new FabricItemSettings()));
        registeredBlocks.add(block);
        index++;
        TNT_DISPENSER_REGISTRY_LIST.add(new Pair<>(block, item));
        return block;
    }

    public LTNTBlock registerTNTBlock(String registryName, LTNTBlock block) {
        Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, registryName), block);
        Item item = Registry.register(Registries.ITEM, new Identifier(NAMESPACE, registryName), new BlockItem(block, new FabricItemSettings()));
        registeredBlocks.add(block);
        index++;
        TNT_DISPENSER_REGISTRY_LIST.add(new Pair<>(block, item));
        return block;
    }

    public LTNTBlock registerTNTBlock(String registryName, String tab, boolean isLivingTNT) {
        LTNTBlock block = new LTNTBlock(AbstractBlock.Settings.of(Material.TNT, DyeColor.BLACK).dropsLike(Blocks.TNT).sounds(Blocks.TNT.getSoundGroup(Blocks.TNT.getDefaultState())), isLivingTNT ? livingIndex : index, tab, isLivingTNT);
        Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, registryName), block);
        Item item = Registry.register(Registries.ITEM, new Identifier(NAMESPACE, registryName), new BlockItem(block, new FabricItemSettings()));
        registeredBlocks.add(block);
        if(isLivingTNT) {
            livingIndex++;
        }
        else {
            index++;
        }
        TNT_DISPENSER_REGISTRY_LIST.add(new Pair<>(block, item));
        return block;
    }

    public LTNTBlock registerTNTBlock(String registryName, String tab, boolean isLivingTNT, boolean shouldRandomlyFuse) {
        LTNTBlock block = new LTNTBlock(AbstractBlock.Settings.of(Material.TNT, DyeColor.BLACK).dropsLike(Blocks.TNT).sounds(Blocks.TNT.getSoundGroup(Blocks.TNT.getDefaultState())), isLivingTNT ? livingIndex : index, tab, isLivingTNT, shouldRandomlyFuse);
        Registry.register(Registries.BLOCK, new Identifier(NAMESPACE, registryName), block);
        Item item = Registry.register(Registries.ITEM, new Identifier(NAMESPACE, registryName), new BlockItem(block, new FabricItemSettings()));
        registeredBlocks.add(block);
        if(isLivingTNT) {
            livingIndex++;
        }
        else {
            index++;
        }
        TNT_DISPENSER_REGISTRY_LIST.add(new Pair<>(block, item));
        return block;
    }

    public EntityType<LTNTEntity> registerTNTEntity(String registryName, Block block, PrimedTNTEffect effect) {
        EntityType<LTNTEntity> entity = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(NAMESPACE, registryName),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<LTNTEntity> type, World world) -> new LTNTEntity(type, world, block, effect)).requires().dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build()
        );
        registeredEntities.add(entity);
        return entity;
    }

    public EntityType<LExplosiveProjectile> registerExplosiveProjectile(String registryName, PrimedTNTEffect effect, float size) {
        EntityType<LExplosiveProjectile> entity = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(NAMESPACE, registryName),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<LExplosiveProjectile> type, World world) -> new LExplosiveProjectile(type, world, effect)).requires().dimensions(EntityDimensions.fixed(size, size)).build()
        );
        return entity;
    }

    public EntityType<LExplosiveProjectile> registerExplosiveProjectile(String registryName, PrimedTNTEffect effect, float size, Block block) {
        EntityType<LExplosiveProjectile> entity = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(NAMESPACE, registryName),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<LExplosiveProjectile> type, World world) -> new LExplosiveProjectile(type, world, effect, block)).requires().dimensions(EntityDimensions.fixed(size, size)).build()
        );
        return entity;
    }

    public EntityType<LivingPrimedLTNT> registerLivingTNTEntity(String registryName, EntityType<LivingPrimedLTNT> TNT) {
        EntityType<LivingPrimedLTNT> entity = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(NAMESPACE, registryName),
                TNT
                );
        registeredLivingEntities.add(entity);
        FabricDefaultAttributeRegistry.register(entity, LivingPrimedLTNT.setAttributes());
        return entity;
    }
}
