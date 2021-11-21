package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BottlerTile extends BlockEntity{

	public final ItemStackHandler itemHandler = createHandler();
	private final LazyOptional<IItemHandler> handler = LazyOptional.of(()->itemHandler);
	
	public BottlerTile(BlockEntityType<?> In, BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.BOTTLER_TILE.get(), pos, state);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		super.load(nbt);
	}
	
	
	@Override
	public CompoundTag save(CompoundTag nbt) {
		nbt.put("inv", itemHandler.serializeNBT());
		return super.save(nbt);
	}
	
	public boolean slotHasItem(int index) {
		return itemHandler.getStackInSlot(index).getCount() > 0;
	}
	
	public NonNullList<ItemStack> getNNLInv(){
		NonNullList<ItemStack> toReturn = NonNullList.withSize(5, ItemStack.EMPTY);
		for(int i=0;i<5;i++) {
			toReturn.set(i, itemHandler.getStackInSlot(i));
		}
		return toReturn; 
	}
	
	public void bottle(Level world) {
		SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
		
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

	
	private ItemStackHandler createHandler() {

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
					case 3: return stack.getItem().getTags().contains(new ResourceLocation("pepsimc", "bottlied_liquid"));
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

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
		
		return super.getCapability(cap, side);
	}


	@Override
	public BlockEntityType<?> getType() {
		// TODO Auto-generated method stub
		return PepsiMcBlockEntity.BOTTLER_TILE.get();
	}

	
	

}
