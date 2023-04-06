package com.luckytntmod.registries;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.block.LTNTBlock;

public class BlockRegistry {

    public static void init() {
        LuckyTNTMod.LOGGER.info("Hello there");
    }

    public static final LTNTBlock TNT_X5 = LuckyTNTMod.RH.registerTNTBlock("tnt_x5", 0, "n");
    public static final LTNTBlock TNT_X20 = LuckyTNTMod.RH.registerTNTBlock("tnt_x20", 1, "n");
    public static final LTNTBlock TNT_X100 = LuckyTNTMod.RH.registerTNTBlock("tnt_x100", 2, "n");
    public static final LTNTBlock TNT_X500 = LuckyTNTMod.RH.registerTNTBlock("tnt_x500", 3, "n");
    public static final LTNTBlock TNT_X2000 = LuckyTNTMod.RH.registerTNTBlock("tnt_x2000", 4, "g");
    public static final LTNTBlock TNT_X10000 = LuckyTNTMod.RH.registerTNTBlock("tnt_x10000", 5, "d");
}
