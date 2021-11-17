package ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.tileentity;


import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.util.tags.PepsiMcTags;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
	public NonNullList<ItemStack> getNNLInv(){
		NonNullList<ItemStack> toReturn = NonNullList.withSize(4, ItemStack.EMPTY);
		for(int i=0;i<4;i++) {
			toReturn.set(i, itemHandler.getStackInSlot(i));
		}
		return toReturn; 
	}
	
	private ItemStack HardCodedRecipe(ItemStack Container, ItemStack Label, ItemStack Fluid) {
		/**
	     * No Recipes needed :D
	     */
		if(Label.sameItem(new ItemStack(PepsiMcItem.PEPSI_LABEL.get()))&&Fluid.sameItem(new ItemStack(PepsiMcItem.PEPSI_FLUID_BUCKET.get()))) {
			if(Container.sameItem(new ItemStack(PepsiMcItem.EMPTY_BOTTLE.get()))) {
				return new ItemStack(PepsiMcItem.PEPSI_BOTTLE.get());
			}else if (Container.sameItem(new ItemStack(PepsiMcItem.EMPTY_CAN.get()))) {
				return new ItemStack(PepsiMcItem.PEPSI_CAN.get());
			}else {
				return ItemStack.EMPTY;
			}
		}else if(Label.sameItem(new ItemStack(PepsiMcItem.PEPSI_MAX_LABEL.get()))&&Fluid.sameItem(new ItemStack(PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get()))) {
			if(Container.sameItem(new ItemStack(PepsiMcItem.EMPTY_BOTTLE.get()))) {
				return new ItemStack(PepsiMcItem.PEPSI_MAX_BOTTLE.get());
			}else if (Container.sameItem(new ItemStack(PepsiMcItem.EMPTY_CAN.get()))) {
				return new ItemStack(PepsiMcItem.PEPSI_MAX_CAN.get());
			}else {
				return ItemStack.EMPTY;
			}
		}else {
			return ItemStack.EMPTY;
		}
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
			itemHandler.insertItem(2, new ItemStack(Items.BUCKET), false);
			itemHandler.insertItem(3, iRecipe.getResultItem(), false);
			setChanged();
		});	
		ItemStack resultItemStack = HardCodedRecipe(inv.getItem(0), inv.getItem(1), inv.getItem(2));
		boolean freeSlot = inv.getItem(3)==ItemStack.EMPTY||inv.getItem(3).getItem()==resultItemStack.getItem();
		if(resultItemStack!=ItemStack.EMPTY&&freeSlot) {
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.extractItem(2, 1, false);
			itemHandler.insertItem(2, new ItemStack(Items.BUCKET), false);
			itemHandler.insertItem(3, HardCodedRecipe(inv.getItem(0), inv.getItem(1), inv.getItem(2)), false);
			setChanged();
		}
		
	}

	
	private ItemStackHandler createHandler() {

		return new ItemStackHandler(4) {
			
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
					case 2: return stack.getItem().is(PepsiMcTags.Items.BOTTLING_LIQUID)||stack.getItem() == Items.BUCKET;
					case 3: return stack.getItem().is(PepsiMcTags.Items.BOTTLED_LIQUID);
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
