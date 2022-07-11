package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world.biomemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PepsiMcBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, "pepsimc");

    public static RegistryObject<Codec<PepsiMcVegetalMod>> VEGETAL_MODIFIER = BIOME_MODIFIERS.register("vegetal", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(PepsiMcVegetalMod::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(PepsiMcVegetalMod::feature)
            ).apply(builder, PepsiMcVegetalMod::new)));

    public static RegistryObject<Codec<PepsiMcOreMod>> ORE_MODIFIER = BIOME_MODIFIERS.register("ores", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(PepsiMcOreMod::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(PepsiMcOreMod::feature)
            ).apply(builder, PepsiMcOreMod::new)));

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }
}
