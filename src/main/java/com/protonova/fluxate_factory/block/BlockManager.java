package com.protonova.fluxate_factory.block;

import com.protonova.fluxate_factory.FactoryMod;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class BlockManager {
	public static Block CONVEYOR;

	public static void initializeBlocks() {
		FactoryMod.LOGGER.info("Initializing blocks...");
		CONVEYOR = registerBlock(ConveyorBlock.BLOCK_ID, new ConveyorBlock(
			QuiltBlockSettings.create().strength(2f)
		));

		FactoryMod.LOGGER.info("Done initializing blocks!");
	}
	private static <T extends Block> T registerBlock(String id, T block) {
		Identifier identifier = new Identifier(FactoryMod.MOD_ID, id);

		FactoryMod.LOGGER.info("Registering Block: {}, With model: {}", id, String.format("block/%s", id));

		PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK,
			PolymerBlockModel.of(new Identifier(FactoryMod.MOD_ID, String.format("block/%s", id))));
		Registry.register( Registries.ITEM, identifier, new PolymerBlockItem(block,
			new QuiltItemSettings(), Items.MAGENTA_GLAZED_TERRACOTTA ));
		return Registry.register( Registries.BLOCK, identifier, block );
	}
}
