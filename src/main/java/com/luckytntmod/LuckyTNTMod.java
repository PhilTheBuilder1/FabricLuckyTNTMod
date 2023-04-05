package com.luckytntmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckyTNTMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("fabricluckytntmod");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
	}
}