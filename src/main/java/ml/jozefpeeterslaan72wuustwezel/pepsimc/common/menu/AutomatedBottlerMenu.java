package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.AutomatedBottlerEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.AutomatedProcessingBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.TankOwner;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.FluidSynchronizationPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;


public class AutomatedBottlerMenu extends AutomatedProcessingMenu implements FluidHandlerMenu{

	public AutomatedBottlerMenu(int ID, Inventory inv, BlockEntity entity, ContainerData data) {
		super(ID,  inv, entity, PepsiMcMenu.AUTOMATED_BOTTLER_MENU.get(), 5, data);
	}

	@Override
	protected void addSlots(IItemHandler h) {
		SlotHandlers.add(new SlotItemHandler(h,0,12,29));
		SlotHandlers.add(new SlotItemHandler(h,1,30,29));
		SlotHandlers.add(new SlotItemHandler(h,2,48,29));
		SlotHandlers.add(new OutputSlot(h,3,129,29));
		SlotHandlers.add(new OutputSlot(h,4,129,50));
		SlotHandlers.forEach(this::addSlot);
	}

	@Override
	public ItemStack quickMoveStack(Player plr, int index) {
		AutomatedProcessingBlockEntity rEntity = (AutomatedProcessingBlockEntity)entity;
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();

			if (index < 36) {
				if (this.slots.get(36).mayPlace(itemstack)&&rEntity.itemHandler.isItemValid(0,itemstack)) {
					if (this.moveItemStackTo(itemstack1, 36, 37, false)) {
						return ItemStack.EMPTY;
					}
				}else if (this.slots.get(37).mayPlace(itemstack)&&rEntity.itemHandler.isItemValid(1,itemstack)) {
					if (this.moveItemStackTo(itemstack1, 37, 38, false)) {
						return ItemStack.EMPTY;
					}
				}else if (this.slots.get(38).mayPlace(itemstack)&&rEntity.itemHandler.isItemValid(2,itemstack)) {
					if (this.moveItemStackTo(itemstack1, 38, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 0 && index < 27) {
					if (!this.moveItemStackTo(itemstack1, 27, 35, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 27 && index < 35) {
					if (!this.moveItemStackTo(itemstack1, 0, 27, false)) {
						return ItemStack.EMPTY;
					}
				} else if (!this.moveItemStackTo(itemstack1, 0, 35, false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(itemstack1, 0, 35, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(plr, itemstack1);
		}

		return itemstack;
	}


	public void  transferItemFluidToFluidTank(Player plr, @NotNull ItemStack itemStack) {
		AutomatedBottlerEntity entity = (AutomatedBottlerEntity)this.entity;
		if(itemStack.getItem() instanceof BucketItem && entity.fluidTank.getSpace()>=1000){
			entity.fluidTank.fill(new FluidBucketWrapper(itemStack).getFluid(), IFluidHandler.FluidAction.EXECUTE);
			itemStack.setCount(itemStack.getCount()-1);
			if(!plr.getInventory().add(new ItemStack(Items.BUCKET)))
				plr.drop(new ItemStack(Items.BUCKET),true);

		}else {
			ItemStack handlerItem = itemStack.copy();
			handlerItem.setCount(1);
			FluidUtil.getFluidHandler(handlerItem).ifPresent(handler -> {
				int drainAmount = Math.min(entity.fluidTank.getSpace(), 1000);

				FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
				if (entity.fluidTank.isFluidValid(stack)) {
					handler.drain(stack, IFluidHandler.FluidAction.EXECUTE);

					itemStack.setCount(itemStack.getCount() - 1);
					if (!plr.getInventory().add(handlerItem))
						plr.drop(handlerItem, false);
				}

			});
		}
		PepsimcNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), new FluidSynchronizationPacket(this.entity.getBlockPos(),this.getFluidStack()));
	}

	@Override
	public void transferTankFluidToFluidItem(Player plr, @NotNull ItemStack itemStack) {
		AutomatedBottlerEntity entity = (AutomatedBottlerEntity)this.entity;
		if(itemStack.getItem() instanceof BucketItem && entity.fluidTank.getFluid().getAmount()>=1000){
			itemStack.setCount(itemStack.getCount()-1);
			ItemStack filled = FluidUtil.getFilledBucket(entity.fluidTank.getFluid());
			entity.fluidTank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
			if(!plr.getInventory().add(filled))
				plr.drop(filled,true);

		}else {
			ItemStack handlerItem = itemStack.copy();
			FluidUtil.getFluidHandler(handlerItem).ifPresent(handler -> {
				int drainAmount = Math.min(entity.fluidTank.getSpace(), 1000);
				FluidStack stack = entity.fluidTank.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);

				if(handler.isFluidValid(0,stack)) {
					handler.fill(stack, IFluidHandler.FluidAction.EXECUTE);

					itemStack.setCount(itemStack.getCount()-1);
					if(!plr.getInventory().add(handlerItem))
						plr.drop(handlerItem,true);
				}
			});
		}

		PepsimcNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), new FluidSynchronizationPacket(this.entity.getBlockPos(),this.getFluidStack()));
	}

	@Override
	public void setFluidStack(FluidStack fluidStack) {
		((TankOwner)entity).setFluidStack(fluidStack);
	}

	@Override
	public FluidStack getFluidStack() {
		return ((TankOwner)entity).getFluidStack();
	}

}
