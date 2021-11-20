package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import java.util.function.Supplier;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.tileentity.BottlerTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class BottlerCraftPacket {
	public int pos[];
	
	public BottlerCraftPacket(int[] pos) {
		this.pos = pos;
	}
	public static void encode(BottlerCraftPacket message, PacketBuffer buffer) {
		buffer.writeVarIntArray(message.pos);
	}
	
	public static BottlerCraftPacket decode(PacketBuffer buffer) {
		return new BottlerCraftPacket(buffer.readVarIntArray());
	}
	
	public static void handle(BottlerCraftPacket message,Supplier<NetworkEvent.Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(()->{
			ServerPlayerEntity plrEntity = ctx.getSender();
			World world = plrEntity.getCommandSenderWorld();
			
			try {
				TileEntity TE = world.getBlockEntity(new BlockPos(message.pos[0], message.pos[1], message.pos[2]));
				if(TE instanceof BottlerTile) {
					BottlerTile BT = (BottlerTile) TE;
					BT.bottle(world);
				}
			}catch (Exception e) {
				throw e;
			}
			
		});
	}
	
}
