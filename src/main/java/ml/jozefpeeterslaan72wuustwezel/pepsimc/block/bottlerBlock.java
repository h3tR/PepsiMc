package ml.jozefpeeterslaan72wuustwezel.pepsimc.block;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.container.BottlerContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.tileentity.BottlerTile;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.tileentity.PepsiMcTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BottlerBlock extends Block {
	
	private static final DirectionProperty FACING = HorizontalBlock.FACING;
	
	
	private static final VoxelShape ShW = Stream.of(
			Block.box(5, 7, 10, 6, 9, 11),
			Block.box(10, 7, 10, 11, 9, 11),
			Block.box(10, 7, 5, 11, 9, 6),
			Block.box(5, 7, 5, 6, 9, 6),
			Block.box(6, 7, 11, 10, 9, 12),
			Block.box(6, 7, 4, 10, 9, 5),
			Block.box(13, 5, 6, 15, 16, 10),
			Block.box(1, 5, 6, 3, 16, 10),
			Block.box(4, 7, 6, 5, 9, 10),
			Block.box(13, 7, 10, 14, 9, 16),
			Block.box(11, 7, 6, 12, 9, 10),
			Block.box(7, 12, 7, 9, 16, 9),
			Block.box(3, 14, 6, 13, 15, 10),
			Block.box(2, 7, 10, 3, 9, 16),
			Block.box(13, 7, 0, 14, 9, 6),
			Block.box(2, 7, 0, 3, 9, 6),
			Block.box(0, 0, 0, 16, 7, 16)
			).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
	
	private static final VoxelShape ShL = Stream.of(
			Block.box(10, 7, 10, 11, 9, 11),
			Block.box(10, 7, 5, 11, 9, 6),
			Block.box(5, 7, 5, 6, 9, 6),
			Block.box(5, 7, 10, 6, 9, 11),
			Block.box(11, 7, 6, 12, 9, 10),
			Block.box(4, 7, 6, 5, 9, 10),
			Block.box(6, 5, 1, 10, 16, 3),
			Block.box(6, 5, 13, 10, 16, 15),
			Block.box(6, 7, 11, 10, 9, 12),
			Block.box(10, 7, 2, 16, 9, 3),
			Block.box(6, 7, 4, 10, 9, 5),
			Block.box(7, 12, 7, 9, 16, 9),
			Block.box(6, 14, 3, 10, 15, 13),
			Block.box(10, 7, 13, 16, 9, 14),
			Block.box(0, 7, 2, 6, 9, 3),
			Block.box(0, 7, 13, 6, 9, 14),
			Block.box(0, 0, 0, 16, 7, 16)
			).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
			
	public BottlerBlock() {
		super(AbstractBlock.Properties
				.of(Material.PISTON)
				.harvestLevel(2)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.requiresCorrectToolForDrops()
				.harvestTool(ToolType.PICKAXE));
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return PepsiMcTileEntity.BOTTLER_TILE.get().create();
	}
	  
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, World level, BlockPos pos, BlockState secondState, boolean p_196243_5_) {
	      if (!state.is(secondState.getBlock())) {
	         TileEntity tileentity = level.getBlockEntity(pos);
	         if (tileentity instanceof BottlerTile) {
		         BottlerTile bottlerTile = (BottlerTile)tileentity;
	            InventoryHelper.dropContents(level, pos, bottlerTile.getNNLInv());
	            level.updateNeighbourForOutputSignal(pos, this);
	         }

	         super.onRemove(state, level, pos, secondState, p_196243_5_);
	      }
	   }
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity plr, Hand hand, BlockRayTraceResult RT) {
		if(!world.isClientSide) {
			TileEntity TE = world.getBlockEntity(pos);
				if(!plr.isCrouching()) {
					if(TE instanceof BottlerTile) {
						INamedContainerProvider containerProvider = createContainerProvider(world, pos);
						NetworkHooks.openGui(((ServerPlayerEntity)plr), containerProvider, pos );
					} else {
						throw new IllegalStateException("Container provider is missing.");
					}
				}
		}
		return ActionResultType.SUCCESS;
	}
	
	private INamedContainerProvider createContainerProvider(World world, BlockPos pos) {
		return new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("screen.pepsimc.bottler");
			}

			@Override
			public Container createMenu(int i, PlayerInventory inv, PlayerEntity plr) {
				return new BottlerContainer(i, world, pos, inv, plr);
			}
		};  
	}
	
	@Override 
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos p, ISelectionContext context) {
		 switch (state.getValue(FACING)) {
		 	case NORTH:
		 		return ShW;
		 	case EAST:
		 		return ShL;
		 	case SOUTH:
		 		return ShW;
		 	case WEST:
		 		return ShL;
		 	default:
		 		return ShW;
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
