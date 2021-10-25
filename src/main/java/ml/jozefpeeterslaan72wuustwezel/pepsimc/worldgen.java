package ml.jozefpeeterslaan72wuustwezel.pepsimc;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.block;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class worldgen {
	
	public static void OreGen(final BiomeLoadingEvent event)
	{
		if(!(event.getCategory().equals(Biome.Category.NETHER)||event.getCategory().equals(Biome.Category.THEEND)))
		{
			genOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, block.PEPSITEORE.get().defaultBlockState(), 12, 5, 50, 45);
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
}

