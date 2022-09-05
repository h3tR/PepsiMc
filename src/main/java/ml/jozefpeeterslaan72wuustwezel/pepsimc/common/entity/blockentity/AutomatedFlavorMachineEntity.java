package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedFlavorMachineMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class AutomatedFlavorMachineEntity extends AutomatedProcessingBlockEntity implements MenuProvider {

	public AutomatedFlavorMachineEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.AUTOMATED_FLAVOR_MACHINE_BLOCK_ENTITY.get(), pos, state,1000, 10);
	}


	@Override
	protected Optional<FlavoringRecipe> getRecipe() {
		return  this.getLevel().getRecipeManager().getRecipeFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE, getSimpleInv(), this.getLevel());
	}

	@Override
	protected void finishProduct() {
		Optional<FlavoringRecipe> recipe = getRecipe();

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.insertItem(2, iRecipe.getResultItem(), false);
			setChanged();
		});
	}

	@Override
	protected int getOutputSlot() {
		return 2;
	}

	@Override
	protected int getByProductSlot() {
		return -1;
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
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.FLAVOR);
					case 2 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.FLAVORED);
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
				return 1;
			}

			@NotNull
			@Override
			public ItemStack getStackInSlot(int slot) {
				return itemHandler.getStackInSlot(slot+2);
			}

			@NotNull
			@Override
			public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
				return itemHandler.insertItem(slot+2, stack, simulate);
			}

			@NotNull
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				return itemHandler.extractItem(slot+2,amount,simulate);
			}

			@Override
			public int getSlotLimit(int slot) {
				return itemHandler.getSlotLimit(slot+2);
			}

			@Override
			public boolean isItemValid(int slot, @NotNull ItemStack stack) {
				return itemHandler.isItemValid(slot+2,stack);
			}
		});
	}

	@Override
	public @NotNull Component getDisplayName() {
		return new TranslatableComponent("block.pepsimc.flavor_machine");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player plr) {
		return new AutomatedFlavorMachineMenu(id,inv,this,dataAccess);
	}
}
