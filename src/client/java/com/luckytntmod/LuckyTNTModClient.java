package com.luckytntmod;

import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.renderer.BombRenderer;
import com.luckytntmod.renderer.LTNTRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LuckyTNTModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		//TNT
		EntityRendererRegistry.register(EntityRegistry.TNT_X5, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X20, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X100, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X500, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X2000, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X10000, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.COBBLESTONE_HOUSE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.WOOD_HOUSE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.BRICK_HOUSE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.DIGGING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.DRILLING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SPHERE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.FLOATING_ISLAND, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.OCEAN_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.HELLFIRE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.FIRE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SNOW_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.FREEZE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.VAPORIZE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.GRAVITY_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.LIGHTNING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.CUBIC_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.FLOATING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_FIREWORK, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SAND_FIREWORK, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ARROW_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TIMER_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.FLAT_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.MININGFLAT_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.COMPACT_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ANIMAL_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.METEOR_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SPIRAL_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ERUPTING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.GROVE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.METEOR_SHOWER, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.INVERTED_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ENDER_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.NUCLEAR_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.CHEMICAL_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.REACTION_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.EASTER_EGG, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.DAY_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.NIGHT_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.VILLAGE_DEFENSE, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ZOMBIE_APOCALYPSE, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SHATTERPROOF_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.GRAVEL_FIREWORK, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.LAVA_OCEAN_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ATTACKING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.WALKING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.WOOL_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SAY_GOODBYE, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.WITHERING_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.NUCLEAR_WASTE_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.STATIC_TNT, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TSAR_BOMB, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.HYDROGEN_BOMB, LTNTRenderer::new);

		//Projectile
		EntityRendererRegistry.register(EntityRegistry.METEOR, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.SPIRAL_PROJECTILE, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.ERUPTING_PROJECTILE, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.MINI_METEOR, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.LITTLE_METEOR, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.CHEMICAL_PROJECTILE, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TSAR_BOMB_BOMB, BombRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.HYDROGEN_BOMB_BOMB, BombRenderer::new);
	}
}