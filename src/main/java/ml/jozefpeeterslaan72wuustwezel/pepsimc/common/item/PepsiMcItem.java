package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.fluid.PepsiMcFluid;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect.PepsiMcEffect;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.PepsiMcItemGroup;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.soundevent.PepsiMcSoundEvent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcItem {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "pepsimc");

	public static final RegistryObject<Item> TOKEN = ITEMS.register("token", ()-> new Item(new Item.Properties())); 
	
	public static final RegistryObject<Item> USED_CAN = ITEMS.register("used_can", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> USED_BOTTLE = ITEMS.register("used_bottle", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<BeverageItem> PEPSI_CAN = ITEMS.register("pepsi_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_BOTTLE = ITEMS.register("pepsi_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_LEMON_CAN = ITEMS.register("pepsi_lemon_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_LEMON_BOTTLE = ITEMS.register("pepsi_lemon_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_BERRY_CAN = ITEMS.register("pepsi_berry_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.SLOW_FALLING, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_BERRY_BOTTLE = ITEMS.register("pepsi_berry_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.SLOW_FALLING, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_FIRE_CAN = ITEMS.register("pepsi_fire_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_FIRE_BOTTLE = ITEMS.register("pepsi_fire_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_CRYSTAL_BOTTLE = ITEMS.register("pepsi_crystal_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.NIGHT_VISION, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MANGO_CAN = ITEMS.register("pepsi_mango_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.DIG_SPEED, 1600, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MANGO_BOTTLE = ITEMS.register("pepsi_mango_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.DIG_SPEED, 1600, 2),1)

					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_CHERRY_CAN = ITEMS.register("pepsi_cherry_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.JUMP, 1600, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_CHERRY_BOTTLE = ITEMS.register("pepsi_cherry_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1600, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.JUMP, 1600, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_BOOM_CAN = ITEMS.register("pepsi_boom_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_BOOM_BOTTLE = ITEMS.register("pepsi_boom_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 2400, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2000, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> COKE_CAN = ITEMS.register("coke_can", ()-> new CokeItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(0)
					.build()
					)));
	
	public static final RegistryObject<BeverageItem> COKE_BOTTLE = ITEMS.register("coke_bottle", ()-> new CokeItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(0)
					.build()
					)));
	
	public static final RegistryObject<Item> CARAMEL = ITEMS.register("caramel", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1),1)
					.nutrition(2)
					.saturationMod(.2f)
					.build()
			)));
	
	public static final RegistryObject<Item> PHOSPHORIC_ACID = ITEMS.register("phosphoric_acid", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<Item> BOTTLECAP = ITEMS.register("bottlecap", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<Item> CAN_LID = ITEMS.register("can_lid", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<Item> CAFFEINE = ITEMS.register("caffeine", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB).food(new FoodProperties.Builder()
					.nutrition(0)
					.saturationMod(0)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 3200), 1)
					.build()
					)));
	
	public static final RegistryObject<Item> STEVIA = ITEMS.register("stevia", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<Item> MANGO = ITEMS.register("mango", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB).food(new FoodProperties.Builder()
					.nutrition(4)
					.saturationMod(4)
					.build()
					)));
	
	public static final RegistryObject<Item> LEMON = ITEMS.register("lemon", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB).food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(6)
					.build()
					)));
	
	public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB).food(new FoodProperties.Builder()
					.nutrition(3)
					.saturationMod(2)
					.build()
					)));
	
	public static final RegistryObject<Item> PEPSI_MAN_TUNE_DISC = ITEMS.register("pepsi_man_tune_disc", ()-> new RecordItem(13,()->PepsiMcSoundEvent.PEPSI_MAN_TUNE.get(),new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB).rarity(Rarity.RARE).stacksTo(1)));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_BOTTLE = ITEMS.register("pepsi_max_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
			),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_CAN = ITEMS.register("pepsi_max_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.build()
			),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_MANGO_BOTTLE = ITEMS.register("pepsi_max_mango_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 2),1)
					.build()
			),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_MANGO_CAN = ITEMS.register("pepsi_max_mango_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 2),1)
					.build()
			),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_LEMON_BOTTLE = ITEMS.register("pepsi_max_lemon_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 2),1)
					.build()
			),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_LEMON_CAN = ITEMS.register("pepsi_max_lemon_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 2),1)
					.build()
			),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_CHERRY_CAN = ITEMS.register("pepsi_max_cherry_can", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.JUMP, 1200, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_CAN.get())));
	
	public static final RegistryObject<BeverageItem> PEPSI_MAX_CHERRY_BOTTLE = ITEMS.register("pepsi_max_cherry_bottle", ()-> new BeverageItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.food(new FoodProperties.Builder()
					.nutrition(2)
					.saturationMod(.2f)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.REGENERATION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2),1)
					.effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 2),1)
					.effect(()->new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(), 6400), .3f)
					.effect(()->new MobEffectInstance(MobEffects.JUMP, 1200, 2),1)
					.build()
					),new ItemStack(()->PepsiMcItem.USED_BOTTLE.get())));
	
	public static final RegistryObject<Item> PEPSITE_INGOT = ITEMS.register("pepsite_ingot", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> RAW_PEPSITE = ITEMS.register("raw_pepsite", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<Item> PEPSI_LABEL = ITEMS.register("pepsi_label", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> PEPSI_MAX_LABEL = ITEMS.register("pepsi_max_label", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> EMPTY_BOTTLE = ITEMS.register("empty_bottle", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> EMPTY_CAN = ITEMS.register("empty_can", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> PEPSI_FLUID_BUCKET = ITEMS.register("pepsi_bucket", ()-> new BucketItem(()->PepsiMcFluid.PEPSI_FLUID.get(),new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.stacksTo(16))); 
	public static final RegistryObject<Item> PEPSI_MAX_FLUID_BUCKET = ITEMS.register("pepsi_max_bucket", ()-> new BucketItem(()->PepsiMcFluid.PEPSI_MAX_FLUID.get(),new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.stacksTo(16))); 
	
	//blockItems
	public static final RegistryObject<Item> STEVIA_SEEDS = ITEMS.register("stevia_seeds", ()-> new BlockItem(PepsiMcBlock.STEVIA_CROP.get(),new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<BlockItem> PEPSITE_BLOCK = ITEMS.register("pepsite_block", ()-> new BlockItem(PepsiMcBlock.PEPSITE_BLOCK.get(), new Item.Properties()
			.tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	
	public static final RegistryObject<BlockItem> RAW_PEPSITE_BLOCK = ITEMS.register("raw_pepsite_block", ()-> new BlockItem(PepsiMcBlock.RAW_PEPSITE_BLOCK.get(), new Item.Properties()
			.tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	
	public static final RegistryObject<BlockItem> STEVIA_PLANT = ITEMS.register("stevia_plant", ()-> new BlockItem(PepsiMcBlock.STEVIA_PLANT.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> PEPSITE_ORE = ITEMS.register("pepsite_ore", ()-> new BlockItem(PepsiMcBlock.PEPSITE_ORE.get(), new Item.Properties()
			.tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	
	public static final RegistryObject<BlockItem> DEEPSLATE_PEPSITE_ORE = ITEMS.register("deepslate_pepsite_ore", ()-> new BlockItem(PepsiMcBlock.DEEPSLATE_PEPSITE_ORE.get(), new Item.Properties()
			.tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	
	public static final RegistryObject<BlockItem> BOTTLER = ITEMS.register("bottler", ()-> new BlockItem(PepsiMcBlock.BOTTLER.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> FLAVOR_MACHINE = ITEMS.register("flavor_machine", ()-> new BlockItem(PepsiMcBlock.FLAVOR_MACHINE.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> RECYCLER = ITEMS.register("recycler", ()-> new BlockItem(PepsiMcBlock.RECYCLER.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> EXTRACTOR = ITEMS.register("extractor", ()-> new BlockItem(PepsiMcBlock.EXTRACTOR.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	
	}

	
}
