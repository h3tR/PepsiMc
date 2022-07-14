package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.ArrayList;


public class RecyclerMenu extends ProcessingTileMenu {


	public RecyclerMenu(int ID, Inventory inv, BlockEntity entity) {
		super(ID,  inv, entity, PepsiMcMenu.RECYCLER_MENU.get(), 3, PepsiMcBlock.RECYCLER.get());
	}
	
	@Override
	protected void addSlots(IItemHandler h) {
		SlotHandlers.add(new SlotItemHandler(h,0,80,9));
		SlotHandlers.add(new SlotItemHandler(h,1,42,31));
		SlotHandlers.add(new SlotItemHandler(h,2,80,53));
		SlotHandlers.forEach((i) -> addSlot(i));
	}

	@Override
	public boolean slotHasItem(int slotIndex) {
		switch (slotIndex) {
			case 0:
				//catalyst
				return SlotHandlers.get(0).hasItem();
		
			case 1:
				//used
				return SlotHandlers.get(1).hasItem();
					
			case 2:
				//out
				return SlotHandlers.get(2).hasItem();
				
			default:
				return false;
		}	
	}
}
