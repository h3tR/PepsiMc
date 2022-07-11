package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.trades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.world.structure.PepsiMcStructure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.NonNullList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class PepsiMcTrades {/*
	  private static final Map<VillagerProfession, Int2ObjectMap<ItemListing[]>> PEPSIMC_TRADES = new HashMap<>();
	    
	    public static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ItemListing[]>> TRADES = 
	    	Util.make(Maps.newHashMap(), (p_221237_0_) -> {
	        p_221237_0_.put(PepsiMcProfession.BOTTLING_OPERATOR.get(), toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
	        		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSITE_INGOT.get(), 1, 2, 2),
	    			new EmeraldForItemsTrade(PepsiMcItem.CAFFEINE.get(), 16, 1, 2),
	    			new EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 2),
	    			
	        	}, 2, new VillagerTrades.ItemListing[]{
	        			new EmeraldForItemsTrade(PepsiMcItem.EMPTY_CAN.get(), 4, 16, 5),
	        			new EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 5),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 5, 1, 5),

	        	}, 3, new VillagerTrades.ItemListing[]{
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_LABEL.get(), 10, 2, 15),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_LABEL.get(), 10, 2, 15),
	            		new EmeraldForItemsTrade(PepsiMcItem.STEVIA.get(), 10, 2, 15),
	            		new EmeraldForMapTrade(32, PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get(), MapDecoration.Type.TARGET_POINT, 1, 15)

	        	}, 4, new VillagerTrades.ItemListing[]{
	            		new ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 1, 8, 12),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get(), 10, 2, 15),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_FLUID_BUCKET.get(), 10, 2, 15),
	        			new EmeraldForItemsTrade(PepsiMcItem.BOTTLER.get(), 1, 32, 15),

	        	}, 5, new VillagerTrades.ItemListing[]{
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_BOTTLE.get(), 48, 1, 20),
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_BOTTLE.get(), 48, 1, 20),
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_CAN.get(), 48, 1, 20),
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_CAN.get(), 48, 1, 20),

	        	}
	        	)));});
	    
	    private static void postVillagerEvent(VillagerProfession prof)
	    {
	    		
	    	TRADES.entrySet().forEach(e ->{
		 		Int2ObjectMap<ItemListing[]> copy = new Int2ObjectOpenHashMap<>();
		 		e.getValue().int2ObjectEntrySet().forEach(ent -> copy.put(ent.getIntKey(), Arrays.copyOf(ent.getValue(), ent.getValue().length)));
		 		PEPSIMC_TRADES.put(e.getKey(), copy);
			});
	    	
	    	
	            Int2ObjectMap<ItemListing[]> trades = PEPSIMC_TRADES.getOrDefault(prof, new Int2ObjectOpenHashMap<>());
	            Int2ObjectMap<List<ItemListing>> mutableTrades = new Int2ObjectOpenHashMap<>();
	            for (int i = 1; i < 6; i++)
	            {
	                mutableTrades.put(i, NonNullList.create());
	            }
	            trades.int2ObjectEntrySet().forEach(e ->
	            {
	                Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
	            });
	            MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(mutableTrades, prof));
	            Int2ObjectMap<ItemListing[]> newTrades = new Int2ObjectOpenHashMap<>();
	            mutableTrades.int2ObjectEntrySet().forEach(e -> newTrades.put(e.getIntKey(), e.getValue().toArray(new ItemListing[0])));
	            VillagerTrades.TRADES.put(prof, newTrades); 
	    }
		
	    public static void loadTrades(FMLServerAboutToStartEvent e)
	    {
	        postVillagerEvent(PepsiMcProfession.BOTTLING_OPERATOR.get());
	    }
	    
	    static class EmeraldForItemsTrade implements VillagerTrades.ItemListing {
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
	    
	    static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
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
	    
	    static class EmeraldForMapTrade implements VillagerTrades.ItemListing {
	        private final int emeraldCost;
	        private final StructureFeature<?> destination;
	        private final MapDecoration.Type destinationType;
	        private final int maxUses;
	        private final int villagerXp;

	        public EmeraldForMapTrade(int cost, StructureFeature<?> destination, MapDecoration.Type destinationType, int stock, int Xp) {
	           this.emeraldCost = cost;
	           this.destination = destination;
	           this.destinationType = destinationType;
	           this.maxUses = stock;
	           this.villagerXp = Xp;
	        }

	        @Nullable
	        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	           if (!(p_221182_1_.level instanceof ServerLevel)) {
	              return null;
	           } else {
	              ServerLevel serverworld = (ServerLevel)p_221182_1_.level;
	              BlockPos blockpos = serverworld.findNearestMapFeature(this.destination, p_221182_1_.blockPosition(), 100, true);
	              if (blockpos != null) {
	                 ItemStack itemstack = MapItem.create(serverworld, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
	                 MapItem.renderBiomePreviewMap(serverworld, itemstack);
	                 MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
	                 itemstack.setHoverName(Component.translatable("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
	                 return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
	              } else {
	                 return null;
	              }
	           }
	        }
	     }
	    
	    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
	        return new Int2ObjectOpenHashMap<>(p_221238_0_);
	     }*/
}
