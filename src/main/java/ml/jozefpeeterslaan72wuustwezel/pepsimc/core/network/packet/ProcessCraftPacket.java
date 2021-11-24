package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import java.util.function.Supplier;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.tileentity.ProcessingTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class ProcessCraftPacket {
	public BlockPos pos;
	
	public ProcessCraftPacket() {
	}
	
	public ProcessCraftPacket(BlockPos pos) {
		this.pos = pos;
	}
	
	
	public static void encode(ProcessCraftPacket message, PacketBuffer buffer) {
		buffer.writeBlockPos(message.pos);
	}
	
	public ProcessCraftPacket(PacketBuffer buffer) {
		this(buffer.readBlockPos());
	}
	
	public static boolean handle(ProcessCraftPacket message,Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(()->{

			final TileEntity TE = ctx.get().getSender().level.getBlockEntity(message.pos);
			if(TE instanceof ProcessingTile) {
				ProcessingTile PT = (ProcessingTile) TE;
				PT.process(ctx.get().getSender().level);
			}

		});
		ctx.get().setPacketHandled(true);
		return true;
	}
	
}
