package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;

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

public class ProcessingBlockEntity extends BlockEntity{

	public final ItemStackHandler itemHandler;
	private LazyOptional<IItemHandler> handler;
	private BlockEntityType<?> type;
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
	public CompoundTag save(CompoundTag nbt) {
		nbt.put("inv", itemHandler.serializeNBT());
		return super.save(nbt);
	}
	
	public boolean slotHasItem(int index) {
		return itemHandler.getStackInSlot(index).getCount() > 0;
	}
	
	public NonNullList<ItemStack> getNNLInv(){
		NonNullList<ItemStack> toReturn = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);
		for(int i=0;i<itemHandler.getSlots();i++) {
			toReturn.set(i, itemHandler.getStackInSlot(i));
		}
		return toReturn; 
	}
	
	public void process(Level world) {
		LogManager.getLogger().warn("Process method called outside super class!");
	}
	
	public void processAll(Level world) {
		LogManager.getLogger().warn("Processall method called outside super class!");
	}
	
	protected SimpleContainer getSimpleInv() {
		SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
		
		for(int i=0;i<itemHandler.getSlots();i++) {
			inv.setItem(i, itemHandler.getStackInSlot(i));
		}
		return inv;
	}
	
	protected ItemStackHandler createHandler() {return null;}

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
		return type;
	}
}
