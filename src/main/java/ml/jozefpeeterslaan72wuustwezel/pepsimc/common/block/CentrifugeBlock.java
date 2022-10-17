package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import java.util.stream.Stream;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.CentrifugeEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.PepsiMcBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CentrifugeBlock extends HorizontalFacedBlock implements EntityBlock{
			

	private static final VoxelShape N = Stream.of(
			Block.box(0, 0, 0, 16, 1, 2),
			Block.box(0, 0, 14, 16, 1, 16),
			Block.box(0, 0, 2, 2, 1, 14),
			Block.box(14, 0, 2, 16, 1, 14),
			Block.box(13, 0, 13, 15, 13, 15),
			Block.box(13, 0, 1, 15, 13, 3),
			Block.box(1, 0, 1, 3, 13, 3),
			Block.box(2, 5, 2, 14, 6, 14),
			Block.box(2, 11, 2, 14, 12, 14),
			Block.box(5, 0, 7, 11, 5, 14),
			Block.box(1, 0, 13, 3, 13, 15)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	private static final VoxelShape E = Stream.of(
			Block.box(0, 0, 0, 16, 1, 2),
			Block.box(0, 0, 14, 16, 1, 16),
			Block.box(0, 0, 2, 2, 1, 14),
			Block.box(14, 0, 2, 16, 1, 14),
			Block.box(13, 0, 13, 15, 13, 15),
			Block.box(13, 0, 1, 15, 13, 3),
			Block.box(1, 0, 1, 3, 13, 3),
			Block.box(2, 5, 2, 14, 6, 14),
			Block.box(2, 11, 2, 14, 12, 14),
			Block.box(2, 0, 5, 9, 5, 11),
			Block.box(1, 0, 13, 3, 13, 15)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	private static final VoxelShape S = Stream.of(
			Block.box(0, 0, 0, 16, 1, 2),
			Block.box(0, 0, 14, 16, 1, 16),
			Block.box(0, 0, 2, 2, 1, 14),
			Block.box(14, 0, 2, 16, 1, 14),
			Block.box(13, 0, 13, 15, 13, 15),
			Block.box(13, 0, 1, 15, 13, 3),
			Block.box(1, 0, 1, 3, 13, 3),
			Block.box(2, 5, 2, 14, 6, 14),
			Block.box(2, 11, 2, 14, 12, 14),
			Block.box(5, 0, 2, 11, 5, 9),
			Block.box(1, 0, 13, 3, 13, 15)
		).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	private static final VoxelShape W = Stream.of(
			Block.box(0, 0, 0, 16, 1, 2),
			Block.box(0, 0, 14, 16, 1, 16),
			Block.box(0, 0, 2, 2, 1, 14),
			Block.box(14, 0, 2, 16, 1, 14),
			Block.box(13, 0, 13, 15, 13, 15),
			Block.box(13, 0, 1, 15, 13, 3),
			Block.box(1, 0, 1, 3, 13, 3),
			Block.box(2, 5, 2, 14, 6, 14),
			Block.box(2, 11, 2, 14, 12, 14),
			Block.box(7, 0, 5, 14, 5, 11),
			Block.box(1, 0, 13, 3, 13, 15)
		).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	
	public CentrifugeBlock() {
		super(BlockBehaviour.Properties
				.of(Material.PISTON)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.noOcclusion()
				.requiresCorrectToolForDrops());
	}
	
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState secondState, boolean what) {
	      if (!state.is(secondState.getBlock())) {
	         BlockEntity TE = level.getBlockEntity(pos);
	         if (TE instanceof CentrifugeEntity CT) {
				 Containers.dropContents(level, pos, CT.getNNLInv());
	            level.updateNeighbourForOutputSignal(pos, this);
	         }

	         super.onRemove(state, level, pos, secondState, what);
	      }
	   }

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos,
										  @NotNull Player plr, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (!world.isClientSide()) {
			BlockEntity entity = world.getBlockEntity(pos);
			if(entity instanceof CentrifugeEntity) {
				NetworkHooks.openGui(((ServerPlayer)plr), (CentrifugeEntity)entity, pos);
			} else {
				throw new IllegalStateException("Container provider is missing!");
			}
		}

		return InteractionResult.sidedSuccess(world.isClientSide());
	}


	@Override
	public @NotNull RenderShape getRenderShape(@NotNull BlockState p_60550_) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return PepsiMcBlockEntity.CENTRIFUGE_BLOCK_ENTITY.get().create(pos, state);
	}

	@Override 
	public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos p, @NotNull CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case NORTH -> N;
			case EAST -> E;
			case SOUTH -> S;
			case WEST -> W;
			default -> N;
		};
	}
	
}
