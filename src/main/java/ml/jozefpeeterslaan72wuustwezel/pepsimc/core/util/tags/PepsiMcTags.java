package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcTags {
	public static class Items{
		
		public static final TagKey<Item> BOTTLING_LIQUID = createTag("bottling_liquid");
		public static final TagKey<Item> BOTTLING_CONTAINER = createTag("bottling_container");
		public static final TagKey<Item> BOTTLING_LABEL = createTag("bottling_label");
		public static final TagKey<Item> BOTTLED_LIQUID = createTag("bottled_liquid");
		public static final TagKey<Item> RECYCLABLE = createTag("recyclable");
		public static final TagKey<Item> RECYCLED = createTag("recycled");
		public static final TagKey<Item> RECYCLING_CATALYST = createTag("recycling_catalyst");

		public static final TagKey<Item> PEPSI_VARIANT = createTag("pepsi_unflavored");
		public static final TagKey<Item> FLAVOR = createTag("flavor");
		public static final TagKey<Item> PEPSI_VARIANT_FLAVOR = createTag("flavored");
		
		public static final TagKey<Item> EXTRACTED = createTag("extracted");
		public static final TagKey<Item> EXTRACTION_BYPRODUCT = createTag("extraction_byproduct");

		
		public static final TagKey<Fluid> SHARD_MENDABLE = createFluidTag("shard_mendable");
		public static final TagKey<Fluid> PEPSI_TYPE_FLUID = createFluidTag("pepsi_type_fluid");

		
		private static TagKey<Item> createTag(String name){
			return ItemTags.create(new ResourceLocation("pepsimc", name));
		}
		
		@SuppressWarnings("unused")
		private static TagKey<Block> createBlockTag(String name){
			return BlockTags.create(new ResourceLocation("pepsimc", name));
		}
		
		private static TagKey<Fluid> createFluidTag(String name){
			return FluidTags.create(new ResourceLocation("pepsimc", name));
		}

	}
	public static class Structures{
		public static final TagKey<ConfiguredStructureFeature<?, ?>> ABANDONED_BOTTLING_PLANT = createStructureTag("on_abandoned_bottling_plant_maps");
		private static TagKey<ConfiguredStructureFeature<?, ?>> createStructureTag(String name){
			return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(name));
		}
	}
}
