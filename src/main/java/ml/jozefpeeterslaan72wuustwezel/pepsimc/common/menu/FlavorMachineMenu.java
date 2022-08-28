package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class FlavorMachineMenu extends ProcessingMenu {




	public FlavorMachineMenu(int ID, Inventory inv, BlockEntity entity) {
		super(ID,  inv, entity, PepsiMcMenu.FLAVOR_MACHINE_MENU.get(), 3, PepsiMcBlock.FLAVOR_MACHINE.get());
	}
	
	@Override
	protected void addSlots(IItemHandler h) {
		SlotHandlers.add(new SlotItemHandler(h,0,80,20));
		SlotHandlers.add(new SlotItemHandler(h,1,42,42));
		SlotHandlers.add(new SlotItemHandler(h,2,80,64));
		SlotHandlers.forEach((i) -> addSlot(i));
	}
	@Override
	public boolean slotHasItem(int slotIndex) {
		switch (slotIndex) {
			case 1:
				return SlotHandlers.get(1).hasItem();
		
			case 0:
				return SlotHandlers.get(0).hasItem();
					
			case 2:
				return SlotHandlers.get(2).hasItem();
				
			default:
				return false;
		}	
	}
}
