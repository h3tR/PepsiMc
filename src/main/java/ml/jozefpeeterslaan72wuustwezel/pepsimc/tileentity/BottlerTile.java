package ml.jozefpeeterslaan72wuustwezel.pepsimc.tileentity;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.tags.PepsiMcTags;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BottlerTile extends TileEntity implements ITickableTileEntity{

	private final ItemStackHandler itemHandler = createHandler();
	private final LazyOptional<IItemHandler> handler = LazyOptional.of(()->itemHandler);
	
	public BottlerTile(TileEntityType<?> In) {
		super(In);
	}
	public BottlerTile() {
		this(PepsiMcTileEntity.BOTTLER_TILE.get());
	}
	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		super.load(state, nbt);
	}
	
	@Override
	public CompoundNBT save(CompoundNBT nbt) {
		nbt.put("inv", itemHandler.serializeNBT());
		return super.save(nbt);
	}
	
	public boolean slotHasItem(int index) {
		return itemHandler.getStackInSlot(index).getCount() > 0;
	}
	
	@Override
	public void tick() {
		if(this.level.isClientSide)
		return;	
		
		bottle(this.level);
	}
	
	public void bottle(World world) {
		Inventory inv = new Inventory(itemHandler.getSlots());
		for(int i=0;i<itemHandler.getSlots();i++) {
			inv.setItem(i, itemHandler.getStackInSlot(i));
		}
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, inv, world);
		
		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.extractItem(2, 1, false);
			itemHandler.insertItem(3, iRecipe.getResultItem(), false);
			setChanged();
		});
	}

	
	private ItemStackHandler createHandler() {

		return new ItemStackHandler(4) {
			
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}

			@Override 
			public int getSlotLimit(int slot)
			{
				switch (slot) {
					case 2:
						return 1;
					default:
						return 64;
					
				}
			}
			
			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				switch (slot) {
					case 0: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_CONTAINER);
					case 1: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_LABEL);
					case 2: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_LIQUID);
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

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
		
		return super.getCapability(cap, side);
	}
	

}
