package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.crops;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.NotNull;

public class SteviaCrop extends CropBlock{

	 private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),

	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
	            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

	public SteviaCrop(Properties properties) {
		super(properties);
	}


	protected ItemLike getSeedsItem() {
		return PepsiMcItem.STEVIA_SEEDS.get();
	}
	
	@Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }
	
}
