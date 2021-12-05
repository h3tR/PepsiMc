package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.RecyclerContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.RecyclerTile;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
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
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class RecyclerBlock extends Block implements EntityBlock{
			
	public RecyclerBlock() {
		super(BlockBehaviour.Properties
				.of(Material.PISTON)
				.strength(4.5f,15)
				.sound(SoundType.METAL)
				.requiresCorrectToolForDrops());
	}
	
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState secondState, boolean p_196243_5_) {
	      if (!state.is(secondState.getBlock())) {
	         BlockEntity TE = level.getBlockEntity(pos);
	         if (TE instanceof RecyclerTile) {
	        	 RecyclerTile RT = (RecyclerTile)TE;
	            Containers.dropContents(level, pos, RT.getNNLInv());
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
					if(TE instanceof RecyclerTile) {
						MenuProvider containerProvider = createContainerProvider(world, pos);
						NetworkHooks.openGui(((ServerPlayer)plr), containerProvider, pos);
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
				return new TranslatableComponent("screen.pepsimc.recycler");
			}

			@Override
			public AbstractContainerMenu createMenu(int i, Inventory inv, Player plr) {
				return new RecyclerContainer(i, world, pos, inv, plr);
			}
		};  
	}

	@Override
	public RenderShape getRenderShape(BlockState p_60550_) {
		// TODO Auto-generated method stub
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return PepsiMcBlockEntity.RECYCLER_TILE.get().create(pos, state);
	}

	
	
}
