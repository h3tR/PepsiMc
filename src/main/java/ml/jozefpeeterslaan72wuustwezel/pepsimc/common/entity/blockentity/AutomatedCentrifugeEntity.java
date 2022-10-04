package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedCentrifugeMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.configuration.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class AutomatedCentrifugeEntity extends AutomatedProcessingBlockEntity implements MenuProvider {
	public AutomatedCentrifugeEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.AUTOMATED_CENTRIFUGE_BLOCK_ENTITY.get(), pos, state, CommonConfig.CENTRIFUGE_FE_STORAGE.get(), CommonConfig.CENTRIFUGE_CONDUCTIVITY.get(),CommonConfig.CENTRIFUGE_FE_USAGE_PER_TICK.get());
	}


	@Override
	protected Optional<CentrifugeRecipe> getRecipe() {
		return this.getLevel().getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, getSimpleInv(),this.getLevel());
	}

	@Override
	protected void finishProduct() {
		Optional<CentrifugeRecipe> recipe = getRecipe();

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.insertItem(1, iRecipe.getResultItem(), false);
			itemHandler.insertItem(2, iRecipe.getByproductItem(), false);
			setChanged();
		});
	}

	@Override
	protected int getOutputSlot() {
		return 1;
	}

	@Override
	protected int getByProductSlot() {
		return -1;
	}

	@Override
	public void tickServer() {
		super.tickServer();
		level.setBlock(worldPosition,getBlockState().setValue(BlockStateProperties.ENABLED,isActive()), Block.UPDATE_ALL);
		energyStorage.consumeEnergy(CommonConfig.CENTRIFUGE_FE_USAGE_PER_TICK.get());

	}

	@Override
	protected ItemStackHandler createHandler() {

		return new ItemStackHandler(3) {
			
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}

			@Override 
			public int getSlotLimit(int slot){
				return 64;	
			}
			

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				return switch (slot) {
					case 0 -> true;
					case 1 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.EXTRACTED);
					case 2 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.EXTRACTION_BYPRODUCT);
					default -> false;
				};
			}
			@Override
			@Nonnull
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				if(!isItemValid(slot, stack)) {
					return stack;
				}
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	@Override
	protected LazyOptional<IItemHandler> getOutHandler() {
		return LazyOptional.of(()->new IItemHandler(){

			@Override
			public int getSlots() {
				return 2;
			}

			@NotNull
			@Override
			public ItemStack getStackInSlot(int slot) {
				return itemHandler.getStackInSlot(slot+1);
			}

			@NotNull
			@Override
			public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
				return itemHandler.insertItem(slot+1, stack, simulate);
			}

			@NotNull
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				return itemHandler.extractItem(slot+1,amount,simulate);
			}

			@Override
			public int getSlotLimit(int slot) {
				return itemHandler.getSlotLimit(slot+1);
			}

			@Override
			public boolean isItemValid(int slot, @NotNull ItemStack stack) {
				return itemHandler.isItemValid(slot+1,stack);
			}
		});
	}

	@Override
	public Component getDisplayName() {
		return new TranslatableComponent("block.pepsimc.automated_centrifuge");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player plr) {
		return new AutomatedCentrifugeMenu(id,inv,this,dataAccess);
	}
}
