package com.protonova.fluxate_factory.block;

import com.mojang.serialization.MapCodec;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ConveyorBlock extends HorizontalFacingBlock implements PolymerTexturedBlock {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final String BLOCK_ID = "conveyor";
	public ConveyorBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<ConveyorBlock> getCodec() {
		return null;
	}

	@Override
	public Block getPolymerBlock(BlockState state) {
		return Blocks.MAGENTA_GLAZED_TERRACOTTA;
	}

	@Override
	public BlockState getPolymerBlockState(BlockState state) {
		return Blocks.MAGENTA_GLAZED_TERRACOTTA.getDefaultState()
			.with(Properties.HORIZONTAL_FACING, state.get(FACING));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			player.sendMessage(Text.literal(String.format("The current block state is %s",
				world.getBlockState(hit.getBlockPos()).get(Properties.HORIZONTAL_FACING))), true);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx)
			.with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
