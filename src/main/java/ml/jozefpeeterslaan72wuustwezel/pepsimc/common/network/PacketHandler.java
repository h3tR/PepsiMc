package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.network;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.network.packets.ProcessingCraftPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler{

	public static final String PROTOCOL_VERSION = "1";
	public static SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation("pepsimc","main"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(()->PROTOCOL_VERSION)
			.simpleChannel();
	
	public static void init() {
		int index =0;		
		
		CHANNEL.messageBuilder(ProcessingCraftPacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
		.encoder(ProcessingCraftPacket::encode)
		.decoder(ProcessingCraftPacket::decode)
		.consumer(ProcessingCraftPacket::handle)
		.add();
	}
}
