package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BottlerContainer extends ProcessingTileContainer{
	

	private SlotItemHandler ContainerSlotItemHandler;
	private SlotItemHandler LabelSlotItemHandler;
	private SlotItemHandler FluidSlotItemHandler;
	private SlotItemHandler OutSlotItemHandler;
	private SlotItemHandler OutBucketItemHandler;

	public BottlerContainer(int ID, Inventory inv, BlockEntity Entity){
		super(ID, inv, Entity, new SimpleContainerData(5),PepsiMcContainer.BOTTLER_CONTAINER.get(), PepsiMcBlock.BOTTLER.get());
	}
	
	@Override
	protected void addSlots(IItemHandler h) {
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
	}
	
	@Override
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
    
}
