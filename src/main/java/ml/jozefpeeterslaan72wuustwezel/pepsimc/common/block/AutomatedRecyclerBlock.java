package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedProcessingBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedRecyclerEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.RecyclerEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class AutomatedRecyclerBlock extends HorizontalFacedBlock implements EntityBlock{


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
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return PepsiMcBlockEntity.AUTOMATED_RECYCLER_BLOCK_ENTITY.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (level.isClientSide()) {
			return null;
		} else {
			return (level1, pos, state1, tile) -> {
				if (tile instanceof AutomatedProcessingBlockEntity Machine) {
					Machine.tickServer();
				}
			};
		}
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BlockStateProperties.ENABLED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context)
				.setValue(BlockStateProperties.ENABLED,false);
	}
}
