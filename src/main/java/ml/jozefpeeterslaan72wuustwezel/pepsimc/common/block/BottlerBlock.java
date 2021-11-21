package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import java.util.stream.Stream;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.BottlerContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.BottlerTile;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class BottlerBlock extends HorizontalFacedBlock implements EntityBlock {
	
	private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	
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
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	
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
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
			
	public BottlerBlock() {
		super(BlockBehaviour.Properties
				.of(Material.PISTON)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.requiresCorrectToolForDrops());
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState secondState, boolean p_196243_5_) {
	      if (!state.is(secondState.getBlock())) {
	         BlockEntity tileentity = level.getBlockEntity(pos);
	         if (tileentity instanceof BottlerTile) {
		         BottlerTile bottlerTile = (BottlerTile)tileentity;
	            Containers.dropContents(level, pos, bottlerTile.getNNLInv());
	            level.updateNeighbourForOutputSignal(pos, this);
	         }

	         super.onRemove(state, level, pos, secondState, p_196243_5_);
	      }
	   }
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player plr, InteractionHand hand, BlockHitResult RT) {
		if(!world.isClientSide) {
			BlockEntity TE = world.getBlockEntity(pos);
				if(!plr.isCrouching()) {
					if(TE instanceof BottlerTile) {
						MenuProvider containerProvider = createContainerProvider(world, pos);
						NetworkHooks.openGui(((ServerPlayer)plr), containerProvider, pos );
					} else {
						throw new IllegalStateException("Container provider is missing.");
					}
				}
		}
		return InteractionResult.SUCCESS;
	}
	
	private MenuProvider createContainerProvider(Level world, BlockPos pos) {
		return new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return new TranslatableComponent("screen.pepsimc.bottler");
			}

			@Override
			public AbstractContainerMenu createMenu(int i, Inventory inv, Player plr) {
				return new BottlerContainer(i, world, pos, inv, plr);
			}
		};  
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

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return PepsiMcBlockEntity.BOTTLER_TILE.get().create(pos, state);
	}
	
	
}
