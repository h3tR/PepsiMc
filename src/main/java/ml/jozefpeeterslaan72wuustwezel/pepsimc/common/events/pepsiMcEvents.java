package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcProfession;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.PepsiMcTrades;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "pepsimc")
public class pepsiMcEvents {
    @SubscribeEvent
    public static void addTrades(VillagerTradesEvent event){
        if(event.getType()== PepsiMcProfession.BOTTLING_OPERATOR.get()){
            VillagerTrades.ItemListing[][] TradeList = {{
                    //new PepsiMcTrades.TreasureMapForEmeralds(32, PepsiMcTags.Structures.ABANDONED_BOTTLING_PLANT, "filled_map.pepsimc:abandoned_bottling_plant", MapDecoration.Type.TARGET_POINT, 1, 15),

                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSITE_INGOT.get(), 8, 1, 2),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.CAFFEINE.get(), 16, 1, 2),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 2)
            },{
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.EMPTY_CAN.get(), 4, 16, 5),
                   new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.EMPTY_BOTTLE.get(), 5, 16, 5),
                   new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 5, 1, 5)
            },{
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_LABEL.get(), 10, 2, 15),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_LABEL.get(), 10, 2, 15),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.STEVIA.get(), 10, 2, 15),
                    new PepsiMcTrades.TreasureMapForEmeralds(32, PepsiMcTags.Structures.ABANDONED_BOTTLING_PLANT, "filled_map.pepsimc:abandoned_bottling_plant", MapDecoration.Type.TARGET_POINT, 1, 15)
            },{

                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.CARAMEL.get(), 1, 8, 12),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get(), 26, 2, 15),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_FLUID_BUCKET.get(), 26, 2, 15),
                    new PepsiMcTrades.EmeraldForItemsTrade(PepsiMcItem.BOTTLER.get(), 1, 32, 15)
            },{
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_BOTTLE.get(), 48, 1, 20),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_BOTTLE.get(), 48, 1, 20),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_CAN.get(), 48, 1, 20),
                    new PepsiMcTrades.ItemsForEmeraldsTrade(PepsiMcItem.PEPSI_MAX_CAN.get(), 48, 1, 20)
            }};
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            for(int i=1;i<TradeList.length+1;i++){
                for(int j =0;j<TradeList[i-1].length;j++){
                    trades.get(i).add(TradeList[i-1][j]);
                }
            }
        }
    }
}
