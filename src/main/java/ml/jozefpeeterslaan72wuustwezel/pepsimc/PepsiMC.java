package ml.jozefpeeterslaan72wuustwezel.pepsimc;



import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.BottlerScreen;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.fluid.PepsiMcFluid;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.PepsiMcContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.structures.village.common.houses.PepsiStore;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect.PepsiMcEffect;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.trades.PepsiMcTrades;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.villagerPOI.PepsiMcVillagerPOI;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.soundevent.PepsiMcSoundEvent;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world.structure.PepsiMcStructure;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("pepsimc")
public class PepsiMC {
	
	public PepsiMC() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	PepsiMcItem.register(bus);
    	PepsiMcBlock.register(bus);
    	PepsiMcFluid.register(bus);
    	PepsiMcBlockEntity.register(bus);
    	PepsiMcContainer.register(bus);
    	PepsiMcRecipeType.register(bus);
    	PepsiMcStructure.register(bus);
    	PepsiMcProfession.register(bus);
    	PepsiMcSoundEvent.register(bus);
    	PepsiMcVillagerPOI.register(bus);
    	PepsiMcEffect.register(bus);
    	bus.addListener(this::setup);
		bus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, PepsiMcTrades::loadTrades);
        MinecraftForge.EVENT_BUS.register(this);

	}
	
	 private void setup(final FMLCommonSetupEvent event) {
		 PepsimcNetwork.init();
	        event.enqueueWork(() -> {
	            PepsiMcStructure.setup();
	            PepsiStore.init();
	        });
	    }
	 
	private void doClientStuff(final FMLClientSetupEvent event) {
		event.enqueueWork(()->{
			ItemBlockRenderTypes.setRenderLayer(PepsiMcBlock.PEPSI_FLUID_BLOCK.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcFluid.PEPSI_FLUID.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcFluid.PEPSI_FLOW.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcBlock.PEPSI_MAX_FLUID_BLOCK.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcFluid.PEPSI_MAX_FLUID.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcFluid.PEPSI_MAX_FLOW.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcBlock.STEVIA_PLANT.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(PepsiMcBlock.STEVIA_CROP.get(), RenderType.cutout());


		});
		MenuScreens.register(PepsiMcContainer.BOTTLER_CONTAINER.get(), BottlerScreen::new);

	}

	
	
}
