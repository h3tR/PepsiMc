package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import java.util.function.Supplier;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.tileentity.BottlerTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class BottlerCraftPacket {
	public int pos[];
	
	public BottlerCraftPacket() {
	}
	
	public BottlerCraftPacket(int[] pos) {
		this.pos = pos;
	}
	
	
	public static void encode(BottlerCraftPacket message, PacketBuffer buffer) {
		buffer.writeVarIntArray(message.pos);
	}
	
	public BottlerCraftPacket(PacketBuffer buffer) {
		this(buffer.readVarIntArray());
	}
	
	public static boolean handle(BottlerCraftPacket message,Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(()->{
			final BlockPos pos = new BlockPos(message.pos[0], message.pos[1], message.pos[2]);
			final TileEntity TE = ctx.get().getSender().level.getBlockEntity(pos);
			if(TE instanceof BottlerTile) {
				BottlerTile BT = (BottlerTile) TE;
				BT.bottle(ctx.get().getSender().level);
			}

		});
		ctx.get().setPacketHandled(true);
		return true;
	}
	
}
