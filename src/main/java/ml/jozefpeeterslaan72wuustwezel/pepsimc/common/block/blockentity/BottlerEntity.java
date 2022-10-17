package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity;


import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.BottlerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
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
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BottlerEntity extends ProcessingBlockEntity implements MenuProvider {

	public BottlerEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.BOTTLER_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void process() {
		Optional<BottlerRecipe> recipe = Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), this.getLevel());

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.extractItem(2, 1, false);
			itemHandler.insertItem(3, iRecipe.getResultItem(), false);
			itemHandler.insertItem(4, new ItemStack(Items.BUCKET), false);
			setChanged();
		});

	}

	@Override
	public void processAll() {
		Optional<BottlerRecipe> recipe = Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), this.getLevel());
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.extractItem(1, 1, false);
				itemHandler.extractItem(2, 1, false);
				itemHandler.insertItem(3, iRecipe.getResultItem(), false);
				itemHandler.insertItem(4, Objects.requireNonNull(iRecipe.getByproductItem()), false);
				setChanged();
			});
			recipe = this.getLevel().getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), this.getLevel());
		}
		

	}
	
	@Override
	protected ItemStackHandler createHandler() {

		return new ItemStackHandler(5) {
			
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
					case 0 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_CONTAINER);
					case 1 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LABEL);
					case 2 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LIQUID);
					case 3 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLED_LIQUID);
					case 4 -> stack.getItem() == Items.BUCKET;
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
		return new TranslatableComponent("block.pepsimc.bottler");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player plr) {
		return new BottlerMenu(id,inv,this);
	}
}
