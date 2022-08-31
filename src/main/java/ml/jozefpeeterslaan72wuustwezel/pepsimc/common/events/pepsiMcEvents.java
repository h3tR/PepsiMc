package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcTrades;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class pepsiMcEvents {
    @SubscribeEvent
    public static void addTrades(VillagerTradesEvent event){
        //TODO
        if(event.getType()== PepsiMcProfession.BOTTLING_OPERATOR.get()){
            VillagerTrades.ItemListing[][] TradeList = {{
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSITE_INGOT.get(), 1, 2, 2),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.CAFFEINE.get(), 16, 1, 2),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 2)
            },{
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.EMPTY_CAN.get(), 4, 16, 5),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 5),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 5, 1, 5)
            },{
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_LABEL.get(), 10, 2, 15),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_LABEL.get(), 10, 2, 15),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.STEVIA.get(), 10, 2, 15)//,
                    //TODO new EmeraldForMapTrade(32, PepsiMcStructure.ABANDONED_BOTTLING_PLANT.get(), MapDecoration.Type.TARGET_POINT, 1, 15)
            },{
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 1, 8, 12),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get(), 10, 2, 15),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_FLUID_BUCKET.get(), 10, 2, 15),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.BOTTLER.get(), 1, 32, 15)
            },{
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_BOTTLE.get(), 48, 1, 20),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_BOTTLE.get(), 48, 1, 20),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_CAN.get(), 48, 1, 20),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_CAN.get(), 48, 1, 20)
            }};
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            for(int i=0;i<TradeList.length;i++){
                for(int j =0;j<TradeList[i].length;j++){
                    trades.get(i).add(TradeList[i][j]);
                }
            }
        }
    }
}
