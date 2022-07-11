package ml.jozefpeeterslaan72wuustwezel.pepsimc.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	    @SubscribeEvent
	    public static void gatherData(GatherDataEvent event) {
	        DataGenerator generator = event.getGenerator();
	        if (event.includeClient()) {
	            generator.addProvider(true,new ItemModel(generator, event.getExistingFileHelper()));
	        }
	    }
	
}
