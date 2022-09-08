package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedProcessingBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.ProcessingBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class AutomatedCentrifugeMenu extends AutomatedProcessingMenu {

	public AutomatedCentrifugeMenu(int ID, Inventory inv, BlockEntity entity, ContainerData data) {
		super(ID,  inv, entity, PepsiMcMenu.AUTOMATED_CENTRIFUGE_MENU.get(), 3,data);

	}
	protected void addSlots(IItemHandler h) {
		SlotHandlers.add(new SlotItemHandler(h,0,80,23));
		SlotHandlers.add(new OutputSlot(h,1,66,53));
		SlotHandlers.add(new OutputSlot(h,2,94,53));
		SlotHandlers.forEach(this::addSlot);
	}

	@Override
	public ItemStack quickMoveStack(Player plr, int index) {
		AutomatedProcessingBlockEntity rEntity = (AutomatedProcessingBlockEntity)entity;
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 36) {
				if (this.slots.get(36).mayPlace(itemstack)&&rEntity.itemHandler.isItemValid(0,itemstack)) {
					if (this.moveItemStackTo(itemstack1, 36, 37, false)) {
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


}
