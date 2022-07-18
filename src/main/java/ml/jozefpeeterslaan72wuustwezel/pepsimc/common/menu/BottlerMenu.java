package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BottlerMenu extends ProcessingTileMenu {




	public BottlerMenu(int ID, Inventory inv, BlockEntity entity) {
		super(ID,  inv, entity, PepsiMcMenu.BOTTLER_MENU.get(), 3, PepsiMcBlock.BOTTLER.get());
	}

	protected void addSlots(IItemHandler h) {
		SlotHandlers.add(new SlotItemHandler(h,0,12,15));
		SlotHandlers.add(new SlotItemHandler(h,1,30,15));
		SlotHandlers.add(new SlotItemHandler(h,2,12,43));
		SlotHandlers.add(new SlotItemHandler(h,3,143,30));
		SlotHandlers.add(new SlotItemHandler(h,4,143,51));

		SlotHandlers.forEach((i) -> addSlot(i));
	}
	@Override
		public boolean slotHasItem(int slotIndex) 
		{
			switch (slotIndex) {
				case 0:
					return SlotHandlers.get(0).hasItem();
					
				case 1:
					return SlotHandlers.get(1).hasItem();
					
				case 2:
					return SlotHandlers.get(2).hasItem();
				case 3:
					return SlotHandlers.get(3).hasItem();
				case 4:
					return SlotHandlers.get(4).hasItem();
				default:
					return false;
			}
			
	}
    
}
