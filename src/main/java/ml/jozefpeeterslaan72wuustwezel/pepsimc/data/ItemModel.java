package ml.jozefpeeterslaan72wuustwezel.pepsimc.data;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModel extends ItemModelProvider {

	
	public ItemModel(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, "pepsimc", existingFileHelper);
	}

	@Override
	protected void registerModels() {
		Item[] singleTextureGen = {
				PepsiMcItem.TOKEN.get(),
				PepsiMcItem.USED_CAN.get(),
				PepsiMcItem.USED_BOTTLE.get(),
				PepsiMcItem.PEPSI_CAN.get(),
				PepsiMcItem.PEPSI_BOTTLE.get(),
				PepsiMcItem.PEPSI_LEMON_CAN.get(),
				PepsiMcItem.PEPSI_LEMON_BOTTLE.get(),
				PepsiMcItem.PEPSI_BERRY_CAN.get(),
				PepsiMcItem.PEPSI_BERRY_BOTTLE.get(),
				PepsiMcItem.PEPSI_FIRE_CAN.get(),
				PepsiMcItem.PEPSI_FIRE_BOTTLE.get(),
				PepsiMcItem.PEPSI_CRYSTAL_BOTTLE.get(),
				PepsiMcItem.PEPSI_MANGO_CAN.get(),
				PepsiMcItem.PEPSI_MANGO_BOTTLE.get(),
				PepsiMcItem.PEPSI_CHERRY_CAN.get(),
				PepsiMcItem.PEPSI_CHERRY_BOTTLE.get(),
				PepsiMcItem.PEPSI_BOOM_CAN.get(),
				PepsiMcItem.PEPSI_BOOM_BOTTLE.get(),
				PepsiMcItem.COKE_CAN.get(),
				PepsiMcItem.COKE_BOTTLE.get(),
				PepsiMcItem.CARAMEL.get(),
				PepsiMcItem.PHOSPHORIC_ACID.get(),
				PepsiMcItem.BOTTLECAP.get(),
				PepsiMcItem.CAN_LID.get(),
				PepsiMcItem.CAFFEINE.get(),
				PepsiMcItem.STEVIA.get(),
				PepsiMcItem.LEMON.get(),
				PepsiMcItem.CHERRY.get(),
				PepsiMcItem.MANGO.get(),
				PepsiMcItem.PEPSI_MAN_TUNE_DISC.get(),
				PepsiMcItem.PEPSI_MAX_BOTTLE.get(),
				PepsiMcItem.PEPSI_MAX_CAN.get(),
				PepsiMcItem.PEPSI_MAX_MANGO_BOTTLE.get(),
				PepsiMcItem.PEPSI_MAX_MANGO_CAN.get(),
				PepsiMcItem.PEPSI_MAX_LEMON_BOTTLE.get(),
				PepsiMcItem.PEPSI_MAX_LEMON_CAN.get(),
				PepsiMcItem.PEPSI_MAX_CHERRY_CAN.get(),
				PepsiMcItem.PEPSI_MAX_CHERRY_BOTTLE.get(),
				PepsiMcItem.PEPSITE_INGOT.get(),
				PepsiMcItem.RAW_PEPSITE.get(),
				PepsiMcItem.PEPSI_LABEL.get(),
				PepsiMcItem.PEPSI_MAX_LABEL.get(),
				PepsiMcItem.EMPTY_BOTTLE.get(),
				PepsiMcItem.EMPTY_CAN.get(),
				PepsiMcItem.PEPSI_FLUID_BUCKET.get(),
				PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get(),
				PepsiMcItem.STEVIA_PLANT.get(),
				PepsiMcItem.STEVIA_SEEDS.get(),
				PepsiMcItem.PEPSITE_SHARD.get(),
				PepsiMcItem.SHARD_HELMET.get(),
				PepsiMcItem.SHARD_CHESTPLATE.get(),
				PepsiMcItem.SHARD_LEGGINGS.get(),
				PepsiMcItem.SHARD_BOOTS.get(),
				PepsiMcItem.PEPSITE_HELMET.get(),
				PepsiMcItem.PEPSITE_CHESTPLATE.get(),
				PepsiMcItem.PEPSITE_LEGGINGS.get(),
				PepsiMcItem.PEPSITE_BOOTS.get(),
				PepsiMcItem.PEPSITE_CORE.get(),
				PepsiMcItem.POWERED_PEPSITE_CHESTPLATE.get(),



		};
		for (int i = 0; i < singleTextureGen.length; i++) {
			String noIDName = "items/"+singleTextureGen[i].getRegistryName().toString().split(":")[1];
			singleTexture(singleTextureGen[i].getRegistryName().getPath(),new ResourceLocation("item/generated"),"layer0",new ResourceLocation("pepsimc",noIDName));

		}
	}
}
