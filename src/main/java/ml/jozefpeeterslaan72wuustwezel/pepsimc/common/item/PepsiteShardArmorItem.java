package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

//import org.apache.logging.log4j.LogManager;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class PepsiteShardArmorItem extends Item {	

	private Item result;
    public PepsiteShardArmorItem(Properties properties,Item result) {
		super(properties);
		this.result = result;
	}
    
   @Override
public InteractionResult useOn(UseOnContext ctx) {
	   BlockPos pos = new BlockPos(ctx.getClickLocation());
	 //  LogManager.getLogger().warn(pos);
	   Level level = ctx.getLevel();
	   if(!level.isClientSide) {
	   Player plr = ctx.getPlayer();
	   Block clickedBlock = level.getBlockState(pos).getBlock();
	   if(clickedBlock.getTags().contains((new ResourceLocation("pepsimc", "shard_mendable")))){
		   ctx.getItemInHand().shrink(1);
		  plr.getInventory().add(plr.getInventory().selected, new ItemStack(this.result));
		  level.destroyBlock(pos, true);
		  EntityType.LIGHTNING_BOLT.spawn((ServerLevel)level, null, null, plr, pos, MobSpawnType.TRIGGERED, true, true);
	   }
	  }
	return super.useOn(ctx);
}

}