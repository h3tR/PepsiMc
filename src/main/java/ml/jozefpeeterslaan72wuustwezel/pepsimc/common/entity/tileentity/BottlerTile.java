package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.tileentity;


import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class BottlerTile extends ProcessingTile implements ITickableTileEntity{
	
	public BottlerTile() {
		super(PepsiMcTileEntity.BOTTLER_TILE.get());
	}
	
	@Override
	public void process(World world) {
		Inventory inv = new Inventory(itemHandler.getSlots());
		
		for(int i=0;i<itemHandler.getSlots();i++) {
			inv.setItem(i, itemHandler.getStackInSlot(i));
		}
		
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, inv, world);
		
		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.extractItem(2, 1, false);
			itemHandler.insertItem(4, new ItemStack(Items.BUCKET), false);
			itemHandler.insertItem(3, iRecipe.getResultItem(), false);
			setChanged();
		});	
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
					case 0: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_CONTAINER);
					case 1: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_LABEL);
					case 2: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_LIQUID);
					case 3: return stack.getItem().is(PepsiMcTags.Items.BOTTLED_LIQUID);
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
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	

}
