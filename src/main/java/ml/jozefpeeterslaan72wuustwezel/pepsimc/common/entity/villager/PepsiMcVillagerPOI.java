package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager;



import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcVillagerPOI {
	public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, "pepsimc");

	public static final RegistryObject<PoiType> BOTTLING_OPERATOR = POI.register("bottling_plant_operator",()->new PoiType("bottling_operator",PoiType.getBlockStates(PepsiMcBlock.BOTTLER.get()), 1, 1));

	
	public static void register(IEventBus bus) {
		POI.register(bus);
	}
}
