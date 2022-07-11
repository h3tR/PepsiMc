package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import java.util.stream.Stream;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.RecyclerContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.RecyclerEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class RecyclerBlock extends HorizontalFacedBaseEntityBlock{
			
	private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	
		private static final VoxelShape ShW = Stream.of(
				Block.box(0, 0, 0, 1, 8, 16),
				Block.box(15, 0, 0, 16, 8, 16),
				Block.box(1, 0, 0, 15, 8, 16),
				Block.box(4, 10, 2, 7, 13, 14),
				Block.box(9, 10, 2, 12, 13, 14),
				Block.box(3, 8, 0, 13, 16, 2),
				Block.box(3, 8, 14, 13, 16, 16),
				Block.box(1, 8, 0, 3, 16, 16),
				Block.box(13, 8, 0, 15, 16, 16)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	private static final VoxelShape ShL = Stream.of(
			Block.box(0, 0, 15, 16, 8, 16),
			Block.box(0, 0, 0, 16, 8, 1),
			Block.box(0, 0, 1, 16, 8, 15),
			Block.box(2, 10, 9, 14, 13, 12),
			Block.box(2, 10, 4, 14, 13, 7),
			Block.box(0, 8, 3, 2, 16, 13),
			Block.box(14, 8, 3, 16, 16, 13),
			Block.box(0, 8, 13, 16, 16, 15),
			Block.box(0, 8, 1, 16, 16, 3)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	
	public RecyclerBlock() {
		super(BlockBehaviour.Properties
				.of(Material.PISTON)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.noOcclusion()
				.requiresCorrectToolForDrops());
	}
	
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState secondState, boolean p_196243_5_) {
	      if (!state.is(secondState.getBlock())) {
	         BlockEntity TE = level.getBlockEntity(pos);
	         if (TE instanceof RecyclerEntity) {
	        	 RecyclerEntity RT = (RecyclerEntity)TE;
	            Containers.dropContents(level, pos, RT.getNNLInv());
	            level.updateNeighbourForOutputSignal(pos, this);
	         }

	         super.onRemove(state, level, pos, secondState, p_196243_5_);
	      }
	   }
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player plr, InteractionHand hand, BlockHitResult RT) {
		if(!world.isClientSide) {
			BlockEntity Entity = world.getBlockEntity(pos);
				if(!plr.isCrouching()) {
					if(Entity instanceof RecyclerEntity) {
						NetworkHooks.openGui(((ServerPlayer)plr), (RecyclerEntity)Entity, pos);
					} else {
						throw new IllegalStateException("Container provider is missing.");
					}
				}
		}
		return InteractionResult.SUCCESS;
	}


	@Override
	public RenderShape getRenderShape(BlockState p_60550_) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new RecyclerEntity(pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos p, CollisionContext context) {
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
	
}
