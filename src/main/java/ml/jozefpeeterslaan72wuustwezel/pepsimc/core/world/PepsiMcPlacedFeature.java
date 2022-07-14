package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PepsiMcPlacedFeature {
    public static final Holder<PlacedFeature> STEVIA_PLANT_PLACED = PlacementUtils.register("pink_rose_placed",
            PepsiMcConfiguredFeature.STEVIA_PLANT_CONFIG, RarityFilter.onAverageOnceEvery(16),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());


    public static final Holder<PlacedFeature> PEPSITE_ORE_PLACED = PlacementUtils.register("pepsite_ore_placed",
            PepsiMcConfiguredFeature.PEPSITE_ORE_CONFIG, List.of(CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.aboveBottom(-80),
                            VerticalAnchor.aboveBottom(80)),
                    BiomeFilter.biome())
    );
}
