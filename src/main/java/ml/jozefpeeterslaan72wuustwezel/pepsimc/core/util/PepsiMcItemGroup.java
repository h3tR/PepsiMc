package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PepsiMcItemGroup {
	 public static final CreativeModeTab PEPSIMC_TAB = new CreativeModeTab("PepsiMc") {
		 
	        @Override
	        public ItemStack makeIcon() {
	            return new ItemStack(PepsiMcItem.TOKEN.get());
	        }
	    };
}