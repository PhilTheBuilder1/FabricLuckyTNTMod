package com.luckytntmod.registries;

import com.luckytntmod.LuckyTNTMod;
import com.luckytntmod.entity.LExplosiveProjectile;
import com.luckytntmod.entity.LTNTEntity;
import com.luckytntmod.entity.LivingPrimedLTNT;
import com.luckytntmod.tnteffects.*;
import com.luckytntmod.tnteffects.projectiles.*;
import com.luckytntmod.util.TNTXStrengthEffect;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class EntityRegistry {

    //Normal TNT
    public static final EntityType<LTNTEntity> TNT_X5 = LuckyTNTMod.RH.registerTNTEntity("tnt_x5", BlockRegistry.TNT_X5, new TNTXStrengthEffect().fuse(120).strength(10f).randomVecLength(1.25f).knockbackStrength(1.5f));
    public static final EntityType<LTNTEntity> TNT_X20 = LuckyTNTMod.RH.registerTNTEntity("tnt_x20", BlockRegistry.TNT_X20, new TNTXStrengthEffect().fuse(160).strength(20f).randomVecLength(1.5f).knockbackStrength(1.5f));
    public static final EntityType<LTNTEntity> TNT_X100 = LuckyTNTMod.RH.registerTNTEntity("tnt_x100", BlockRegistry.TNT_X100, new TNTXStrengthEffect().fuse(200).strength(50f).yStrength(1.3f).knockbackStrength(3f));
    public static final EntityType<LTNTEntity> TNT_X500 = LuckyTNTMod.RH.registerTNTEntity("tnt_x500", BlockRegistry.TNT_X500, new TNTXStrengthEffect().fuse(240).strength(80f).yStrength(1.3f).knockbackStrength(5f));
    public static final EntityType<LTNTEntity> COBBLESTONE_HOUSE_TNT = LuckyTNTMod.RH.registerTNTEntity("cobblestone_house_tnt", BlockRegistry.COBBLESTONE_HOUSE_TNT, new HouseTNTEffect("cobblehouse", -5, -3));
    public static final EntityType<LTNTEntity> WOOD_HOUSE_TNT = LuckyTNTMod.RH.registerTNTEntity("woodhouse_tnt", BlockRegistry.WOOD_HOUSE_TNT, new HouseTNTEffect("woodhouse", -5, -3));
    public static final EntityType<LTNTEntity> BRICK_HOUSE_TNT = LuckyTNTMod.RH.registerTNTEntity("brickhouse_tnt", BlockRegistry.BRICK_HOUSE_TNT, new HouseTNTEffect("brickhouse", -5, -3));
    public static final EntityType<LTNTEntity> DIGGING_TNT = LuckyTNTMod.RH.registerTNTEntity("digging_tnt", BlockRegistry.DIGGING_TNT, new DiggingTNTEffect());
    public static final EntityType<LTNTEntity> DRILLING_TNT = LuckyTNTMod.RH.registerTNTEntity("drilling_tnt", BlockRegistry.DRILLING_TNT, new DrillingTNTEffect());
    public static final EntityType<LTNTEntity> SPHERE_TNT = LuckyTNTMod.RH.registerTNTEntity("sphere_tnt", BlockRegistry.SPHERE_TNT, new SphereTNTEffect(9));
    public static final EntityType<LTNTEntity> FLOATING_ISLAND = LuckyTNTMod.RH.registerTNTEntity("floating_island", BlockRegistry.FLOATING_ISLAND, new FloatingIslandEffect(20));
    public static final EntityType<LTNTEntity> OCEAN_TNT = LuckyTNTMod.RH.registerTNTEntity("ocean_tnt", BlockRegistry.OCEAN_TNT, new OceanTNTEffect(30, 10, 10));
    public static final EntityType<LTNTEntity> HELLFIRE_TNT = LuckyTNTMod.RH.registerTNTEntity("hellfire_tnt", BlockRegistry.HELLFIRE_TNT, new HellfireTNTEffect(20, 5));
    public static final EntityType<LTNTEntity> FIRE_TNT = LuckyTNTMod.RH.registerTNTEntity("fire_tnt", BlockRegistry.FIRE_TNT, new FireTNTEffect(10));
    public static final EntityType<LTNTEntity> SNOW_TNT = LuckyTNTMod.RH.registerTNTEntity("snow_tnt", BlockRegistry.SNOW_TNT, new SnowTNTEffect(10));
    public static final EntityType<LTNTEntity> FREEZE_TNT = LuckyTNTMod.RH.registerTNTEntity("freeze_tnt", BlockRegistry.FREEZE_TNT, new FreezeTNTEffect(10));
    public static final EntityType<LTNTEntity> VAPORIZE_TNT = LuckyTNTMod.RH.registerTNTEntity("vaporize_tnt", BlockRegistry.VAPORIZE_TNT, new VaporizeTNTEffect(12));
    public static final EntityType<LTNTEntity> GRAVITY_TNT = LuckyTNTMod.RH.registerTNTEntity("gravity_tnt", BlockRegistry.GRAVITY_TNT, new GravityTNTEffect());
    public static final EntityType<LTNTEntity> LIGHTNING_TNT = LuckyTNTMod.RH.registerTNTEntity("lightning_tnt", BlockRegistry.LIGHTNING_TNT, new LightningTNTEffect());
    public static final EntityType<LTNTEntity> CUBIC_TNT = LuckyTNTMod.RH.registerTNTEntity("cubic_tnt", BlockRegistry.CUBIC_TNT, new CubicTNTEffect(3));
    public static final EntityType<LTNTEntity> FLOATING_TNT = LuckyTNTMod.RH.registerTNTEntity("floating_tnt", BlockRegistry.FLOATING_TNT, new FloatingTNTEffect());
    public static final EntityType<LTNTEntity> TNT_FIREWORK = LuckyTNTMod.RH.registerTNTEntity("tnt_firework", BlockRegistry.TNT_FIREWORK, new TNTFireworkEffect());
    public static final EntityType<LTNTEntity> SAND_FIREWORK = LuckyTNTMod.RH.registerTNTEntity("sand_firework", BlockRegistry.SAND_FIREWORK, new SandFireworkEffect());
    public static final EntityType<LTNTEntity> ARROW_TNT = LuckyTNTMod.RH.registerTNTEntity("arrow_tnt", BlockRegistry.ARROW_TNT, new ArrowTNTEffect(360));
    public static final EntityType<LTNTEntity> TIMER_TNT = LuckyTNTMod.RH.registerTNTEntity("timer_tnt", BlockRegistry.TIMER_TNT, new TimerTNTEffect());
    public static final EntityType<LTNTEntity> FLAT_TNT = LuckyTNTMod.RH.registerTNTEntity("flat_tnt", BlockRegistry.FLAT_TNT, new FlatTNTEffect(18, 9));
    public static final EntityType<LTNTEntity> MININGFLAT_TNT = LuckyTNTMod.RH.registerTNTEntity("miningflat_tnt_tnt", BlockRegistry.MININGFLAT_TNT, new MiningflatTNTEffect(30, 9));
    public static final EntityType<LTNTEntity> COMPACT_TNT = LuckyTNTMod.RH.registerTNTEntity("compact_tnt", BlockRegistry.COMPACT_TNT, new CompactTNTEffect(0.05, 9, (TntBlock) Blocks.TNT));
    public static final EntityType<LTNTEntity> ANIMAL_TNT = LuckyTNTMod.RH.registerTNTEntity("animal_tnt", BlockRegistry.ANIMAL_TNT, new AnimalTNTEffect());
    public static final EntityType<LTNTEntity> METEOR_TNT = LuckyTNTMod.RH.registerTNTEntity("meteor_tnt", BlockRegistry.METEOR_TNT, new MeteorTNTEffect());
    public static final EntityType<LTNTEntity> SPIRAL_TNT = LuckyTNTMod.RH.registerTNTEntity("spiral_tnt", BlockRegistry.SPIRAL_TNT, new SpiralTNTEffect());
    public static final EntityType<LTNTEntity> ERUPTING_TNT = LuckyTNTMod.RH.registerTNTEntity("erupting_tnt", BlockRegistry.ERUPTING_TNT, new EruptingTNTEffect());
    public static final EntityType<LTNTEntity> GROVE_TNT = LuckyTNTMod.RH.registerTNTEntity("grove_tnt", BlockRegistry.GROVE_TNT, new GroveTNTEffect(20));
    public static final EntityType<LTNTEntity> ENDER_TNT = LuckyTNTMod.RH.registerTNTEntity("ender_tnt", BlockRegistry.ENDER_TNT, new EnderTNTEffect(20));
    public static final EntityType<LTNTEntity> METEOR_SHOWER = LuckyTNTMod.RH.registerTNTEntity("meteor_shower", BlockRegistry.METEOR_SHOWER, new MeteorShowerEffect());
    public static final EntityType<LTNTEntity> INVERTED_TNT = LuckyTNTMod.RH.registerTNTEntity("inverted_tnt", BlockRegistry.INVERTED_TNT, new InvertedTNTEffect());
    public static final EntityType<LTNTEntity> NUCLEAR_TNT = LuckyTNTMod.RH.registerTNTEntity("nuclear_tnt", BlockRegistry.NUCLEAR_TNT, new NuclearTNTEffect(50));
    public static final EntityType<LTNTEntity> CHEMICAL_TNT = LuckyTNTMod.RH.registerTNTEntity("chemical_tnt", BlockRegistry.CHEMICAL_TNT, new ChemicalTNTEffect());
    public static final EntityType<LTNTEntity> REACTION_TNT = LuckyTNTMod.RH.registerTNTEntity("reaction_tnt", BlockRegistry.REACTION_TNT, new ReactionTNTEffect());
    public static final EntityType<LTNTEntity> EASTER_EGG = LuckyTNTMod.RH.registerTNTEntity("easter_egg", BlockRegistry.EASTER_EGG, new EasterEggEffect());
    public static final EntityType<LTNTEntity> DAY_TNT = LuckyTNTMod.RH.registerTNTEntity("day_tnt", BlockRegistry.DAY_TNT, new DayTNTEffect());
    public static final EntityType<LTNTEntity> NIGHT_TNT = LuckyTNTMod.RH.registerTNTEntity("night_tnt", BlockRegistry.NIGHT_TNT, new NightTNTEffect());
    public static final EntityType<LTNTEntity> VILLAGE_DEFENSE = LuckyTNTMod.RH.registerTNTEntity("village_defense", BlockRegistry.VILLAGE_DEFENSE, new VillageDefenseEffect());
    public static final EntityType<LTNTEntity> ZOMBIE_APOCALYPSE = LuckyTNTMod.RH.registerTNTEntity("zombie_apocalypse", BlockRegistry.ZOMBIE_APOCALYPSE, new ZombieApocalypseEffect());
    public static final EntityType<LTNTEntity> SHATTERPROOF_TNT = LuckyTNTMod.RH.registerTNTEntity("shatterproof_tnt", BlockRegistry.SHATTERPROOF_TNT, new ShatterproofTNTEffect());
    public static final EntityType<LTNTEntity> GRAVEL_FIREWORK = LuckyTNTMod.RH.registerTNTEntity("gravel_firework", BlockRegistry.GRAVEL_FIREWORK, new GravelFireworkEffect());
    public static final EntityType<LTNTEntity> LAVA_OCEAN_TNT = LuckyTNTMod.RH.registerTNTEntity("lava_ocean_tnt", BlockRegistry.LAVA_OCEAN_TNT, new LavaOceanTNTEffect(15, 10));
    public static final EntityType<LivingPrimedLTNT> ATTACKING_TNT = LuckyTNTMod.RH.registerLivingTNTEntity("attacking_tnt", FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<LivingPrimedLTNT> type, World level) -> new LivingPrimedLTNT(type, level, BlockRegistry.ATTACKING_TNT, new TNTXStrengthEffect().fuse(400)) {
        @Override
        public void initGoals() {
            this.goalSelector.add(1, new MeleeAttackGoal(this, 1, false));
            this.goalSelector.add(2, new LookAroundGoal(this));
            this.goalSelector.add(3, new WanderAroundGoal(this, 1));
            this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        }
    }).fireImmune().dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build());
    public static final EntityType<LivingPrimedLTNT> WALKING_TNT = LuckyTNTMod.RH.registerLivingTNTEntity("walking_tnt", FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType<LivingPrimedLTNT> type, World level) -> new LivingPrimedLTNT(type, level, BlockRegistry.WALKING_TNT, new TNTXStrengthEffect().fuse(400)) {
        @Override
        public void initGoals() {
            this.goalSelector.add(1, new WanderAroundGoal(this, 1));
            this.goalSelector.add(2, new LookAroundGoal(this));
            this.goalSelector.add(3, new FlyGoal(this, 1));
        }
    }).fireImmune().dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build());
    public static final EntityType<LTNTEntity> WOOL_TNT = LuckyTNTMod.RH.registerTNTEntity("wool_tnt", BlockRegistry.WOOL_TNT, new WoolTNTEffect(40));
    public static final EntityType<LTNTEntity> SAY_GOODBYE = LuckyTNTMod.RH.registerTNTEntity("say_goodbye", BlockRegistry.SAY_GOODBYE, new SayGoodbyeEffect());
    //Unimplemented Miner TNT
    public static final EntityType<LTNTEntity> WITHERING_TNT = LuckyTNTMod.RH.registerTNTEntity("withering_tnt", BlockRegistry.WITHERING_TNT, new WitheringTNTEffect(20));
    public static final EntityType<LTNTEntity> NUCLEAR_WASTE_TNT = LuckyTNTMod.RH.registerTNTEntity("nuclear_waste_tnt", BlockRegistry.NUCLEAR_WASTE_TNT, new NuclearWasteTNTEffect(15));
    public static final EntityType<LTNTEntity> STATIC_TNT = LuckyTNTMod.RH.registerTNTEntity("static_tnt", BlockRegistry.STATIC_TNT, new StaticTNTEffect());
    //Unimplemented Pumpkin Bomb TNT
    //public static final EntityType<LTNTEntity> SMOKE_TNT = LuckyTNTMod.RH.registerTNTEntity("smoke_tnt", BlockRegistry.SMOKE_TNT, new SmokeTNTEffect());
    public static final EntityType<LTNTEntity> TROLL_TNT = LuckyTNTMod.RH.registerTNTEntity("troll_tnt", Blocks.TNT, new TrollTNTEffect());
    public static final EntityType<LTNTEntity> TROLL_TNT_MK2 = LuckyTNTMod.RH.registerTNTEntity("troll_tnt_mk2", Blocks.TNT, new TrollTNTEffect());
    public static final EntityType<LTNTEntity> TROLL_TNT_MK3 = LuckyTNTMod.RH.registerTNTEntity("troll_tnt_mk3", Blocks.TNT, new TrollTNTMk3Effect());
    public static final EntityType<LTNTEntity> CLUSTER_BOMB_TNT = LuckyTNTMod.RH.registerTNTEntity("cluster_bomb_tnt", BlockRegistry.CLUSTER_BOMB_TNT, new ClusterBombTNTEffect());
    public static final EntityType<LTNTEntity> AIR_STRIKE = LuckyTNTMod.RH.registerTNTEntity("air_strike", BlockRegistry.AIR_STRIKE, new AirStrikeEffect());
    public static final EntityType<LTNTEntity> SPAMMING_TNT = LuckyTNTMod.RH.registerTNTEntity("spamming_tnt", BlockRegistry.SPAMMING_TNT, new SpammingTNTEffect());
    public static final EntityType<LTNTEntity> BOUNCING_TNT = LuckyTNTMod.RH.registerTNTEntity("bouncing_tnt", BlockRegistry.BOUNCING_TNT, new BouncingTNTEffect());
    public static final EntityType<LTNTEntity> ROULETTE_TNT = LuckyTNTMod.RH.registerTNTEntity("roulette_tnt", BlockRegistry.ROULETTE_TNT, new RouletteTNTEffect(10));
    public static final EntityType<LTNTEntity> SENSOR_TNT = LuckyTNTMod.RH.registerTNTEntity("sensor_tnt", BlockRegistry.SENSOR_TNT, new SensorTNTEffect());
    public static final EntityType<LTNTEntity> RAINBOW_FIREWORK = LuckyTNTMod.RH.registerTNTEntity("rainbow_firework", BlockRegistry.RAINBOW_FIREWORK, new RainbowFireworkEffect());
    public static final EntityType<LTNTEntity> XRAY_TNT = LuckyTNTMod.RH.registerTNTEntity("xray_tnt", BlockRegistry.XRAY_TNT, new XRayTNTEffect(40));
    public static final EntityType<LTNTEntity> FARMING_TNT = LuckyTNTMod.RH.registerTNTEntity("farming_tnt", BlockRegistry.FARMING_TNT, new FarmingTNTEffect(10));
    public static final EntityType<LTNTEntity> PHANTOM_TNT = LuckyTNTMod.RH.registerTNTEntity("phantom_tnt", BlockRegistry.PHANTOM_TNT, new PhantomTNTEffect());
    public static final EntityType<LTNTEntity> SWAP_TNT = LuckyTNTMod.RH.registerTNTEntity("swap_tnt", BlockRegistry.SWAP_TNT, new SwapTNTEffect());
    public static final EntityType<LTNTEntity> IGNITER_TNT = LuckyTNTMod.RH.registerTNTEntity("igniter_tnt", BlockRegistry.IGNITER_TNT, new IgniterTNTEffect(12));
    public static final EntityType<LTNTEntity> MULTIPLYING_TNT = LuckyTNTMod.RH.registerTNTEntity("multiplying_tnt", BlockRegistry.MULTIPLYING_TNT, new MultiplyingTNTEffect());
    public static final EntityType<LTNTEntity> BUTTER_TNT = LuckyTNTMod.RH.registerTNTEntity("butter_tnt", BlockRegistry.BUTTER_TNT, new ButterTNTEffect());
    public static final EntityType<LTNTEntity> TUNNELING_TNT = LuckyTNTMod.RH.registerTNTEntity("tunneling_tnt", BlockRegistry.TUNNELING_TNT, new TunnelingTNTEffect());

    //God TNT
    public static final EntityType<LTNTEntity> TNT_X2000 = LuckyTNTMod.RH.registerTNTEntity("tnt_x2000", BlockRegistry.TNT_X2000, new TNTXStrengthEffect().fuse(1).strength(160f).randomVecLength(0.05f).knockbackStrength(15f).resistanceImpact(0.167f).isStrongExplosion());
    //public static final EntityType<LTNTEntity> TNT_2_X2000 = LuckyTNTMod.RH.registerTNTEntity("tnt_2_x2000", BlockRegistry.TNT_2_X2000, new TNTXStrengthEffect().fuse(1).strength(160f).randomVecLength(0.05f).knockbackStrength(15f).resistanceImpact(0.167f).isStrongExplosion().experiment(3));
    public static final EntityType<LTNTEntity> TSAR_BOMB = LuckyTNTMod.RH.registerTNTEntity("tsar_bomba", Blocks.AIR, new TsarBombEffect());
    public static final EntityType<LTNTEntity> EYE_OF_THE_SAHARA = LuckyTNTMod.RH.registerTNTEntity("eye_of_the_sahara", BlockRegistry.EYE_OF_THE_SAHARA, new EyeOfTheSaharaEffect());
    public static final EntityType<LTNTEntity> GLOBAL_DISASTER = LuckyTNTMod.RH.registerTNTEntity("global_disaster", BlockRegistry.GLOBAL_DISASTER, new GlobalDisasterEffect());
    public static final EntityType<LTNTEntity> TETRAHEDRON_TNT = LuckyTNTMod.RH.registerTNTEntity("tetrahedron_tnt", BlockRegistry.TETRAHEDRON_TNT, new TetrahedronTNTEffect());

    //Doomsday TNT
    public static final EntityType<LTNTEntity> TNT_X10000 = LuckyTNTMod.RH.registerTNTEntity("tnt_x10000", BlockRegistry.TNT_X10000, new TNTXStrengthEffect().fuse(480).strength(360f).randomVecLength(0.05f).knockbackStrength(30f).resistanceImpact(0.167f).isStrongExplosion());
    public static final EntityType<LTNTEntity> HYDROGEN_BOMB = LuckyTNTMod.RH.registerTNTEntity("hydrogen_bomb", Blocks.AIR, new HydrogenBombEffect());
    public static final EntityType<LTNTEntity> ASTEROID_BELT = LuckyTNTMod.RH.registerTNTEntity("asteroid_belt", BlockRegistry.ASTEROID_BELT, new AsteroidBeltEffect());


    //Explosive Projectiles
    public static final EntityType<LExplosiveProjectile> METEOR = LuckyTNTMod.RH.registerExplosiveProjectile("meteor", new MeteorEffect(53, 2f), 2f, Blocks.MAGMA_BLOCK);
    public static final EntityType<LExplosiveProjectile> SPIRAL_PROJECTILE = LuckyTNTMod.RH.registerExplosiveProjectile("spiral_projectile", new SpiralTNTEffect(), 1f, BlockRegistry.SPIRAL_TNT);
    public static final EntityType<LExplosiveProjectile> ERUPTING_PROJECTILE = LuckyTNTMod.RH.registerExplosiveProjectile("erupting_projectile", new EruptingTNTEffect(), 1f, BlockRegistry.ERUPTING_TNT);
    public static final EntityType<LExplosiveProjectile> LITTLE_METEOR = LuckyTNTMod.RH.registerExplosiveProjectile("little_meteor", new MeteorEffect(20, 1.5f), 1.5f, Blocks.MAGMA_BLOCK);
    public static final EntityType<LExplosiveProjectile> MINI_METEOR = LuckyTNTMod.RH.registerExplosiveProjectile("mini_meteor", new MiniMeteorEffect(), 1f, Blocks.MAGMA_BLOCK);
    public static final EntityType<LExplosiveProjectile> CHEMICAL_PROJECTILE = LuckyTNTMod.RH.registerExplosiveProjectile("chemical_projectile", new ChemicalTNTEffect(), 1f, Blocks.AIR);
    public static final EntityType<LExplosiveProjectile> TSAR_BOMB_BOMB = LuckyTNTMod.RH.registerExplosiveProjectile("tsar_bomba_bomb", new TsarBombBombEffect(), 1.2f);
    public static final EntityType<LExplosiveProjectile> HYDROGEN_BOMB_BOMB = LuckyTNTMod.RH.registerExplosiveProjectile("hydrogen_bomb_bomb", new HydrogenBombBombEffect(), 1.4f);
    public static final EntityType<LExplosiveProjectile> CLUSTER_BOMB = LuckyTNTMod.RH.registerExplosiveProjectile("cluster_bomb", new ClusterBombEffect(), 1);
    public static final EntityType<LExplosiveProjectile> SHRAPNEL = LuckyTNTMod.RH.registerExplosiveProjectile("shrapnel", new ShrapnelEffect(), 0.25f, Blocks.BLACKSTONE);
    public static final EntityType<LExplosiveProjectile> BOMB = LuckyTNTMod.RH.registerExplosiveProjectile("bomb", new BombEffect(), 1);

    public static Predicate<LivingEntity> PREDICATE = living -> living instanceof PlayerEntity player && !player.isCreative() && !player.isSpectator();
}
