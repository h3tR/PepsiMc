package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class ProcessingBlockEntity extends BlockEntity{

	public final ItemStackHandler itemHandler;
	private final LazyOptional<IItemHandler> handler;
	private final BlockEntityType<?> type;
	public ProcessingBlockEntity(BlockEntityType<?> in, BlockPos pos, BlockState state) {
		super(in, pos, state);
		this.itemHandler = createHandler();
		this.handler = LazyOptional.of(()->itemHandler);
		this.type = in;
	}
	
	@Override
	public void load(CompoundTag nbt) {
		itemHandler.deserializeNBT(nbt.getCompound("inv"));
		super.load(nbt);
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		nbt.put("inv", itemHandler.serializeNBT());
		super.saveAdditional(nbt);
	}

	public NonNullList<ItemStack> getNNLInv(){
		NonNullList<ItemStack> toReturn = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);
		for(int i=0;i<itemHandler.getSlots();i++) {
			toReturn.set(i, itemHandler.getStackInSlot(i));
		}
		return toReturn; 
	}
	
	public abstract void process();
	
	public abstract void processAll();
	
	protected SimpleContainer getSimpleInv() {
		SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
		
		for(int i=0;i<itemHandler.getSlots();i++) {
			inv.setItem(i, itemHandler.getStackInSlot(i));
		}
		return inv;
	}
	
	protected abstract ItemStackHandler createHandler();

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY  && side == null) {
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}
	
	@Override
	public @NotNull BlockEntityType<?> getType() {
		return type;
	}
}
