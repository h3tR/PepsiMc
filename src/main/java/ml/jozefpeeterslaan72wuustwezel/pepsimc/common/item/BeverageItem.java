package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BeverageItem extends Item{

	protected ItemStack item;
	public BeverageItem(Properties properties,ItemStack item) {
		super(properties);
		this.item = item;
	}
	
	@Override
	 public @NotNull UseAnim getUseAnimation(@NotNull ItemStack p_77661_1_) {
	      return UseAnim.DRINK;
	   }
	
	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull LivingEntity plr) {
		Player player = plr instanceof Player ? (Player)plr : null;
		
		 if (player != null) {
	        player.awardStat(Stats.ITEM_USED.get(this));
	        Inventory inv = player.getInventory();
	        if(inv.getSlotWithRemainingSpace(this.item)!=-1) {
	        	inv.getItem(inv.getSlotWithRemainingSpace(this.item)).grow(1);
	        	 inv.setChanged();
	        }else if(inv.getFreeSlot()!=-1) {
	        	inv.setItem(inv.getFreeSlot(),this.item);
	        	 inv.setChanged();
	        } else {
	        	inv.add(this.item);
	        	inv.setChanged();
	        }
	       
	      }
		return super.finishUsingItem(itemStack, world, plr);
	}
}
