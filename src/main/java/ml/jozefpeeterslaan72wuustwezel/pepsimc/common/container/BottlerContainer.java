package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class BottlerContainer extends AbstractContainerMenu{
	
	public final BlockEntity TE;
	private final IItemHandler inv;
	private SlotItemHandler ContainerSlotItemHandler;
	private SlotItemHandler LabelSlotItemHandler;
	private SlotItemHandler FluidSlotItemHandler;
	private SlotItemHandler OutSlotItemHandler;
	private SlotItemHandler OutBucketItemHandler;


	public BottlerContainer(int ID, Level world, BlockPos pos, Inventory inventory, Player player) {
		super(PepsiMcContainer.BOTTLER_CONTAINER.get(), ID);
		this.TE = world.getBlockEntity(pos);
		this.inv = new InvWrapper(inventory);
		layoutPlayerInventorySlots(8, 86);
		
		if(TE !=null) {
			TE.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h->{
				ContainerSlotItemHandler=new SlotItemHandler(h,0,12,15);
				LabelSlotItemHandler=new SlotItemHandler(h,1,30,15);
				FluidSlotItemHandler=new SlotItemHandler(h,2,12,43);
				OutSlotItemHandler=new SlotItemHandler(h,3,143,30);
				OutBucketItemHandler=new SlotItemHandler(h,4,143,51);
				addSlot(OutBucketItemHandler);
				addSlot(ContainerSlotItemHandler);
				addSlot(LabelSlotItemHandler);
				addSlot(FluidSlotItemHandler);
				addSlot(OutSlotItemHandler);

			});
		}
		
	}
	
	
	
		public boolean slotHasItem(int slotIndex) 
		{
			switch (slotIndex) {
				case 0:
					return ContainerSlotItemHandler.hasItem();
					
				case 1:
					return LabelSlotItemHandler.hasItem();
					
				case 2:
					return FluidSlotItemHandler.hasItem();
				case 3:
					return OutSlotItemHandler.hasItem();
				case 4:
					return OutBucketItemHandler.hasItem();
				default:
					return false;
			}
			
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
   
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.getSlot(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < 5
					&& !this.moveItemStackTo(stack1, 5, inv.getSlots()-1, true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, inv.getSlots()-1, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
				slot.setChanged();
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}



    @Override
    public boolean stillValid(Player playerIn) {
        return super.stillValid(ContainerLevelAccess.create(TE.getLevel(), TE.getBlockPos()), playerIn, PepsiMcBlock.BOTTLER.get());
    }

    
}
