package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import java.util.List;
import java.util.Set;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.GenerationStep;

@Mod.EventBusSubscriber(modid = "pepsimc")
public class WorldEvents {

	
	@SubscribeEvent
	public static void Generate(final BiomeLoadingEvent event) {
		List<Holder<PlacedFeature>> orestep =
				event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

		orestep.add(PepsiMcPlacedFeature.PEPSITE_ORE_PLACED);
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

		if (types.contains(BiomeDictionary.Type.PLAINS)) {
			List<Holder<PlacedFeature>> vegetalstep =
					event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

			vegetalstep.add(PepsiMcPlacedFeature.STEVIA_PLANT_PLACED);
		}
	}
}

