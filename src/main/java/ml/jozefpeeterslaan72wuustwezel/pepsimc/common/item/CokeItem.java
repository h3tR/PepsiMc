package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class CokeItem extends BeverageItem {

	public CokeItem(Properties properties) {
		super(properties, ItemStack.EMPTY);
	}
	

	@Override
	public ItemStack finishUsingItem(ItemStack itemStack, Level world, LivingEntity plr) {
		Player player = plr instanceof Player ? (Player)plr : null;
		 if (player != null) {
	        player.awardStat(Stats.ITEM_USED.get(this));
	        player.hurt(DamageSource.explosion(player.level.explode(plr, plr.xo, plr.yo, plr.zo, 4.0F, Explosion.BlockInteraction.DESTROY)), Float.MAX_VALUE);
		 }
		return super.finishUsingItem(itemStack, world, plr);
	}
}
