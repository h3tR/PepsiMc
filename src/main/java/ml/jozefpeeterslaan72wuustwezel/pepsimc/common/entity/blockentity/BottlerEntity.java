package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

public class BottlerEntity extends ProcessingBlockEntity{
	
	public BottlerEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.BOTTLER_TILE.get(), pos, state);
	}

	@Override
	public void process(Level world) {
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, getSimpleInv(), world);

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
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, getSimpleInv(), world);
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.extractItem(1, 1, false);
				itemHandler.extractItem(2, 1, false);
				itemHandler.insertItem(3, iRecipe.getResultItem(), false);
				itemHandler.insertItem(4, new ItemStack(Items.BUCKET), false);
				setChanged();
			});	
			recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, getSimpleInv(), world);
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
					case 0: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "bottling_container"));
					case 1: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "bottling_label"));
					case 2: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "bottling_liquid"));
					case 3: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "bottled_liquid"));
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
}
