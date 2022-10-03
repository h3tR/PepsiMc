package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.mixin.SingleJigsawAccess;
import net.minecraft.core.Holder;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.*;
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
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

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
	public static void VillageInit(ParallelDispatchEvent event)
	{

		event.enqueueWork(WorldEvents::addVillageTemplate);

	}

	private static void addVillageTemplate(){
		//TODO
		PlainVillagePools.bootstrap();
		SnowyVillagePools.bootstrap();
		SavannaVillagePools.bootstrap();
		DesertVillagePools.bootstrap();
		TaigaVillagePools.bootstrap();


		String[] biomes = {"plains","snowy","savanna","desert","taiga"};
		for (String biome : biomes) {
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation("pepsimc:village/" + biome + "/houses/" + biome + "_pepsi_store_1"), 500);
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


		// Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's rawTemplates field public for us to see.
		// This list of pairs of pieces and weights is not used by vanilla by default but another mod may need it for efficiency.
		// So let's add to this list for completeness. We need to make a copy of the array as it can be an immutable list.
		List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
		listOfPieceEntries.add(new Pair<>(piece, weight));
		pool.rawTemplates = listOfPieceEntries;
	}
	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight)
	{
		StructureTemplatePool old = BuiltinRegistries.TEMPLATE_POOL.get(pool);
		int id = BuiltinRegistries.TEMPLATE_POOL.getId(old);

		// Fixed seed to prevent inconsistencies between different worlds
		List<StructurePoolElement> shuffled;
		if(old!=null)
			shuffled = old.getShuffledTemplates(new Random(0));
		else
			shuffled = ImmutableList.of();
		Object2IntMap<StructurePoolElement> newPieces = new Object2IntLinkedOpenHashMap<>();
		for(StructurePoolElement p : shuffled)
			newPieces.computeInt(p, (StructurePoolElement pTemp, Integer i) -> (i==null?0: i)+1);
		newPieces.put(SingleJigsawAccess.construct(
				Either.left(toAdd), ProcessorLists.EMPTY, StructureTemplatePool.Projection.RIGID
		), weight);
		List<Pair<StructurePoolElement, Integer>> newPieceList = newPieces.object2IntEntrySet().stream()
				.map(e -> Pair.of(e.getKey(), e.getIntValue()))
				.collect(Collectors.toList());

		ResourceLocation name = old.getName();
		((WritableRegistry<StructureTemplatePool>)BuiltinRegistries.TEMPLATE_POOL).registerOrOverride(
				OptionalInt.of(id),
				ResourceKey.create(BuiltinRegistries.TEMPLATE_POOL.key(), name),
				new StructureTemplatePool(pool, name, newPieceList),
				Lifecycle.stable()
		);
	}
}

