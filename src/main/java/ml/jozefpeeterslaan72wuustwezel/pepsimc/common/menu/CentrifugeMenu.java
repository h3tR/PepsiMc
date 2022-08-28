package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class CentrifugeMenu extends ProcessingMenu {





	public CentrifugeMenu(int ID, Inventory inv, BlockEntity entity) {
		super(ID,  inv, entity, PepsiMcMenu.CENTRIFUGE_MENU.get(), 3, PepsiMcBlock.CENTRIFUGE.get());

	}
	protected void addSlots(IItemHandler h) {
		SlotHandlers.add(new SlotItemHandler(h,0,80,9));
		SlotHandlers.add(new SlotItemHandler(h,1,66,53));
		SlotHandlers.add(new SlotItemHandler(h,2,94,53));
		SlotHandlers.forEach((i) -> addSlot(i));
	}
	@Override
	public boolean slotHasItem(int slotIndex) {
		switch (slotIndex) {
			case 0:
				return SlotHandlers.get(0).hasItem();
		
			case 1:
				return SlotHandlers.get(01).hasItem();
					
			case 2:
				return SlotHandlers.get(2).hasItem();
				
			default:
				return false;
		}	
	}
}
