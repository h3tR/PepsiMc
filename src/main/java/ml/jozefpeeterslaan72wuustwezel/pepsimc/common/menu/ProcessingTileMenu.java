package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import org.apache.logging.log4j.LogManager;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.ArrayList;

public class ProcessingTileMenu extends AbstractContainerMenu{
	
	public final BlockEntity entity;
	private final IItemHandler inv;
	public Block block;
	public int Size;
    protected final ArrayList<SlotItemHandler> SlotHandlers;


    public ProcessingTileMenu(int ID, Inventory inv, BlockEntity entity, MenuType<?> container, int Size, Block block) {
		super(container, ID);
        this.entity = entity;
		this.inv = new InvWrapper(inv);
		this.block = block;
		this.Size = Size;
        this.SlotHandlers = new ArrayList<SlotItemHandler>();
        layoutPlayerInventorySlots(8, 86);
		
		if(entity !=null) {
            entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h->{
				addSlots(h);
			});
		}
		
	}

	public boolean slotHasItem(int slotIndex) {
		return inv.getStackInSlot(slotIndex) != null;
	}
	
	protected void addSlots(IItemHandler h) {
		LogManager.getLogger().warn("addSlots method called outside super class");
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
   
  /*  private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;*/

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {     
    	return ItemStack.EMPTY;        
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return super.stillValid(ContainerLevelAccess.create(entity.getLevel(), entity.getBlockPos()), playerIn,block);
    }

    
}
