package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.mixin.SingleJigsawAccess;
import net.minecraft.core.Holder;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;

import static net.minecraft.data.BuiltinRegistries.TEMPLATE_POOL;

@Mod.EventBusSubscriber(modid = "pepsimc")
public class WorldEvents {

	@SubscribeEvent
	public static void Generate(final BiomeLoadingEvent event) {
		List<Holder<PlacedFeature>> OreStep =
				event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

		OreStep.add(PepsiMcPlacedFeature.PEPSITE_ORE_PLACED);
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(event.getName()));
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

		if (types.contains(BiomeDictionary.Type.PLAINS)) {
			List<Holder<PlacedFeature>> VegetationStep =
					event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

			VegetationStep.add(PepsiMcPlacedFeature.STEVIA_PLANT_PLACED);
		}
	}
	public static void VillageInit(ParallelDispatchEvent event)
	{
		LogUtils.getLogger().debug("Init Village");

		event.enqueueWork(WorldEvents::addVillageTemplate);

	}

	private static void addVillageTemplate(){
		//TODO
		PlainVillagePools.bootstrap();
		SnowyVillagePools.bootstrap();
		SavannaVillagePools.bootstrap();
		DesertVillagePools.bootstrap();
		TaigaVillagePools.bootstrap();

		LogUtils.getLogger().debug("Adding Village templates");
		String[] biomes = {"plains","snowy","savanna","desert","taiga"};
		for (String biome : biomes) {
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation("pepsimc:village/" + biome + "/houses/" + biome + "_pepsi_store_1"), 150);
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation("pepsimc:village/" + biome + "/houses/" + biome + "_pepsi_store_1"), 150);
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation("pepsimc:village/" + biome + "/houses/" + biome + "_pepsi_store_1"), 150);
			addToPool(new ResourceLocation("village/" + biome + "/houses"), new ResourceLocation("pepsimc:village/" + biome + "/houses/" + biome + "_pepsi_store_1"), 150);

		}
		//TEMPLATE_POOL.get(new ResourceLocation("village/desert/houses")).getShuffledTemplates(new Random(0)).forEach(e->LogUtils.getLogger().debug(e.toString()));
	}




	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight)
	{
		StructureTemplatePool old = TEMPLATE_POOL.get(pool);
		int id = TEMPLATE_POOL.getId(old);

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
		((WritableRegistry<StructureTemplatePool>) TEMPLATE_POOL).registerOrOverride(
				OptionalInt.of(id),
				ResourceKey.create(TEMPLATE_POOL.key(), name),
				new StructureTemplatePool(pool, name, newPieceList),
				Lifecycle.stable()
		);
	}
}

