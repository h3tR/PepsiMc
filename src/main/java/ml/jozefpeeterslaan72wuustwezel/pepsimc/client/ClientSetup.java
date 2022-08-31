package ml.jozefpeeterslaan72wuustwezel.pepsimc.client;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render.CentrifugeRender;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render.RecyclerRender;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = "pepsimc", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

	@SubscribeEvent
	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
			event.registerBlockEntityRenderer(PepsiMcBlockEntity.RECYCLER_BLOCK_ENTITY.get(), RecyclerRender::new);
			event.registerBlockEntityRenderer(PepsiMcBlockEntity.CENTRIFUGE_BLOCK_ENTITY.get(), CentrifugeRender::new);

	}
	
	@SubscribeEvent
	public static void registerRenderers(final FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(PepsiMcBlock.RECYCLER.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcBlock.CENTRIFUGE.get(), RenderType.cutout());

	}
	

}
