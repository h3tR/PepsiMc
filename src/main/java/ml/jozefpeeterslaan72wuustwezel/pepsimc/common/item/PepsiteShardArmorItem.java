package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import org.apache.logging.log4j.LogManager;


//import org.apache.logging.log4j.LogManager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class PepsiteShardArmorItem extends Item {	

	private final Item result;
    public PepsiteShardArmorItem(Properties properties,Item result) {
		super(properties);
		this.result = result;
	}
    

   @Override
   public InteractionResultHolder<ItemStack> use(Level lvl, Player plr, InteractionHand hand) {
	   LogManager.getLogger().debug("use");
	      ItemStack itemstack = plr.getItemInHand(hand);
	      BlockHitResult blockhitresult = getPlayerPOVHitResult(lvl, plr,ClipContext.Fluid.SOURCE_ONLY);
	      if (blockhitresult.getType() == HitResult.Type.MISS) {
	         return InteractionResultHolder.pass(itemstack);
	      } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
	         return InteractionResultHolder.pass(itemstack);
	      } else {
	         BlockPos blockpos = blockhitresult.getBlockPos();
	         Direction direction = blockhitresult.getDirection();
	         BlockPos blockpos1 = blockpos.relative(direction);
	         if (lvl.mayInteract(plr, blockpos) && plr.mayUseItemAt(blockpos1, direction, itemstack)) {
	        	BlockState blockstate1 = lvl.getBlockState(blockpos);
	               if (blockstate1.getBlock().defaultBlockState().getTags().toList().contains(new ResourceLocation("pepsimc", "shard_mendable"))) {
	                	  plr.awardStat(Stats.ITEM_USED.get(this));
	                	  if(!lvl.isClientSide) {
	                		  EntityType.LIGHTNING_BOLT.spawn((ServerLevel)lvl, null, null, plr, blockpos, MobSpawnType.TRIGGERED, true, true);
	                		  lvl.destroyBlock(blockpos, true);
	                		  plr.getItemInHand(hand).shrink(1);
	                		  plr.getInventory().add(plr.getInventory().selected, new ItemStack(this.result));
	                	  }
	                     return InteractionResultHolder.sidedSuccess(new ItemStack(this.result), lvl.isClientSide());
	                  
	               }

	               return InteractionResultHolder.fail(itemstack);
	         }
	         return InteractionResultHolder.fail(itemstack);
	      }
	   }
}