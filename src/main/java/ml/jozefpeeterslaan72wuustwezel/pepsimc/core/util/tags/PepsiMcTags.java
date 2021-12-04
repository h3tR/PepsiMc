package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags;

import net.minecraft.world.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;

public class PepsiMcTags {
	public static class Items{
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_LIQUID = createTag("bottling_liquid");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_CONTAINER = createTag("bottling_container");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_LABEL = createTag("bottling_label");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLED_LIQUID = createTag("bottled_liquid");

		public static final Tags.IOptionalNamedTag<Item> RECYCLABLE = createTag("recyclable");

		public static final Tags.IOptionalNamedTag<Item> RECYCLED = createTag("recycled");
		
		public static final Tags.IOptionalNamedTag<Item> RECYCLING_CATALYST = createTag("recycing_catalyst");

		public static final Tags.IOptionalNamedTag<Item> FLAVOR = createTag("flavor");

		public static final Tags.IOptionalNamedTag<Item> FLAVORED = createTag("flavored");
		
		public static final Tags.IOptionalNamedTag<Item> EXTRACTED = createTag("extracted");
		
		private static Tags.IOptionalNamedTag<Item> createTag(String name){
			return ItemTags.createOptional(new ResourceLocation("pepsimc", name));
		}

	}
}
