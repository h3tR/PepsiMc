package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.data.worldgen.Features;

public class PepsiMcConfiguredFeature {
	public static final ConfiguredFeature<?, ?> STEVIA_PLANT_CONFIG = Feature.FLOWER.configured((
            new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(PepsiMcBlock.STEVIA_PLANT.get().defaultBlockState()),
          SimpleBlockPlacer.INSTANCE)).tries(6).build()).decorated(Features.Decorators.HEIGHTMAP).count(1);
}
