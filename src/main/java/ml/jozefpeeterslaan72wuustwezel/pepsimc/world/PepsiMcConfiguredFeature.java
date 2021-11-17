package ml.jozefpeeterslaan72wuustwezel.pepsimc.world;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;

public class PepsiMcConfiguredFeature {
	public static final ConfiguredFeature<?, ?> STEVIA_PLANT_CONFIG = Feature.FLOWER.configured((
            new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(PepsiMcBlock.STEVIA_PLANT.get().defaultBlockState()),
          SimpleBlockPlacer.INSTANCE)).tries(12).build()).decorated(Features.Placements.HEIGHTMAP).count(1);
}
