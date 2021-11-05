package ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager.villagerPOI.PepsiMcVillagerPOI;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcProfession {
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.PROFESSIONS, "pepsimc");

	public static final RegistryObject<VillagerProfession> BOTTLING_OPERATOR = VILLAGER_PROFESSION.register("bottling_plant_operator",()->new VillagerProfession("bottling_plant_operator",PepsiMcVillagerPOI.BOTTLING_OPERATOR.get(), null, null, null));

	public static void register(IEventBus bus) {
		VILLAGER_PROFESSION.register(bus);
	}

}
