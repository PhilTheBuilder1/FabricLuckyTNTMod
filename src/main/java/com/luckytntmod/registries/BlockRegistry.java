package com.luckytntmod.registries;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.block.LTNTBlock;
import com.luckytntmod.block.NuclearWasteBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class BlockRegistry {

    public static void init() {
        LuckyTNTMod.LOGGER.info("Hello there");
    }

    //Normal TNT
    public static final LTNTBlock TNT_X5 = LuckyTNTMod.RH.registerTNTBlock("tnt_x5", "n");
    public static final LTNTBlock TNT_X20 = LuckyTNTMod.RH.registerTNTBlock("tnt_x20", "n");
    public static final LTNTBlock TNT_X100 = LuckyTNTMod.RH.registerTNTBlock("tnt_x100", "n");
    public static final LTNTBlock TNT_X500 = LuckyTNTMod.RH.registerTNTBlock("tnt_x500", "n");
    public static final LTNTBlock COBBLESTONE_HOUSE_TNT = LuckyTNTMod.RH.registerTNTBlock("cobblestone_house_tnt", "n");
    public static final LTNTBlock WOOD_HOUSE_TNT = LuckyTNTMod.RH.registerTNTBlock("woodhouse_tnt", "n");
    public static final LTNTBlock BRICK_HOUSE_TNT = LuckyTNTMod.RH.registerTNTBlock("brickhouse_tnt", "n");
    public static final LTNTBlock DIGGING_TNT = LuckyTNTMod.RH.registerTNTBlock("digging_tnt", "n");
    public static final LTNTBlock DRILLING_TNT = LuckyTNTMod.RH.registerTNTBlock("drilling_tnt", "n");
    public static final LTNTBlock SPHERE_TNT = LuckyTNTMod.RH.registerTNTBlock("sphere_tnt", "n");
    public static final LTNTBlock FLOATING_ISLAND = LuckyTNTMod.RH.registerTNTBlock("floating_island", "n");
    public static final LTNTBlock OCEAN_TNT = LuckyTNTMod.RH.registerTNTBlock("ocean_tnt", "n");
    public static final LTNTBlock HELLFIRE_TNT = LuckyTNTMod.RH.registerTNTBlock("hellfire_tnt", "n");
    public static final LTNTBlock FIRE_TNT = LuckyTNTMod.RH.registerTNTBlock("fire_tnt", "n");
    public static final LTNTBlock SNOW_TNT = LuckyTNTMod.RH.registerTNTBlock("snow_tnt", "n");
    public static final LTNTBlock FREEZE_TNT = LuckyTNTMod.RH.registerTNTBlock("freeze_tnt", "n");
    public static final LTNTBlock VAPORIZE_TNT = LuckyTNTMod.RH.registerTNTBlock("vaporize_tnt", "n");
    public static final LTNTBlock GRAVITY_TNT = LuckyTNTMod.RH.registerTNTBlock("gravity_tnt", "n");
    public static final LTNTBlock LIGHTNING_TNT = LuckyTNTMod.RH.registerTNTBlock("lightning_tnt", "n");
    public static final LTNTBlock CUBIC_TNT = LuckyTNTMod.RH.registerTNTBlock("cubic_tnt", "n");
    public static final LTNTBlock FLOATING_TNT = LuckyTNTMod.RH.registerTNTBlock("floating_tnt", "n");
    public static final LTNTBlock TNT_FIREWORK = LuckyTNTMod.RH.registerTNTBlock("tnt_firework", "n");
    public static final LTNTBlock SAND_FIREWORK = LuckyTNTMod.RH.registerTNTBlock("sand_firework", "n");
    public static final LTNTBlock ARROW_TNT = LuckyTNTMod.RH.registerTNTBlock("arrow_tnt", "n");
    public static final LTNTBlock TIMER_TNT = LuckyTNTMod.RH.registerTNTBlock("timer_tnt", "n");
    public static final LTNTBlock FLAT_TNT = LuckyTNTMod.RH.registerTNTBlock("flat_tnt", "n");
    public static final LTNTBlock MININGFLAT_TNT = LuckyTNTMod.RH.registerTNTBlock("miningflat_tnt", "n");
    public static final LTNTBlock COMPACT_TNT = LuckyTNTMod.RH.registerTNTBlock("compact_tnt", "n");
    public static final LTNTBlock ANIMAL_TNT = LuckyTNTMod.RH.registerTNTBlock("animal_tnt", "n");
    public static final LTNTBlock METEOR_TNT = LuckyTNTMod.RH.registerTNTBlock("meteor_tnt", "n");
    public static final LTNTBlock SPIRAL_TNT = LuckyTNTMod.RH.registerTNTBlock("spiral_tnt", "n");
    public static final LTNTBlock ERUPTING_TNT = LuckyTNTMod.RH.registerTNTBlock("erupting_tnt", "n");
    public static final LTNTBlock GROVE_TNT = LuckyTNTMod.RH.registerTNTBlock("grove_tnt", "n");
    public static final LTNTBlock ENDER_TNT = LuckyTNTMod.RH.registerTNTBlock("ender_tnt", "n");
    public static final LTNTBlock METEOR_SHOWER = LuckyTNTMod.RH.registerTNTBlock("meteor_shower", "n");
    public static final LTNTBlock INVERTED_TNT = LuckyTNTMod.RH.registerTNTBlock("inverted_tnt", "n");
    public static final LTNTBlock NUCLEAR_TNT = LuckyTNTMod.RH.registerTNTBlock("nuclear_tnt", "n");
    public static final LTNTBlock CHEMICAL_TNT = LuckyTNTMod.RH.registerTNTBlock("chemical_tnt", "n");
    public static final LTNTBlock REACTION_TNT = LuckyTNTMod.RH.registerTNTBlock("reaction_tnt", "n");
    public static final LTNTBlock EASTER_EGG = LuckyTNTMod.RH.registerTNTBlock("easter_egg", "n");
    public static final LTNTBlock DAY_TNT = LuckyTNTMod.RH.registerTNTBlock("day_tnt", "n");
    public static final LTNTBlock NIGHT_TNT = LuckyTNTMod.RH.registerTNTBlock("night_tnt", "n");
    public static final LTNTBlock VILLAGE_DEFENSE = LuckyTNTMod.RH.registerTNTBlock("village_defense", "n");
    public static final LTNTBlock ZOMBIE_APOCALYPSE = LuckyTNTMod.RH.registerTNTBlock("zombie_apocalypse", "n");
    public static final LTNTBlock SHATTERPROOF_TNT = LuckyTNTMod.RH.registerTNTBlock("shatterproof_tnt", "n");
    public static final LTNTBlock GRAVEL_FIREWORK = LuckyTNTMod.RH.registerTNTBlock("gravel_firework", "n");
    public static final LTNTBlock LAVA_OCEAN_TNT = LuckyTNTMod.RH.registerTNTBlock("lava_ocean_tnt", "n");
    public static final LTNTBlock ATTACKING_TNT = LuckyTNTMod.RH.registerTNTBlock("attacking_tnt", "n", true);
    public static final LTNTBlock WALKING_TNT = LuckyTNTMod.RH.registerTNTBlock("walking_tnt", "n", true);
    public static final LTNTBlock WOOL_TNT = LuckyTNTMod.RH.registerTNTBlock("wool_tnt", "n");
    public static final LTNTBlock SAY_GOODBYE = LuckyTNTMod.RH.registerTNTBlock("say_goodbye", "n");
    public static final LTNTBlock WITHERING_TNT = LuckyTNTMod.RH.registerTNTBlock("withering_tnt", "n");
    public static final LTNTBlock NUCLEAR_WASTE_TNT = LuckyTNTMod.RH.registerTNTBlock("nuclear_waste_tnt", "n");
    public static final LTNTBlock STATIC_TNT = LuckyTNTMod.RH.registerTNTBlock("static_tnt", "n", false, false);

    //God TNT
    public static final LTNTBlock TNT_X2000 = LuckyTNTMod.RH.registerTNTBlock("tnt_x2000", "g");
    public static final LTNTBlock TSAR_BOMB = LuckyTNTMod.RH.registerTNTBlock("tsar_bomba", "g");

    //Doomsday TNT
    public static final LTNTBlock TNT_X10000 = LuckyTNTMod.RH.registerTNTBlock("tnt_x10000", "d");
    public static final LTNTBlock HYDROGEN_BOMB = LuckyTNTMod.RH.registerTNTBlock("hydrogen_bomb", "d");

    //Other
    public static final Block NUCLEAR_WASTE = Registry.register(Registries.BLOCK, new Identifier(LuckyTNTMod.NAMESPACE, "nuclear_waste"), new NuclearWasteBlock(AbstractBlock.Settings.of(Material.SNOW_LAYER, DyeColor.GREEN).sounds(BlockSoundGroup.SLIME).luminance(state -> 8).breakInstantly().noCollision().dropsNothing().ticksRandomly()));
}
