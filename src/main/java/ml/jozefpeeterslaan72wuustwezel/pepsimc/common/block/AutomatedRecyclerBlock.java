package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedRecyclerEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.RecyclerEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class AutomatedRecyclerBlock extends HorizontalFacedBlock implements EntityBlock{


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

	public AutomatedRecyclerBlock() {
		super(Properties
				.of(Material.PISTON)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.noOcclusion()
				.requiresCorrectToolForDrops());
	}
	
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState secondState, boolean p_196243_5_) {
	      if (!state.is(secondState.getBlock())) {
	         BlockEntity TE = level.getBlockEntity(pos);
	         if (TE instanceof AutomatedRecyclerEntity RT) {
				 Containers.dropContents(level, pos, RT.getNNLInv());
	            level.updateNeighbourForOutputSignal(pos, this);
	         }

	         super.onRemove(state, level, pos, secondState, p_196243_5_);
	      }
	   }

	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos,
										  @NotNull Player plr, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (!world.isClientSide()) {
			BlockEntity entity = world.getBlockEntity(pos);
			if(entity instanceof AutomatedRecyclerEntity) {
				NetworkHooks.openGui(((ServerPlayer)plr), (AutomatedRecyclerEntity)entity, pos);
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
		return PepsiMcBlockEntity.AUTOMATED_RECYCLER_BLOCK_ENTITY.get().create(pos, state);
	}

	@Override 
	public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos p, @NotNull CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST, WEST -> ShL;
			default -> ShW;
        };
	}
	
}
