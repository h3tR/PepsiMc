package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.BottlerCraftPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PepsimcNetwork {
	public static final String PROTOCOL_VERSION = "1.3.2";
	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation("pepsimc","network"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(()->PROTOCOL_VERSION)
			.simpleChannel();
	
	public static void init() {
		int index = 0;
		CHANNEL.messageBuilder(BottlerCraftPacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
		.encoder(BottlerCraftPacket::encode)
		.decoder(BottlerCraftPacket::new)
		.consumer(BottlerCraftPacket::handle)
		.add();
	}
}
