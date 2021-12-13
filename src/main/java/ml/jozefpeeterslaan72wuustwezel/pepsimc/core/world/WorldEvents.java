package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;

import com.mojang.serialization.Codec;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world.structure.PepsiMcStructure;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = "pepsimc")
public class WorldEvents {
	
    public static final List<ConfiguredFeature<?, ?>> OVERWORLD_ORES = new ArrayList<>();

	
	@SubscribeEvent
	public static void Generate(final BiomeLoadingEvent event)
	{
		if(!(event.getCategory().equals(Biome.BiomeCategory.NETHER)||event.getCategory().equals(Biome.BiomeCategory.THEEND)))
		{
			genOre(event, OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, PepsiMcBlock.PEPSITE_ORE.get(), 5, 50, 5, "pepsite_ore");
			genOre(event, OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, PepsiMcBlock.DEEPSLATE_PEPSITE_ORE.get(), 5, 20, 5, "deepslate_pepsite_ore");

		}
		genStruct(event, BiomeDictionary.Type.PLAINS, PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get().configured(FeatureConfiguration.NONE));
		genStruct(event, BiomeDictionary.Type.SANDY, PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get().configured(FeatureConfiguration.NONE));
	//	genStruct(event, BiomeDictionary.Type.PLAINS, PepsiMcStructure.EXCAVATION_SITE.get().configured(IFeatureConfig.NONE));
	//	genStruct(event, BiomeDictionary.Type.SANDY, PepsiMcStructure.EXCAVATION_SITE.get().configured(IFeatureConfig.NONE));
		genFlowers(event,PepsiMcConfiguredFeature.STEVIA_PLANT_CONFIG);
	}
	private static void genOre(final BiomeLoadingEvent event, RuleTest fillerType, Block block, int vein, int max, int count, String name)
	{
		event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,Feature.ORE.configured(
				 new OreConfiguration(
						 List.of(OreConfiguration.target(fillerType, block.defaultBlockState())),
						 vein)).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(max)).squared().count(count));
	}
	
			 
	 
	private static void genFlowers(final BiomeLoadingEvent event, ConfiguredFeature<?, ?> feature)
	{
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.PLAINS)) {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

            base.add(() -> feature);
        }
	}
	
	public static void genStruct(final BiomeLoadingEvent event,Type biome, ConfiguredStructureFeature<?, ?> Configured) {

		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(biome)) {
            List<Supplier<ConfiguredStructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

            structures.add(() -> Configured);
        }
       
	}
	
	@SubscribeEvent
	@SuppressWarnings("resource")
	public static void DimSpace(final WorldEvent.Load event) {

		if(event.getWorld() instanceof ServerLevel) {
			ServerLevel serverWorld = (ServerLevel) event.getWorld();
			try {
                Method GETCODEC_METHOD =
                        ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                @SuppressWarnings("unchecked")
				ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(
                        (Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));

                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) {

                    return;
                }
            } catch (Exception e) {
            	LogManager.getLogger().error("Was unable to check if " + serverWorld.dimension().location()
                        + " is using Terraforged's ChunkGenerator.");
            }
			
			 if (serverWorld.getChunkSource().generator instanceof FlatLevelSource &&
	                    serverWorld.dimension().equals(Level.OVERWORLD)) {

	                return;
	            }
			 
			 addToStructConfig(PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get(),serverWorld);
			 //addToStructConfig(PepsiMcStructure.EXCAVATION_SITE.get(),serverWorld);

		}
	}
	@SuppressWarnings("resource")
	private static void addToStructConfig(StructureFeature<?> struct,ServerLevel server) {
		Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(server.getChunkSource().generator.getSettings().structureConfig());
        tempMap.putIfAbsent(struct, StructureSettings.DEFAULTS.get(struct));
        server.getChunkSource().generator.getSettings().structureConfig().putAll(tempMap);
	}

	 
	 @Mod.EventBusSubscriber(modid = "pepsimc", bus = Bus.FORGE)
	    public static class ForgeBusSubscriber {
	        @SubscribeEvent
	        public static void biomeLoading(BiomeLoadingEvent event) {
	            List<Supplier<ConfiguredFeature<?, ?>>> features = event.getGeneration()
	                    .getFeatures(Decoration.UNDERGROUND_ORES);

	            switch(event.getCategory()) {
	                default -> WorldEvents.OVERWORLD_ORES.forEach(ore -> features.add(() -> ore));
	            }
	        }
	    }
}

