package ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager.trades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.PepsiMcItem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Util;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

public class PepsiMcTrades {
	  private static final Map<VillagerProfession, Int2ObjectMap<ITrade[]>> PEPSIMC_TRADES = new HashMap<>();
	    
	    public static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ITrade[]>> TRADES = 
	    	Util.make(Maps.newHashMap(), (p_221237_0_) -> {
	        p_221237_0_.put(PepsiMcProfession.BOTTLING_OPERATOR.get(), toIntMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
	        		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSITE_INGOT.get(), 1, 2, 2),
	    			new EmeraldForItemsTrade(PepsiMcItem.CAFFEINE.get(), 1, 16, 2),
	    			new EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 2),

	        	}, 2, new VillagerTrades.ITrade[]{
	        			new EmeraldForItemsTrade(PepsiMcItem.EMPTY_CAN.get(), 2, 16, 5),
	        			new EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 5),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 10, 2, 5),

	        	}, 3, new VillagerTrades.ITrade[]{
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_LABEL.get(), 10, 2, 10),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_LABEL.get(), 10, 2, 10),
	            		new EmeraldForItemsTrade(PepsiMcItem.STEVIA.get(), 10, 2, 10),


	        	}, 4, new VillagerTrades.ITrade[]{
	            		new ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 1, 8, 12),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get(), 10, 2, 15),
	            		new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_FLUID_BUCKET.get(), 10, 2, 15),
	        			new EmeraldForItemsTrade(PepsiMcItem.BOTTLER.get(), 32, 16, 15),

	        	}, 5, new VillagerTrades.ITrade[]{
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_BOTTLE.get(), 48, 16, 20),
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_BOTTLE.get(), 48, 16, 20),
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_CAN.get(), 48, 16, 20),
	        			new ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_CAN.get(), 48, 16, 20),

	        	}
	        	)));});
	    
	    private static void postVillagerEvent(VillagerProfession prof)
	    {
	    		
	    	TRADES.entrySet().forEach(e ->{
		 		Int2ObjectMap<ITrade[]> copy = new Int2ObjectOpenHashMap<>();
		 		e.getValue().int2ObjectEntrySet().forEach(ent -> copy.put(ent.getIntKey(), Arrays.copyOf(ent.getValue(), ent.getValue().length)));
		 		PEPSIMC_TRADES.put(e.getKey(), copy);
			});
	    	
	    	
	            Int2ObjectMap<ITrade[]> trades = PEPSIMC_TRADES.getOrDefault(prof, new Int2ObjectOpenHashMap<>());
	            Int2ObjectMap<List<ITrade>> mutableTrades = new Int2ObjectOpenHashMap<>();
	            for (int i = 1; i < 6; i++)
	            {
	                mutableTrades.put(i, NonNullList.create());
	            }
	            trades.int2ObjectEntrySet().forEach(e ->
	            {
	                Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
	            });
	            MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(mutableTrades, prof));
	            Int2ObjectMap<ITrade[]> newTrades = new Int2ObjectOpenHashMap<>();
	            mutableTrades.int2ObjectEntrySet().forEach(e -> newTrades.put(e.getIntKey(), e.getValue().toArray(new ITrade[0])));
	            VillagerTrades.TRADES.put(prof, newTrades); 
	    }
		
	    public static void loadTrades(FMLServerAboutToStartEvent e)
	    {
	        postVillagerEvent(PepsiMcProfession.BOTTLING_OPERATOR.get());
	    }
	    
	    static class EmeraldForItemsTrade implements VillagerTrades.ITrade {
	        private final Item item;
	        private final int cost;
	        private final int maxUses;
	        private final int villagerXp;
	        private final float priceMultiplier;

	        public EmeraldForItemsTrade(IItemProvider item, int cost, int stock, int Xp) {
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
	    
	    static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
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
	    
	    private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
	        return new Int2ObjectOpenHashMap<>(p_221238_0_);
	     }
}