package ml.jozefpeeterslaan72wuustwezel.pepsimc.block;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.ToolType;

public class PepsiteBlock extends Block {
	
	private static final DirectionProperty FACING = HorizontalBlock.FACING;
	
	public PepsiteBlock() {
		super(AbstractBlock.Properties.of(Material.HEAVY_METAL)
				.harvestLevel(2)
				.strength(5, 1200)
				.sound(SoundType.METAL)
				.requiresCorrectToolForDrops()
				.harvestTool(ToolType.PICKAXE));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
	 return state.setValue(FACING, rot.rotate(state.getValue(FACING)));	
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirror)
	{
	 return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
	
	@Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
	  builder.add(FACING);
	}
}
