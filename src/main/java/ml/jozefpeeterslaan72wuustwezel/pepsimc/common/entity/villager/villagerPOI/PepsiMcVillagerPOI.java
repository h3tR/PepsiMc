package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.villagerPOI;



import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcVillagerPOI {
	public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, "pepsimc");

	public static final RegistryObject<PointOfInterestType> BOTTLING_OPERATOR = POI.register("bottling_plant_operator",()->new PointOfInterestType("bottling_operator",PointOfInterestType.getBlockStates(PepsiMcBlock.BOTTLER.get()), 1, 1));

	
	public static void register(IEventBus bus) {
		POI.register(bus);
	}
}
