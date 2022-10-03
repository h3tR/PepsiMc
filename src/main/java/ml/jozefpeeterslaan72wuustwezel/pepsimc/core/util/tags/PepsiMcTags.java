package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class PepsiMcTags {
	public static class Items{
		
		public static final TagKey<Item> BOTTLING_LIQUID = createTag("bottling_liquid");
		public static final TagKey<Item> BOTTLING_CONTAINER = createTag("bottling_container");
		public static final TagKey<Item> BOTTLING_LABEL = createTag("bottling_label");
		public static final TagKey<Item> BOTTLED_LIQUID = createTag("bottled_liquid");
		public static final TagKey<Item> RECYCLABLE = createTag("recyclable");
		public static final TagKey<Item> RECYCLED = createTag("recycled");
		public static final TagKey<Item> RECYCLING_CATALYST = createTag("recycling_catalyst");

		public static final TagKey<Item> FLAVOR = createTag("flavor");
		public static final TagKey<Item> FLAVORED = createTag("flavored");
		
		public static final TagKey<Item> EXTRACTED = createTag("extracted");
		public static final TagKey<Item> EXTRACTION_BYPRODUCT = createTag("extraction_byproduct");

		
		public static final TagKey<Fluid> SHARD_MENDABLE = createFluidTag("shard_mendable");

		
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
}
