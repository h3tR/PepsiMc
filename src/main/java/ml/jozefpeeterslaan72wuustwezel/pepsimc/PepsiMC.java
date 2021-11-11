package ml.jozefpeeterslaan72wuustwezel.pepsimc;




import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
//import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.fluid.PepsiMcFluid;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.world.WorldEvents;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.world.structure.PepsiMcStructure;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.container.PepsiMcContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.tileentity.PepsiMcTileEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager.trades.PepsiMcTrades;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager.villagerPOI.PepsiMcVillagerPOI;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.screen.BottlerScreen;
import net.minecraft.client.gui.ScreenManager;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.RenderTypeLookup;
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
    	PepsiMcItem.register(bus);
    	PepsiMcBlock.register(bus);
    	//PepsiMcFluid.register(bus);
    	PepsiMcTileEntity.register(bus);
    	PepsiMcContainer.register(bus);
    	PepsiMcRecipeType.register(bus);
    	PepsiMcStructure.register(bus);
    	PepsiMcProfession.register(bus);
    	PepsiMcVillagerPOI.register(bus);

    	MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, WorldEvents::oreGen);
    	MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, WorldEvents::structGen);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, PepsiMcTrades::loadTrades);

        MinecraftForge.EVENT_BUS.register(this);

	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		event.enqueueWork(()->{
			PepsiMcStructure.setup();
			/*RenderTypeLookup.setRenderLayer(PepsiMcBlock.PEPSI_FLUID_BLOCK.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(PepsiMcFluid.PEPSI_FLUID.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(PepsiMcFluid.PEPSI_FLOW.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(PepsiMcBlock.PEPSI_MAX_FLUID_BLOCK.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(PepsiMcFluid.PEPSI_MAX_FLUID.get(), RenderType.translucent());
			RenderTypeLookup.setRenderLayer(PepsiMcFluid.PEPSI_MAX_FLOW.get(), RenderType.translucent());*/
			ScreenManager.register(PepsiMcContainer.BOTTLER_CONTAINER.get(), BottlerScreen::new);
		});
		
	}
}
