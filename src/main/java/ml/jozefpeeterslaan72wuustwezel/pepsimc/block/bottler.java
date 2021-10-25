package ml.jozefpeeterslaan72wuustwezel.pepsimc.block;

import java.util.stream.Stream;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class bottler extends Block {
	
	private static final DirectionProperty FACING = HorizontalBlock.FACING;
	
	
	private static final VoxelShape ShN = Stream.of(
			Block.box(5, 7, 5, 6, 9, 6),
			Block.box(5, 7, 10, 6, 9, 11),
			Block.box(10, 7, 10, 11, 9, 11),
			Block.box(10, 7, 5, 11, 9, 6),
			Block.box(4, 7, 6, 5, 9, 10),
			Block.box(11, 7, 6, 12, 9, 10),
			Block.box(6, 7, 13, 10, 18, 15),
			Block.box(6, 7, 1, 10, 18, 3),
			Block.box(6, 7, 4, 10, 9, 5),
			Block.box(0, 7, 13, 6, 9, 14),
			Block.box(6, 7, 11, 10, 9, 12),
			Block.box(7, 15, 7, 9, 20, 9),
			Block.box(6, 16, 3, 10, 17, 13),
			Block.box(0, 7, 2, 6, 9, 3),
			Block.box(10, 7, 13, 16, 9, 14),
			Block.box(10, 7, 2, 16, 9, 3),
			Block.box(0, 0, 0, 16, 7, 16)
			).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
	
	private static final VoxelShape ShE = Stream.of(
			Block.box(5, 7, 10, 6, 9, 11),
			Block.box(10, 7, 10, 11, 9, 11),
			Block.box(10, 7, 5, 11, 9, 6),
			Block.box(5, 7, 5, 6, 9, 6),
			Block.box(6, 7, 11, 10, 9, 12),
			Block.box(6, 7, 4, 10, 9, 5),
			Block.box(13, 7, 6, 15, 18, 10),
			Block.box(1, 7, 6, 3, 18, 10),
			Block.box(4, 7, 6, 5, 9, 10),
			Block.box(13, 7, 10, 14, 9, 16),
			Block.box(11, 7, 6, 12, 9, 10),
			Block.box(7, 15, 7, 9, 20, 9),
			Block.box(3, 16, 6, 13, 17, 10),
			Block.box(2, 7, 10, 3, 9, 16),
			Block.box(13, 7, 0, 14, 9, 6),
			Block.box(2, 7, 0, 3, 9, 6),
			Block.box(0, 0, 0, 16, 7, 16)
			).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
			
	public bottler() {
		super(AbstractBlock.Properties
				.of(Material.PISTON)
				.harvestLevel(2)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.requiresCorrectToolForDrops()
				.harvestTool(ToolType.PICKAXE));
	}
	
	@Override 
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos p, ISelectionContext context) {
		 switch (state.getValue(FACING)) {
		 	case NORTH:
		 		return ShN;
		 	case EAST:
		 		return ShE;
		 	case SOUTH:
		 		return ShN;
		 	case WEST:
		 		return ShE;
		 	default:
		 		return ShN;
		 }
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
