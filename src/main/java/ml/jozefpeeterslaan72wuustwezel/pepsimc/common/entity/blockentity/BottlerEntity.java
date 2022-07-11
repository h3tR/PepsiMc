package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.BottlerContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

public class BottlerEntity extends ProcessingBlockEntity implements MenuProvider {
	
	public BottlerEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.BOTTLER_TILE.get(), pos, state);
	}

	@Override
	public void process(Level world) {
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), world);

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
	public void processAll(Level world) {
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), world);
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.extractItem(1, 1, false);
				itemHandler.extractItem(2, 1, false);
				itemHandler.insertItem(3, iRecipe.getResultItem(), false);
				itemHandler.insertItem(4, new ItemStack(Items.BUCKET), false);
				setChanged();
			});	
			recipe = world.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), world);
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
				switch (slot) {
					case 0: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_CONTAINER);
					case 1: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LABEL);
					case 2: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LIQUID);
					case 3: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLED_LIQUID);
					case 4: return stack.getItem() == Items.BUCKET;

					default:
						return false;
				
				}
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
	public Component getDisplayName() {
		return Component.translatable("screen.pepsimc.bottler");

	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player plr) {
		return new BottlerContainer(id,inv,this);
	}
}
