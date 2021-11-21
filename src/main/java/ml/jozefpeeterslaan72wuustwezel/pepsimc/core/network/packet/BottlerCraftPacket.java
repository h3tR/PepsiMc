package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import java.util.function.Supplier;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.BottlerTile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class BottlerCraftPacket {
	public int pos[];
	
	public BottlerCraftPacket(int[] pos) {
		this.pos = pos;
	}
	public static void encode(BottlerCraftPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarIntArray(message.pos);
	}
	
	public static BottlerCraftPacket decode(FriendlyByteBuf buffer) {
		return new BottlerCraftPacket(buffer.readVarIntArray());
	}
	
	public static void handle(BottlerCraftPacket message,Supplier<NetworkEvent.Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(()->{
			ServerPlayer plrEntity = ctx.getSender();
			Level world = plrEntity.getCommandSenderWorld();
			
			try {
				BlockEntity TE = world.getBlockEntity(new BlockPos(message.pos[0], message.pos[1], message.pos[2]));
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
