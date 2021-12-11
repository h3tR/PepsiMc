package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

public class FlavorMachineEntity extends ProcessingBlockEntity{
	
	public FlavorMachineEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.FLAVOR_MACHINE_TILE.get(), pos, state);
	}

	@Override
	public void process(Level world) {
		Optional<FlavoringRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.FLAVORING_RECIPE, getSimpleInv(), world);

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.insertItem(2, iRecipe.getResultItem(), false);
			setChanged();
		});	

	}
	
	@Override
	public void processAll(Level world) {
		Optional<FlavoringRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.FLAVORING_RECIPE, getSimpleInv(), world);
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.extractItem(1, 1, false);
				itemHandler.insertItem(2, iRecipe.getResultItem(), false);
				setChanged();
			});
			recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.FLAVORING_RECIPE, getSimpleInv(), world);
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
				switch (slot) {
					case 0: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "flavor"));
					case 1: return true;
					case 2: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "flavored"));
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