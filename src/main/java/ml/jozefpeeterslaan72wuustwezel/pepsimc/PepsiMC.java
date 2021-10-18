package ml.jozefpeeterslaan72wuustwezel.pepsimc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("pepsimc")
public class PepsiMC {

	public void PepsiMc() {
		
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	item.ITEMS.register(bus);
    	block.BLOCKS.register(bus);
    	
    	MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, worldgen::OreGen);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
