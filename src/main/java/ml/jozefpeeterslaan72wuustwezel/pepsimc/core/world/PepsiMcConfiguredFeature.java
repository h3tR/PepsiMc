package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.configuration.CommonConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public class PepsiMcConfiguredFeature {
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> STEVIA_PLANT_CONFIG =  FeatureUtils.register("stevia_plant_configured",Feature.FLOWER,
			new RandomPatchConfiguration(CommonConfig.STEVIA_SPAWNRATE.get(), 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(BlockStateProvider.simple(PepsiMcBlock.STEVIA_PLANT.get())))));


	public static final List<OreConfiguration.TargetBlockState> PEPSITE_ORES = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, PepsiMcBlock.PEPSITE_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, PepsiMcBlock.DEEPSLATE_PEPSITE_ORE.get().defaultBlockState()));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PEPSITE_ORE_CONFIG = FeatureUtils.register("pepsite_ore",
			Feature.ORE, new OreConfiguration(PEPSITE_ORES, 9));
}
