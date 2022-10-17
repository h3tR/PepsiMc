package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import java.util.stream.Stream;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.FlavorMachineEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.PepsiMcBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
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

public class FlavorMachineBlock extends HorizontalFacedBlock implements EntityBlock{
			

		private static final VoxelShape N = Stream.of(
				Block.box(4, 0, 0, 12, 3, 3),
				Block.box(3, 10, 0, 13, 15, 3),
				Block.box(4, 15, 2, 12, 15.5, 15),
				Block.box(6, 15.5, 7, 10, 16, 11),
				Block.box(7, 7.5, 0.5, 9, 10, 2.5),
				Block.box(3, 0, 3, 13, 15, 16)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
		private static final VoxelShape E = Stream.of(
				Block.box(13, 0, 4, 16, 3, 12),
				Block.box(13, 10, 3, 16, 15, 13),
				Block.box(1, 15, 4, 14, 15.5, 12),
				Block.box(5, 15.5, 6, 9, 16, 10),
				Block.box(13.5, 7.5, 7, 14.5, 10, 9),
				Block.box(0, 0, 3, 13, 15, 13)
				).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
		private static final VoxelShape S = Stream.of(
				Block.box(4, 0, 13, 12, 3, 16),
				Block.box(3, 10, 13, 13, 15, 16),
				Block.box(4, 15, 1, 12, 15.5, 14),
				Block.box(6, 15.5, 5, 10, 16, 9),
				Block.box(7, 7.5, 13.5, 9, 10, 15.5),
				Block.box(3, 0, 0, 13, 15, 13)
				).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
		private static final VoxelShape W = Stream.of(
				Block.box(0, 0, 4, 3, 3, 12),
				Block.box(0, 10, 3, 3, 15, 13),
				Block.box(2, 15, 4, 15, 15.5, 12),
				Block.box(7, 15.5, 6, 11, 16, 10),
				Block.box(0.5, 7.5, 7, 2.5, 10, 9),
				Block.box(3, 0, 3, 16, 15, 13)
				).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	
	public FlavorMachineBlock() {
		super(BlockBehaviour.Properties
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
	         if (TE instanceof FlavorMachineEntity FB) {
				 Containers.dropContents(level, pos, FB.getNNLInv());
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
			if(entity instanceof FlavorMachineEntity) {
				NetworkHooks.openGui(((ServerPlayer)plr), (FlavorMachineEntity)entity, pos);
			} else {
				throw new IllegalStateException("Container provider is missing!");
			}
		}

		return InteractionResult.sidedSuccess(world.isClientSide());
	}


	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return PepsiMcBlockEntity.FLAVOR_MACHINE_BLOCK_ENTITY.get().create(pos, state);
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
