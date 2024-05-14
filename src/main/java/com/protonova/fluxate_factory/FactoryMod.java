package com.protonova.fluxate_factory;

import com.protonova.fluxate_factory.block.BlockManager;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryMod implements DedicatedServerModInitializer {
	public static final String MOD_ID = "fluxate_factory";
	public static final Logger LOGGER = LoggerFactory.getLogger("Fluxate Factory");

	@Override
	public void onInitializeServer(ModContainer mod) {
		LOGGER.info("Starting Fluxate Factory");
		BlockManager.initializeBlocks();
		registerResourcePack();
		LOGGER.info("Fluxate Factory Loaded!");
	}

	private void registerResourcePack() {
		LOGGER.info("Creating resource pack...");
		PolymerResourcePackUtils.addModAssets(MOD_ID);
		LOGGER.info("Done creating resource pack!");
	}
}
