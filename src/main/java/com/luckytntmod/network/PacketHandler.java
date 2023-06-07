package com.luckytntmod.network;

import com.luckytntmod.LuckyTNTMod;
import net.minecraft.util.Identifier;

public class PacketHandler {
    public static final String NAMESPACE = LuckyTNTMod.NAMESPACE;
    public static final Identifier TUNNELING_TNT_INIT = new Identifier(NAMESPACE, "tunneling_tnt_init");
}
