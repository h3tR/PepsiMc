package ml.jozefpeeterslaan72wuustwezel.pepsimc.data;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.BeverageItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class ItemModel extends ItemModelProvider {

	
	public ItemModel(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, "pepsimc", existingFileHelper);
	}

	@Override
	protected void registerModels() {
		List<RegistryObject<Item>> singleTextureGen = new ArrayList<RegistryObject<Item>>();
				singleTextureGen.add(PepsiMcItem.TOKEN);
				singleTextureGen.add(PepsiMcItem.USED_CAN);
				singleTextureGen.add(PepsiMcItem.USED_BOTTLE);
				singleTextureGen.add(PepsiMcItem.CARAMEL);
				singleTextureGen.add(PepsiMcItem.PHOSPHORIC_ACID);
				singleTextureGen.add(PepsiMcItem.BOTTLECAP);
				singleTextureGen.add(PepsiMcItem.CAN_LID);
				singleTextureGen.add(PepsiMcItem.CAFFEINE);
				singleTextureGen.add(PepsiMcItem.STEVIA);
				singleTextureGen.add(PepsiMcItem.LEMON);
				singleTextureGen.add(PepsiMcItem.CHERRY);
				singleTextureGen.add(PepsiMcItem.MANGO);
				singleTextureGen.add(PepsiMcItem.PEPSI_MAN_TUNE_DISC);
				singleTextureGen.add(PepsiMcItem.PEPSITE_INGOT);
				singleTextureGen.add(PepsiMcItem.RAW_PEPSITE);
				singleTextureGen.add(PepsiMcItem.PEPSI_LABEL);
				singleTextureGen.add(PepsiMcItem.PEPSI_MAX_LABEL);
				singleTextureGen.add(PepsiMcItem.EMPTY_BOTTLE);
				singleTextureGen.add(PepsiMcItem.EMPTY_CAN);
				singleTextureGen.add(PepsiMcItem.PEPSI_FLUID_BUCKET);
				singleTextureGen.add(PepsiMcItem.PEPSI_MAX_FLUID_BUCKET);
				singleTextureGen.add(PepsiMcItem.STEVIA_SEEDS);
				singleTextureGen.add(PepsiMcItem.PEPSITE_SHARD);
				singleTextureGen.add(PepsiMcItem.SHARD_HELMET);
				singleTextureGen.add(PepsiMcItem.SHARD_CHESTPLATE);
				singleTextureGen.add(PepsiMcItem.SHARD_LEGGINGS);
				singleTextureGen.add(PepsiMcItem.SHARD_BOOTS);
				singleTextureGen.add(PepsiMcItem.PEPSITE_HELMET);
				singleTextureGen.add(PepsiMcItem.PEPSITE_CHESTPLATE);
				singleTextureGen.add(PepsiMcItem.PEPSITE_LEGGINGS);
				singleTextureGen.add(PepsiMcItem.PEPSITE_BOOTS);
				singleTextureGen.add(PepsiMcItem.PEPSITE_CORE);
				singleTextureGen.add(PepsiMcItem.POWERED_PEPSITE_CHESTPLATE);
		List<RegistryObject<BeverageItem>> singleTextureGenB = new ArrayList<RegistryObject<BeverageItem>>();
				singleTextureGenB.add(PepsiMcItem.PEPSI_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_LEMON_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_LEMON_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_BERRY_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_BERRY_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_FIRE_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_FIRE_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_CRYSTAL_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MANGO_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MANGO_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_CHERRY_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_CHERRY_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_BOOM_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_BOOM_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.COKE_CAN);
				singleTextureGenB.add(PepsiMcItem.COKE_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_MANGO_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_MANGO_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_LEMON_BOTTLE);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_LEMON_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_CHERRY_CAN);
				singleTextureGenB.add(PepsiMcItem.PEPSI_MAX_CHERRY_BOTTLE);

		for (int i = 0; i < singleTextureGen.size(); i++) {
			String noIDName = "items/"+singleTextureGen.get(i).getId().toString().split(":")[1];
			singleTexture(singleTextureGen.get(i).getId().getPath(),new ResourceLocation("item/generated"),"layer0",new ResourceLocation("pepsimc",noIDName));

		}
	}
}
