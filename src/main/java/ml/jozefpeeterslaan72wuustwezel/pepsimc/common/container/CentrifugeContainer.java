package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class CentrifugeContainer extends ProcessingTileContainer {
	

	private SlotItemHandler InSlotItemHandler;
	private SlotItemHandler ByProductSlotItemHandler;
	private SlotItemHandler OutSlotItemHandler;


	public CentrifugeContainer(int ID, Level world, BlockPos pos, Inventory inventory, Player player) {
		super(ID, world, pos, inventory, player, PepsiMcContainer.CENTRIFUGE_CONTAINER.get(), ID, PepsiMcBlock.CENTRIFUGE.get());
	}
	
	@Override
	protected void addSlots(IItemHandler h) {
		InSlotItemHandler=new SlotItemHandler(h,0,80,9);
		OutSlotItemHandler=new SlotItemHandler(h,1,66,53);
		ByProductSlotItemHandler=new SlotItemHandler(h,2,94,53);

		addSlot(InSlotItemHandler);
		addSlot(ByProductSlotItemHandler);
		addSlot(OutSlotItemHandler);
	}
	
	@Override
	public boolean slotHasItem(int slotIndex) {
		switch (slotIndex) {
			case 0:
				return InSlotItemHandler.hasItem();
		
			case 1:
				return OutSlotItemHandler.hasItem();
					
			case 2:
				return ByProductSlotItemHandler.hasItem();
				
			default:
				return false;
		}	
	}
}
