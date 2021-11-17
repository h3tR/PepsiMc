package ml.jozefpeeterslaan72wuustwezel.pepsimc.world.structure;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

//import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.structures.ExcavationSite;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.data.structures.abandonedBottlingPlantStructure;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcStructure {
	public static final DeferredRegister<Structure<?>> STRUCTURES = 
			DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES,"pepsimc");

	public static final RegistryObject<Structure<NoFeatureConfig>> ABANDONED_BOTTLING_PLANT = 
			STRUCTURES.register("abandoned_bottling_plant", abandonedBottlingPlantStructure::new);
	
//	public static final RegistryObject<Structure<NoFeatureConfig>> EXCAVATION_SITE = 
//			STRUCTURES.register("excavation_site", ExcavationSite::new);
	
	public static void setup() {
		LandAndSpacing(ABANDONED_BOTTLING_PLANT.get(),new StructureSeparationSettings(100, 50, 1764348544),true);
		//LandAndSpacing(EXCAVATION_SITE.get(),new StructureSeparationSettings(100, 50, 126431564),true);

	}
	
	public static <F extends Structure<?>> void LandAndSpacing(F structure, StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand) {

	//add our structures into the map in Structure class
	Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
	
	/*
	* Whether surrounding land will be modified automatically to conform to the bottom of the structure.
	* Basically, it adds land at the base of the structure like it does for Villages and Outposts.
	* Doesn't work well on structure that have pieces stacked vertically or change in heights.
	*
	*/
		if (transformSurroundingLand) {

			Structure.NOISE_AFFECTING_FEATURES =
				ImmutableList.<Structure<?>>builder()
					.addAll(Structure.NOISE_AFFECTING_FEATURES)
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
	*/
		DimensionStructuresSettings.DEFAULTS =
				ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
				.putAll(DimensionStructuresSettings.DEFAULTS)
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
	*/
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {

			Map<Structure<?>, StructureSeparationSettings> structureMap = 
					settings.getValue().structureSettings().structureConfig();
		/*
		* Pre-caution in case a mod makes the structure map immutable like datapacks do.
		* I take no chances myself. You never know what another mods does...
		*
		* structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
		*/
			if (structureMap instanceof ImmutableMap) {
				Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
				tempMap.put(structure, structureSeparationSettings);
				settings.getValue().structureSettings().structureConfig();
				
			} else {

				structureMap.put(structure, structureSeparationSettings);
			}
		});
	}
	public static void register(IEventBus bus) {

		STRUCTURES.register(bus);
	}
}
