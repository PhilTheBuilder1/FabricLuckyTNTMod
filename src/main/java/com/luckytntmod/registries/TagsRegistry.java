package com.luckytntmod.registries;

import com.luckytntmod.LuckyTNTMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagsRegistry {
    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(LuckyTNTMod.NAMESPACE, id));
    }

    public static final TagKey<Block> ORES = TagsRegistry.of("ores");
}
