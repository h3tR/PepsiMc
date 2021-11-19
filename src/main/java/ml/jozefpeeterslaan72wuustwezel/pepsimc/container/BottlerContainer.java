package ml.jozefpeeterslaan72wuustwezel.pepsimc.container;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.util.tags.PepsiMcTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BottlerContainer extends Container{
	
	private final TileEntity TE;
	private final IItemHandler inv;
	
	public BottlerContainer(int ID, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {
		super(PepsiMcContainer.BOTTLER_CONTAINER.get(), ID);
		this.TE = world.getBlockEntity(pos);
		this.inv = new InvWrapper(inventory);
		layoutPlayerInventorySlots(8, 86);

		if(TE !=null) {
			TE.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h->{
				addSlot(new SlotItemHandler(h,0,12,15));
				addSlot(new SlotItemHandler(h,1,30,15));
				addSlot(new SlotItemHandler(h,2,12,43));
				addSlot(new SlotItemHandler(h,3,143,30));
				
			});
		}
		
	}
	public boolean slotHasItem(int slotIndex) {
		return !inv.getStackInSlot(slotIndex).isEmpty();
	}
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(inv, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(inv, 0, leftCol, topRow, 9, 18);
    }

    
     public ItemStack quickMoveStack(PlayerEntity plr, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
           ItemStack itemstack1 = slot.getItem();
           itemstack = itemstack1.copy();
           if(itemstack1.getItem().is(PepsiMcTags.Items.BOTTLING_CONTAINER)&&index >= 4 && index < 40) {
        	   if (!this.moveItemStackTo(itemstack1, 0, 0, false)) {
                   return ItemStack.EMPTY;
                }
           } else if(itemstack1.getItem().is(PepsiMcTags.Items.BOTTLING_LABEL)&&index >= 4 && index < 40) {
        	   if (!this.moveItemStackTo(itemstack1, 1, 1, false)) {
                   return ItemStack.EMPTY;
                }
           } else if(itemstack1.getItem().is(PepsiMcTags.Items.BOTTLING_LIQUID)&&index >= 4 && index < 40) {
        	   if (!this.moveItemStackTo(itemstack1, 2, 2, false)) {
                   return ItemStack.EMPTY;
                }
           } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
              return ItemStack.EMPTY;
           } else {
        	   if (index >= 4 && index < 40) {
                   if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                      return ItemStack.EMPTY;
                   }
                }
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
    
	/*@Override
    public ItemStack quickMoveStack(PlayerEntity p_82846_1_, int p_82846_2_) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(p_82846_2_);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (p_82846_2_ < this.inv.getSlots()) {
				if (!this.moveItemStackTo(itemstack1, this.inv.getSlots(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, this.inv.getSlots(), false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}*/


    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return super.stillValid(IWorldPosCallable.create(TE.getLevel(), TE.getBlockPos()), playerIn, PepsiMcBlock.BOTTLER.get());
    }

}
