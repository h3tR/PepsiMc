package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

import net.minecraft.world.item.Item.Properties;

public class BeverageItem extends Item{

	public BeverageItem(Properties p_i48487_1_) {
		super(p_i48487_1_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public UseAnim getUseAnimation(ItemStack p_77661_1_) {
	      return UseAnim.DRINK;
	   }
}
