package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.mojang.datafixers.util.Pair;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.mixin.StructureTemplatePoolAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.pools.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.apache.logging.log4j.LogManager;

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

	@SubscribeEvent
	public static void addTemplate(ServerStartedEvent event){
		RegistryAccess registryAccess = event.getServer().registryAccess();
		String[] biomes = {"plains","snowy","savanna","desert","taiga"};
		for(int i=0;i<biomes.length;i++){
			addStructureToVillageConfig(registryAccess, "village/"+biomes[i]+"/houses", new ResourceLocation("pepsimc", "village/"+biomes[i]+"/"+biomes[i]+"_pepsi_store_1"), 10);
		}
	}

	private static void addStructureToVillageConfig(RegistryAccess registryAccess, String villagePiece, ResourceLocation Structure, int weight) {

		Holder<StructureProcessorList> emptyProcessorList = registryAccess.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getHolderOrThrow(ResourceKey.create(Registry.PROCESSOR_LIST_REGISTRY, new ResourceLocation("minecraft", "empty")));
		LegacySinglePoolElement piece = StructurePoolElement.legacy(Structure.toString(), emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);
		StructureTemplatePool pool = registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).getOptional(new ResourceLocation(villagePiece)).orElse(null);
		if (pool != null) {
			var poolAccessor = (StructureTemplatePoolAccessor) pool;
			// pretty sure this can be an immutable list (when datapacked) so gotta make a copy to be safe.
			List<StructurePoolElement> listOfPieces = new ArrayList<>(poolAccessor.getTemplates());
			for (int i = 0; i < weight; i++) {
				listOfPieces.add(piece);
			}
			Collections.shuffle(listOfPieces);
			poolAccessor.setTemplates(listOfPieces);

			List<Pair<StructurePoolElement, Integer>> listOfWeightedPieces = new ArrayList<>(poolAccessor.getRawTemplates());
			listOfWeightedPieces.add(new Pair<>(piece, weight));
			poolAccessor.setRawTemplates(listOfWeightedPieces);

			poolAccessor.getTemplates().forEach((h)->LogManager.getLogger().debug(h.toString()));
			LogManager.getLogger().debug("next");
		}
	}
}

