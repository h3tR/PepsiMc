package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BeverageItem extends Item{

	private ItemStack item;
	public BeverageItem(Properties properties,ItemStack item) {
		super(properties);
		this.item = item;
	}
	
	@Override
	 public UseAnim getUseAnimation(ItemStack p_77661_1_) {
	      return UseAnim.DRINK;
	   }
	//player.addItem(this.item);

}
