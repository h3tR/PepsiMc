package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public class FacedBlock extends Block{
	
	private static final DirectionProperty FACING = HorizontalBlock.FACING;
	
	public FacedBlock(Properties prop) {
		super(prop);
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
