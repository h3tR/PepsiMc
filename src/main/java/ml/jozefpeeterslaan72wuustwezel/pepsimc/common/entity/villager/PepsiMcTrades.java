package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager;

import java.util.Random;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

import javax.annotation.Nullable;

public class PepsiMcTrades {

	public static class EmeraldForItemsTrade implements VillagerTrades.ItemListing {
	        private final Item item;
	        private final int cost;
	        private final int maxUses;
	        private final int villagerXp;
	        private final float priceMultiplier;

	        public EmeraldForItemsTrade(ItemLike item, int cost, int stock, int Xp) {
	            this.item = item.asItem();
	            this.cost = cost;
	            this.maxUses = stock;
	            this.villagerXp = Xp;
	            this.priceMultiplier = 0.05F;
	         }

	        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	           ItemStack itemstack = new ItemStack(this.item, this.cost);
	           return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
	        }
	     }

	public static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
	        private final ItemStack itemStack;
	        private final int emeraldCost;
	        private final int numberOfItems;
	        private final int maxUses;
	        private final int villagerXp;
	        private final float priceMultiplier;

	        public ItemsForEmeraldsTrade(Block offer, int cost, int amount, int stock, int mult) {
	           this(new ItemStack(offer), cost, amount, stock, mult);
	        }

	        public ItemsForEmeraldsTrade(Item offer, int cost, int amount, int stock) {
	           this(new ItemStack(offer), cost, amount, 12, stock);
	        }

	        public ItemsForEmeraldsTrade(Item offer, int cost, int amount, int stock, int mult) {
	           this(new ItemStack(offer), cost, amount, stock, mult);
	        }

	        public ItemsForEmeraldsTrade(ItemStack offer, int cost, int amount, int stock, int mult) {
	           this(offer, cost, amount, stock, mult, 0.05F);
	        }

	        public ItemsForEmeraldsTrade(ItemStack offer, int cost, int amount, int stock, int xp, float mult) {
	           this.itemStack = offer;
	           this.emeraldCost = cost;
	           this.numberOfItems = amount;
	           this.maxUses = stock;
	           this.villagerXp = xp;
	           this.priceMultiplier = mult;
	        }

	        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	           return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
	        }
	     }
		 public static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
		private final int emeraldCost;
		private final TagKey<ConfiguredStructureFeature<?, ?>> destination;
		private final String displayName;
		private final MapDecoration.Type destinationType;
		private final int maxUses;
		private final int villagerXp;

		public TreasureMapForEmeralds(int p_207767_, TagKey<ConfiguredStructureFeature<?, ?>> p_207768_, String p_207769_, MapDecoration.Type p_207770_, int p_207771_, int p_207772_) {
			this.emeraldCost = p_207767_;
			this.destination = p_207768_;
			this.displayName = p_207769_;
			this.destinationType = p_207770_;
			this.maxUses = p_207771_;
			this.villagerXp = p_207772_;
		}

		@Nullable
		public MerchantOffer getOffer(Entity p_35817_, Random p_35818_) {
			if (!(p_35817_.level instanceof ServerLevel)) {
				return null;
			} else {
				ServerLevel serverlevel = (ServerLevel)p_35817_.level;
				BlockPos blockpos = serverlevel.findNearestMapFeature(this.destination, p_35817_.blockPosition(), 100, true);
				if (blockpos != null) {
					ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
					MapItem.renderBiomePreviewMap(serverlevel, itemstack);
					MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
					itemstack.setHoverName(new TranslatableComponent(this.displayName));
					return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
				} else {
					return null;
				}
			}
		}
	}
}
