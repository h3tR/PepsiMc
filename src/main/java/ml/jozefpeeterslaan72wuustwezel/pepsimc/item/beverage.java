package ml.jozefpeeterslaan72wuustwezel.pepsimc.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

public class Beverage extends Item{

	public Beverage(Properties p_i48487_1_) {
		super(p_i48487_1_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public UseAction getUseAnimation(ItemStack p_77661_1_) {
	      return UseAction.DRINK;
	   }
 
}