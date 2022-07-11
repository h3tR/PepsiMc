package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class FlavorMachineContainer extends ProcessingTileContainer{
	

	private SlotItemHandler FlavorSlotItemHandler;
	private SlotItemHandler BaseBeverageSlotItemHandler;
	private SlotItemHandler OutSlotItemHandler;


	public FlavorMachineContainer(int ID, Inventory inv, BlockEntity Entity) {
		super(ID, inv, Entity, new SimpleContainerData(3), PepsiMcContainer.FLAVOR_MACHINE_CONTAINER.get(), PepsiMcBlock.FLAVOR_MACHINE.get());
	}
	
	@Override
	protected void addSlots(IItemHandler h) {
		FlavorSlotItemHandler=new SlotItemHandler(h,0,80,20);
		BaseBeverageSlotItemHandler=new SlotItemHandler(h,1,42,42);
		OutSlotItemHandler=new SlotItemHandler(h,2,80,64);
		addSlot(FlavorSlotItemHandler);
		addSlot(BaseBeverageSlotItemHandler);
		addSlot(OutSlotItemHandler);
	}
	
	@Override
	public boolean slotHasItem(int slotIndex) {
		switch (slotIndex) {
			case 1:
				return FlavorSlotItemHandler.hasItem();
		
			case 0:
				return BaseBeverageSlotItemHandler.hasItem();
					
			case 2:
				return OutSlotItemHandler.hasItem();
				
			default:
				return false;
		}	
	}
}
