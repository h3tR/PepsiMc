package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import java.util.*;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = "pepsimc")
public class WorldEvents {

	@SubscribeEvent
	public static void Generate(final BiomeLoadingEvent event) {
		List<Holder<PlacedFeature>> OreStep =
				event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

		OreStep.add(PepsiMcPlacedFeature.PEPSITE_ORE_PLACED);
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

		if (types.contains(BiomeDictionary.Type.PLAINS)) {
			List<Holder<PlacedFeature>> VegetationStep =
					event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

			VegetationStep.add(PepsiMcPlacedFeature.STEVIA_PLANT_PLACED);
		}
	}

	@SubscribeEvent
	public static void addVillageTemplate(final ServerAboutToStartEvent event){
		//TODO
		Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registry.TEMPLATE_POOL_REGISTRY).orElseThrow();
		Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registry.PROCESSOR_LIST_REGISTRY).orElseThrow();

		String[] biomes = {"plains","snowy","savanna","desert","taiga"};
		for(int i=0;i<biomes.length;i++){
			addBuildingToPool(templatePoolRegistry,processorListRegistry,new ResourceLocation("village/"+biomes[i]+"/houses"),"pepsimc:village/"+biomes[i]+"/houses/"+biomes[i]+"_pepsi_store_1", 250);
		}
	}



	private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
										  Registry<StructureProcessorList> processorListRegistry,
										  ResourceLocation poolRL,
										  String nbtPieceRL,
										  int weight) {

		// Grabs the processor list we want to use along with our piece.
		// This is a requirement as using the ProcessorLists.EMPTY field will cause the game to throw errors.
		// The reason why is the empty processor list in the world's registry is not the same instance as in that field once the world is started up.
		Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolderOrThrow(ResourceKey.create(
				Registry.PROCESSOR_LIST_REGISTRY, new ResourceLocation("minecraft", "empty")));

		// Grab the pool we want to add to
		StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
		if (pool == null) return;

		// Grabs the nbt piece and creates a SinglePoolElement of it that we can add to a structure's pool.
		// Use .legacy( for villages/outposts and .single( for everything else
		SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL, emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

		// Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's templates field public for us to see.
		// Weight is handled by how many times the entry appears in this list.
		// We do not need to worry about immutability as this field is created using Lists.newArrayList(); which makes a mutable list.
		for (int i = 0; i < weight; i++) {
			pool.templates.add(piece);
		}
		pool.templates.forEach(t->{
			LogManager.getLogger().debug(t);
		});

		// Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's rawTemplates field public for us to see.
		// This list of pairs of pieces and weights is not used by vanilla by default but another mod may need it for efficiency.
		// So lets add to this list for completeness. We need to make a copy of the array as it can be an immutable list.
		List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
		listOfPieceEntries.add(new Pair<>(piece, weight));
		pool.rawTemplates = listOfPieceEntries;
	}

}

