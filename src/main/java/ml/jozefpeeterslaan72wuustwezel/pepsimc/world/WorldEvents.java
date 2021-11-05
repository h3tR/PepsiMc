package ml.jozefpeeterslaan72wuustwezel.pepsimc.world;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;

import com.mojang.serialization.Codec;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.world.structure.PepsiMcStructure;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = "pepsimc")
public class WorldEvents {
	
	public static void oreGen(final BiomeLoadingEvent event)
	{
		if(!(event.getCategory().equals(Biome.Category.NETHER)||event.getCategory().equals(Biome.Category.THEEND)))
		{
			genOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, PepsiMcBlock.PEPSITEORE.get().defaultBlockState(), 5, 5, 50, 20);
		}
	}
	
	private static void genOre(final BiomeLoadingEvent event, RuleTest fillerType, BlockState state, int vein, int min, int max, int count)
	{
		event.getGeneration().addFeature(Decoration.UNDERGROUND_ORES, 
				Feature.ORE.configured(new OreFeatureConfig(fillerType, state, vein))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(min,0,max)))
						.squared()
						.count(count));
	}
	
	
	public static void structGen(final BiomeLoadingEvent event)
	{
		RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.PLAINS)) {
            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

            structures.add(() -> PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get().configured(IFeatureConfig.NONE));
        }
	}
	
	
	
	
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void DimSpace(final WorldEvent.Load event) {
		if(event.getWorld() instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) event.getWorld();
			try {
                Method GETCODEC_METHOD =
                        ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                @SuppressWarnings({ "unchecked", "resource" })
				ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(
                        (Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));

                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception e) {
                LogManager.getLogger().error("Was unable to check if " + serverWorld.dimension().location()
                        + " is using Terraforged's ChunkGenerator.");
            }
			
			
			
			Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
	            tempMap.putIfAbsent(PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get(), DimensionStructuresSettings.DEFAULTS.get(PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get()));
	            serverWorld.getChunkSource().generator.getSettings().structureConfig().putAll(tempMap);
		}
	}
	
}

