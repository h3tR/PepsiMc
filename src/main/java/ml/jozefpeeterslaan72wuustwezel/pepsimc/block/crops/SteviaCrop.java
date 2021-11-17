package ml.jozefpeeterslaan72wuustwezel.pepsimc.block.crops;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.PepsiMcItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SteviaCrop extends CropsBlock{

	 private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),

	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
	
	public SteviaCrop(Properties builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}

	protected IItemProvider getSeedsItem() {
		return PepsiMcItem.STEVIA_SEEDS.get();
	}
	
	@Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }
	
}
