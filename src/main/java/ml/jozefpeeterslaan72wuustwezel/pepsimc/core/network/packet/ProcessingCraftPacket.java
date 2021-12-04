package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import java.util.function.Supplier;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.ProcessingTile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class ProcessingCraftPacket {
	public BlockPos pos;
	public boolean shift;
	public ProcessingCraftPacket(BlockPos pos,boolean shift) {
		this.pos = pos;
		this.shift = shift;
	}
	public static void encode(ProcessingCraftPacket message, FriendlyByteBuf buffer) {
		buffer.writeBlockPos(message.pos);
		buffer.writeBoolean(message.shift);
	}
	
	public static ProcessingCraftPacket decode(FriendlyByteBuf buffer) {
		return new ProcessingCraftPacket(buffer.readBlockPos(),buffer.readBoolean());
	}
	
	public static void handle(ProcessingCraftPacket message,Supplier<NetworkEvent.Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(()->{
			ServerPlayer plrEntity = ctx.getSender();
			Level world = plrEntity.level;
			BlockEntity TE = world.getBlockEntity(message.pos);
			if(TE instanceof ProcessingTile) {
				ProcessingTile PT = (ProcessingTile) TE;
				if(message.shift) {
					PT.processAll(world);
				} else {
					PT.process(world);
				}
			}
		});
		ctx.setPacketHandled(true);
	}
	
}
