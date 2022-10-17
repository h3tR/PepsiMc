package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.FluidSynchronizationPacket;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.GuiFluidTankTransferPacket;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.ProcessingCraftPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PepsimcNetwork {
	public static final String VERSION = "2.2";
	private static int packetId = 0;
	private static int id() {
		return packetId++;
	}
	public static final SimpleChannel CHANNEL = NetworkRegistry
			.newSimpleChannel(new ResourceLocation("pepsimc","network"),()->VERSION , version->version.equals(VERSION), version->version.equals(VERSION));
	
	
	public static void init() {
		CHANNEL.messageBuilder(ProcessingCraftPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
						.decoder(ProcessingCraftPacket::decode)
						.encoder(ProcessingCraftPacket::encode)
						.consumer(ProcessingCraftPacket::handle)
						.add();
		CHANNEL.messageBuilder(GuiFluidTankTransferPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
				.decoder(GuiFluidTankTransferPacket::decode)
				.encoder(GuiFluidTankTransferPacket::encode)
				.consumer(GuiFluidTankTransferPacket::handle)
				.add();
		CHANNEL.messageBuilder(FluidSynchronizationPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
				.decoder(FluidSynchronizationPacket::decode)
				.encoder(FluidSynchronizationPacket::encode)
				.consumer(FluidSynchronizationPacket::handle)
				.add();

	}
}
