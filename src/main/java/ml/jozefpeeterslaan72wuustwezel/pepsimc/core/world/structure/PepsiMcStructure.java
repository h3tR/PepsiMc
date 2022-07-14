package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world.structure;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcStructure {/*
//TODO
	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = 
			DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES,"pepsimc");

	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> ABANDONED_BOTTLING_PLANT = 
			STRUCTURES.register("abandoned_bottling_plant", abandonedBottlingPlantStructure::new);
	
	//public static final RegistryObject<Structure<NoFeatureConfig>> EXCAVATION_SITE = 
//			STRUCTURES.register("excavation_site", ExcavationSite::new);
	
	public static void setup() {
		LandAndSpacing(ABANDONED_BOTTLING_PLANT.get(),new StructureFeatureConfiguration(100, 50, 1764348544),true);
	//	LandAndSpacing(EXCAVATION_SITE.get(),new StructureSeparationSettings(100, 50, 126431564),true);

	}
	
	public static <F extends StructureFeature<?>> void LandAndSpacing(F structure, StructureFeatureConfiguration structureSeparationSettings,
            boolean transformSurroundingLand) {

	//add our structures into the map in Structure class
	StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
	
	/*
	* Whether surrounding land will be modified automatically to conform to the bottom of the structure.
	* Basically, it adds land at the base of the structure like it does for Villages and Outposts.
	* Doesn't work well on structure that have pieces stacked vertically or change in heights.
	*
	*//*
		if (transformSurroundingLand) {

			StructureFeature.NOISE_AFFECTING_FEATURES =
				ImmutableList.<StructureFeature<?>>builder()
					.addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
					.add(structure)
					.build();
		}
	
	/*
	* This is the map that holds the default spacing of all structures.
	* Always add your structure to here so that other mods can utilize it if needed.
	*
	* However, while it does propagate the spacing to some correct dimensions from this map,
	* it seems it doesn't always work for code made dimensions as they read from this list beforehand.
	*
	* Instead, we will use the WorldEvent.Load event in ModWorldEvents to add the structure
	* spacing from this list into that dimension or to do dimension blacklisting properly.
	* We also use our entry in DimensionStructuresSettings.DEFAULTS in WorldEvent.Load as well.
	*
	* DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
	*//*
		StructureSettings.DEFAULTS =
				ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
				.putAll(StructureSettings.DEFAULTS)
				.put(structure, structureSeparationSettings)
				.build();
	
	/*
	* There are very few mods that relies on seeing your structure in the
	* noise settings registry before the world is made.
	*
	* You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the
	* NOISE_GENERATOR_SETTINGS loop below but that field only applies for the default overworld and
	* won't add to other worldtypes or dimensions (like amplified or Nether).
	* So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop
	* below instead if you must.
	*//*
		BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {

			Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = 
					settings.getValue().structureSettings().structureConfig();
		/*
		* Pre-caution in case a mod makes the structure map immutable like datapacks do.
		* I take no chances myself. You never know what another mods does...
		*
		* structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
		*//*
			if (structureMap instanceof ImmutableMap) {
				Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
				tempMap.put(structure, structureSeparationSettings);
				settings.getValue().structureSettings().structureConfig();
				
			} else {

				structureMap.put(structure, structureSeparationSettings);
			}
		});
	}
	public static void register(IEventBus bus) {

		STRUCTURES.register(bus);
	}*/
}
