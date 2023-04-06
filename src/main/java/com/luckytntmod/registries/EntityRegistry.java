package com.luckytntmod.registries;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.minecraft.entity.EntityType;

public class EntityRegistry {
    public static final EntityType<LTNTEntity> TNT_X5 = LuckyTNTMod.RH.registerTNTEntity("tnt_x5", BlockRegistry.TNT_X5, new TNTXStrengthEffect().fuse(120).strength(10f).randomVecLength(1.25f).knockbackStrength(1.5f));
    public static final EntityType<LTNTEntity> TNT_X20 = LuckyTNTMod.RH.registerTNTEntity("tnt_x20", BlockRegistry.TNT_X20, new TNTXStrengthEffect().fuse(160).strength(20f).randomVecLength(1.5f).knockbackStrength(1.5f));
    public static final EntityType<LTNTEntity> TNT_X100 = LuckyTNTMod.RH.registerTNTEntity("tnt_x100", BlockRegistry.TNT_X100, new TNTXStrengthEffect().fuse(200).strength(50f).yStrength(1.3f).knockbackStrength(3f));
    public static final EntityType<LTNTEntity> TNT_X500 = LuckyTNTMod.RH.registerTNTEntity("tnt_x500", BlockRegistry.TNT_X500, new TNTXStrengthEffect().fuse(240).strength(80f).yStrength(1.3f).knockbackStrength(5f));
    public static final EntityType<LTNTEntity> TNT_X2000 = LuckyTNTMod.RH.registerTNTEntity("tnt_x2000", BlockRegistry.TNT_X2000, new TNTXStrengthEffect().fuse(400).strength(120f).xzStrength(0.6f).yStrength(0.625f).randomVecLength(0.075f).knockbackStrength(15f).resistanceImpact(0.167f).isStrongExplosion());
    public static final EntityType<LTNTEntity> TNT_X10000 = LuckyTNTMod.RH.registerTNTEntity("tnt_x10000", BlockRegistry.TNT_X10000, new TNTXStrengthEffect().fuse(480).strength(240f).xzStrength(0.6f).yStrength(0.625f).randomVecLength(0.075f).knockbackStrength(30f).resistanceImpact(0.167f).isStrongExplosion());
}
