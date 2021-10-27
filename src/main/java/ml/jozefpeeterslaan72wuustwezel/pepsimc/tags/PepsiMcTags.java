package ml.jozefpeeterslaan72wuustwezel.pepsimc.tags;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class PepsiMcTags {
	public static class Items{
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_LIQUID = createTag("bottlingliquid");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_CONTAINER = createTag("bottlingcontainer");
		
		public static final Tags.IOptionalNamedTag<Item> BOTTLING_LABEL = createTag("bottlinglabel");
		
		private static Tags.IOptionalNamedTag<Item> createTag(String name){
			return ItemTags.createOptional(new ResourceLocation("pepsimc", name));
		}
	}
}
