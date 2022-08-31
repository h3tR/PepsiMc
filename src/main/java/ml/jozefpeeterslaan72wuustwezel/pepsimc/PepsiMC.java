package ml.jozefpeeterslaan72wuustwezel.pepsimc;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.*;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.fluid.PepsiMcFluid;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.PepsiMcMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect.PepsiMcEffect;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.PepsiMcBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcVillagerPOI;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.soundevent.PepsiMcSoundEvent;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world.WorldEvents;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod("pepsimc")
public class PepsiMC {
	
	public PepsiMC() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	PepsiMcItem.register(bus);
    	PepsiMcBlock.register(bus);
    	PepsiMcFluid.register(bus);
    	PepsiMcBlockEntity.register(bus);
    	PepsiMcMenu.register(bus);
    	PepsiMcRecipeType.register(bus);
    	PepsiMcProfession.register(bus);
    	PepsiMcSoundEvent.register(bus);
    	PepsiMcVillagerPOI.register(bus);
    	PepsiMcEffect.register(bus);
    	bus.addListener(this::setup);
		bus.addListener(this::doClientStuff);
		GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);


	}
	
	 private void setup(final FMLCommonSetupEvent event) {
		 PepsimcNetwork.init();
		 event.enqueueWork(() -> {
			 PepsiMcEffect.registerPotionRecipes();
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
		MenuScreens.register(PepsiMcMenu.BOTTLER_MENU.get(), BottlerScreen::new);
		MenuScreens.register(PepsiMcMenu.RECYCLER_MENU.get(), RecyclerScreen::new);
		MenuScreens.register(PepsiMcMenu.FLAVOR_MACHINE_MENU.get(), FlavorMachineScreen::new);
		MenuScreens.register(PepsiMcMenu.CENTRIFUGE_MENU.get(), CentrifugeScreen::new);
		MenuScreens.register(PepsiMcMenu.AUTOMATED_BOTTLER_MENU.get(), AutomatedBottlerScreen::new);
		MenuScreens.register(PepsiMcMenu.GENERATOR_CONTAINER.get(), GeneratorScreen::new);

	}

	
	
}
