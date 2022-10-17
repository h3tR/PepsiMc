package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.AutomatedCentrifugeEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.AutomatedProcessingBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.PepsiMcBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class AutomatedCentrifugeBlock extends HorizontalFacedBlock implements EntityBlock{

	public AutomatedCentrifugeBlock() {
		super(Properties
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
	         if (TE instanceof AutomatedCentrifugeEntity CT) {
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
			if(entity instanceof AutomatedCentrifugeEntity) {
				NetworkHooks.openGui(((ServerPlayer)plr), (AutomatedCentrifugeEntity)entity, pos);
			} else {
				throw new IllegalStateException("Container provider is missing!");
			}
		}

		return InteractionResult.sidedSuccess(world.isClientSide());
	}


	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return PepsiMcBlockEntity.AUTOMATED_CENTRIFUGE_BLOCK_ENTITY.get().create(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BlockStateProperties.ENABLED);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(BlockStateProperties.ENABLED,false);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
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
}
