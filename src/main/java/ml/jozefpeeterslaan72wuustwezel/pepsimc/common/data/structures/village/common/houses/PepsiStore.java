package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.structures.village.common.houses;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.dirty_workaround.LegacySJP;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.data.worldgen.ProcessorLists;


import net.minecraft.data.worldgen.DesertVillagePools;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.data.worldgen.SavannaVillagePools;
import net.minecraft.data.worldgen.SnowyVillagePools;
import net.minecraft.data.worldgen.TaigaVillagePools;

public class PepsiStore {
	public static void init() {
		PlainVillagePools.bootstrap();
		SnowyVillagePools.bootstrap();
		SavannaVillagePools.bootstrap();
		DesertVillagePools.bootstrap();
		TaigaVillagePools.bootstrap();
		for(String biome : new String[]{"plains", "snowy", "savanna", "desert", "taiga"}) {
			addToPool(new ResourceLocation("village/"+biome+"/houses"),
					new ResourceLocation("pepsimc:village/"+biome+"/houses/"+biome+"_pepsi_store_1"), 150);
		}
		for(String biome : new String[]{"plains", "snowy", "savanna", "desert", "taiga"}) {
			addToPool(new ResourceLocation("village/"+biome+"/houses"),
					new ResourceLocation("pepsimc:village/"+biome+"/houses/"+biome+"_pepsi_store_1"), 150);
		}
	}
	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight)
	{
		StructureTemplatePool old = BuiltinRegistries.TEMPLATE_POOL.get(pool);

		// Fixed seed to prevent inconsistencies between different worlds
		List<StructurePoolElement> shuffled;
		if(old!=null)
			shuffled = old.getShuffledTemplates(new Random(0));
		else
			shuffled = ImmutableList.of();
		
		Object2IntMap<StructurePoolElement> newPieces = new Object2IntLinkedOpenHashMap<>();
		for(StructurePoolElement p : shuffled)
			newPieces.computeInt(p, (StructurePoolElement pTemp, Integer i) -> (i==null?0: i)+1);

		newPieces.put(new LegacySJP(
				Either.left(toAdd), () -> ProcessorLists.EMPTY, StructureTemplatePool.Projection.RIGID
		), weight);

		List<Pair<StructurePoolElement, Integer>> newPieceList = newPieces.object2IntEntrySet().stream()
				.map(e -> Pair.of(e.getKey(), e.getIntValue()))
				.collect(Collectors.toList());

		ResourceLocation name = old.getName();

		Registry.register(BuiltinRegistries.TEMPLATE_POOL, pool, new StructureTemplatePool(pool, name, newPieceList));
	}
}
