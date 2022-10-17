package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity;


import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.FlavorMachineMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlavorMachineEntity extends ProcessingBlockEntity implements MenuProvider {
	
	public FlavorMachineEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.FLAVOR_MACHINE_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void process() {
		Optional<FlavoringRecipe> recipe = Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE, getSimpleInv(), this.getLevel());

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.insertItem(2, iRecipe.getResultItem(), false);
			setChanged();
		});	

	}
	
	@Override
	public void processAll() {
		Optional<FlavoringRecipe> recipe = Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE, getSimpleInv(), this.getLevel());
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.extractItem(1, 1, false);
				itemHandler.insertItem(2, iRecipe.getResultItem(), false);
				setChanged();
			});
			recipe = this.getLevel().getRecipeManager().getRecipeFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE, getSimpleInv(), this.getLevel());
		}
		

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
					case 0 -> stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.PEPSI_VARIANT);
					case 1 -> stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.FLAVOR);
					case 2 -> stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.PEPSI_VARIANT_FLAVOR);
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
	public @NotNull Component getDisplayName() {
		return new TranslatableComponent("block.pepsimc.flavor_machine");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player plr) {
		return new FlavorMachineMenu(id,inv,this);
	}
}
