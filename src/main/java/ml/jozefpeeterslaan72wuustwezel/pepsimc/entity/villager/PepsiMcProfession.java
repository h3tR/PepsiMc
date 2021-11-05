package ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcProfession {
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.PROFESSIONS, "pepsimc");

	public static final Registry<VillagerProfession> BOTTLING_OPERATOR = VILLAGER_PROFESSION.register("bottling_plant_operator",()->new VillagerProfession("bottling_plant_operator", PointOfInterestType.BOTTLING_OPERATOR)));
}
