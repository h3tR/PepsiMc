package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class RecyclerContainer extends ProcessingTileContainer{
	

	private SlotItemHandler CatalystSlotItemHandler;
	private SlotItemHandler UsedSlotItemHandler;
	private SlotItemHandler OutSlotItemHandler;


	public RecyclerContainer(int ID, Level world, BlockPos pos, Inventory inventory, Player player) {
		super(ID, world, pos, inventory, player, PepsiMcContainer.RECYCLER_CONTAINER.get(), ID, PepsiMcBlock.RECYCLER.get());
	}
	
	@Override
	protected void addSlots(IItemHandler h) {
		CatalystSlotItemHandler=new SlotItemHandler(h,0,80,9);
		UsedSlotItemHandler=new SlotItemHandler(h,1,42,31);
		OutSlotItemHandler=new SlotItemHandler(h,2,80,53);
		addSlot(CatalystSlotItemHandler);
		addSlot(UsedSlotItemHandler);
		addSlot(OutSlotItemHandler);
	}
	
	@Override
	public boolean slotHasItem(int slotIndex) {
		switch (slotIndex) {
			case 0:
				return CatalystSlotItemHandler.hasItem();
		
			case 1:
				return UsedSlotItemHandler.hasItem();
					
			case 2:
				return OutSlotItemHandler.hasItem();
				
			default:
				return false;
		}	
	}
}
