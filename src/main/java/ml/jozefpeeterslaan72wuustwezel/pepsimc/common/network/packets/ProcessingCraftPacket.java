package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.network.packets;

import java.util.function.Supplier;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.tileentity.ProcessingTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class ProcessingCraftPacket {
	public final BlockPos pos;
	
	public ProcessingCraftPacket(BlockPos pos) {
		this.pos = pos;
	}
	
	public static ProcessingCraftPacket decode(PacketBuffer buffer) {
		return new ProcessingCraftPacket(buffer.readBlockPos());
	}
	
	public static void encode(ProcessingCraftPacket packet,PacketBuffer buffer) {
		buffer.writeBlockPos(packet.pos);
	}
	
	public static void handle(ProcessingCraftPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(()->{
			final TileEntity TE = ctx.get().getSender().level.getBlockEntity(packet.pos);
			if(TE instanceof ProcessingTile) {
				final ProcessingTile PT = (ProcessingTile) TE;
				PT.process(ctx.get().getSender().level);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
