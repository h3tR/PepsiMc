package ml.jozefpeeterslaan72wuustwezel.pepsimc.util.tags;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class PepsiMcTags {
	public static class Items{
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_LIQUID = createTag("bottling_liquid");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_CONTAINER = createTag("bottling_container");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_LABEL = createTag("bottling_label");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLED_LIQUID = createTag("bottled_liquid");

		private static Tags.IOptionalNamedTag<Item> createTag(String name){
			return ItemTags.createOptional(new ResourceLocation("pepsimc", name));
		}

	}
}
