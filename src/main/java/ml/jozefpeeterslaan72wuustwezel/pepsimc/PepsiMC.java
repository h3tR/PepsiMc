package ml.jozefpeeterslaan72wuustwezel.pepsimc;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.block;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.fluid.fluid;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.item;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("pepsimc")
public class PepsiMC {

	public PepsiMC() {
		
		    	
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::doClientStuff);
    	item.ITEMS.register(bus);
    	block.BLOCKS.register(bus);
    	fluid.FLUIDS.register(bus);

    	MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, worldgen::OreGen);
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		event.enqueueWork(()->{
			RenderTypeLookup.setRenderLayer(block.PEPSI_FLUID_BLOCK.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(fluid.PEPSI_FLUID.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(fluid.PEPSI_FLOW.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(block.PEPSI_MAX_FLUID_BLOCK.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(fluid.PEPSI_MAX_FLUID.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(fluid.PEPSI_MAX_FLOW.get(), RenderType.translucent());
		});
		
	}
}
