package com.luckytntmod;

import com.luckytntmod.registries.EntityRegistry;
import com.luckytntmod.renderer.LTNTRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LuckyTNTModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityRegistry.TNT_X5, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X20, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X100, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X500, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X2000, LTNTRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.TNT_X10000, LTNTRenderer::new);
	}
}