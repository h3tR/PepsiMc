package ml.jozefpeeterslaan72wuustwezel.pepsimc.data.structures.village.common.houses;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.dirty_workaround.LegacySJP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.ProcessorLists;


public class PepsiStore {
	public static void init() {
		PlainsVillagePools.bootstrap();
		SnowyVillagePools.bootstrap();
		SavannaVillagePools.bootstrap();
		DesertVillagePools.bootstrap();
		TaigaVillagePools.bootstrap();
		for(String biome : new String[]{"plains", "snowy", "savanna", "desert", "taiga"}) {
			addToPool(new ResourceLocation("village/"+biome+"/houses"),
					new ResourceLocation("pepsimc:village/"+biome+"/houses/"+biome+"_pepsi_store_1"), 400);
		}
	}
	private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight)
	{
		JigsawPattern old = WorldGenRegistries.TEMPLATE_POOL.get(pool);

		// Fixed seed to prevent inconsistencies between different worlds
		List<JigsawPiece> shuffled;
		if(old!=null)
			shuffled = old.getShuffledTemplates(new Random(0));
		else
			shuffled = ImmutableList.of();

		Object2IntMap<JigsawPiece> newPieces = new Object2IntLinkedOpenHashMap<>();
		for(JigsawPiece p : shuffled)
			newPieces.computeInt(p, (JigsawPiece pTemp, Integer i) -> (i==null?0: i)+1);

		newPieces.put(new LegacySJP(
				Either.left(toAdd), () -> ProcessorLists.EMPTY, JigsawPattern.PlacementBehaviour.RIGID
		), weight);

		List<Pair<JigsawPiece, Integer>> newPieceList = newPieces.object2IntEntrySet().stream()
				.map(e -> Pair.of(e.getKey(), e.getIntValue()))
				.collect(Collectors.toList());

		ResourceLocation name = old.getName();

		Registry.register(WorldGenRegistries.TEMPLATE_POOL, pool, new JigsawPattern(pool, name, newPieceList));
		LogManager.getLogger().info(WorldGenRegistries.TEMPLATE_POOL.get(pool).getRandomTemplate(new Random()));
	}
}
