package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PepsiMcItemGroup {
	 public static final CreativeModeTab PEPSIMC_TAB = new CreativeModeTab("PepsiMc") {
		 
	        @Override
	        public @NotNull ItemStack makeIcon() {
	            return new ItemStack(PepsiMcItem.TOKEN.get());
	        }
	    };
}