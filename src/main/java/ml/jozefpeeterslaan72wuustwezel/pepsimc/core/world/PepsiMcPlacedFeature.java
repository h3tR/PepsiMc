package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PepsiMcPlacedFeature {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY,"pepsimc");

    public static final RegistryObject<PlacedFeature> STEVIA_PLANT = PLACED_FEATURES.register("stevia_plant_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
           PepsiMcConfiguredFeature.STEVIA_PLANT, List.of(RarityFilter.onAverageOnceEvery(16),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PEPSITE_ORE = PLACED_FEATURES.register("pepsite_ore_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                    PepsiMcConfiguredFeature.PEPSITE_ORE,
                    List.of(CountPlacement.of(5),
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(20)))));

    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
