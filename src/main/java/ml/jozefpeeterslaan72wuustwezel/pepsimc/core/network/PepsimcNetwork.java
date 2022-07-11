package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.ProcessingCraftPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PepsimcNetwork {
	public static final String VERSION = "1.3";
	public static final SimpleChannel CHANNEL = NetworkRegistry
			.newSimpleChannel(new ResourceLocation("pepsimc","network"),()->VERSION , version->version.equals(VERSION), version->version.equals(VERSION));
	
	
	public static void init() {
		CHANNEL.registerMessage(0, ProcessingCraftPacket.class, ProcessingCraftPacket::encode, ProcessingCraftPacket::decode, ProcessingCraftPacket::handle);
	}
}
