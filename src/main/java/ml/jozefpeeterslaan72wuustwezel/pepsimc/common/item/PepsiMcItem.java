package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect.PepsiMcEffect;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.PepsiMcItemGroup;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.soundevent.PepsiMcSoundEvent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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

	public static final RegistryObject<Item> MANGO = ITEMS.register("mango", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB).food(new FoodProperties.Builder()
					.nutrition(4)
					.saturationMod(4)
					.build()
					)));
	
	public static final RegistryObject<Item> PEPSI_MAN_TUNE_DISC = ITEMS.register("pepsi_man_tune_disc", ()-> new RecordItem(13, PepsiMcSoundEvent.PEPSI_MAN_TUNE,new Item.Properties()
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
			),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
			),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
			),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
			),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
			),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
			),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_CAN::get)));
	
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
					),new ItemStack(PepsiMcItem.USED_BOTTLE::get)));
	
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
	
	public static final RegistryObject<Item> PEPSI_FLUID_BUCKET = ITEMS.register("pepsi_bucket", ()-> new /*Bucket*/Item(/*()->PepsiMcFluid.PEPSI_FLUID.get(),*/new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.stacksTo(16)));
	public static final RegistryObject<Item> PEPSI_MAX_FLUID_BUCKET = ITEMS.register("pepsi_max_bucket", ()-> new /*Bucket*/Item(/*()->PepsiMcFluid.PEPSI_MAX_FLUID.get(),*/new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)
			.stacksTo(16)));
	
	public static final RegistryObject<Item> PEPSITE_SHARD = ITEMS.register("pepsite_shard", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> PEPSITE_HELMET = ITEMS.register("pepsite_helmet", ()-> new PepsiteArmorItem(PepsiMcArmorMaterial.PEPSITE, EquipmentSlot.HEAD,new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),MobEffects.NIGHT_VISION)); 
	
	public static final RegistryObject<Item> PEPSITE_CHESTPLATE = ITEMS.register("pepsite_chestplate", ()-> new PepsiteArmorItem(PepsiMcArmorMaterial.PEPSITE, EquipmentSlot.CHEST,new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),MobEffects.DIG_SPEED)); 
	
	public static final RegistryObject<Item> PEPSITE_LEGGINGS = ITEMS.register("pepsite_leggings", ()-> new PepsiteArmorItem(PepsiMcArmorMaterial.PEPSITE, EquipmentSlot.LEGS,new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),MobEffects.FIRE_RESISTANCE)); 
	
	public static final RegistryObject<Item> PEPSITE_BOOTS = ITEMS.register("pepsite_boots", ()-> new  PepsiteArmorItem(PepsiMcArmorMaterial.PEPSITE, EquipmentSlot.FEET,new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),MobEffects.JUMP)); 
	
	public static final RegistryObject<Item> POWERED_PEPSITE_CHESTPLATE = ITEMS.register("powered_pepsite_chestplate", ()-> new CoreChestPlateItem(PepsiMcArmorMaterial.COREPEPSITE, EquipmentSlot.CHEST,new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),MobEffects.DIG_SPEED)); 
	
	public static final RegistryObject<Item> PEPSITE_CORE = ITEMS.register("pepsite_core", ()-> new Item(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB))); 
	
	public static final RegistryObject<Item> SHARD_HELMET = ITEMS.register("shard_helmet", ()-> new PepsiteShardArmorItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),PepsiMcItem.PEPSITE_HELMET.get())); 
	
	public static final RegistryObject<Item> SHARD_CHESTPLATE = ITEMS.register("shard_chestplate", ()-> new PepsiteShardArmorItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),PepsiMcItem.PEPSITE_CHESTPLATE.get())); 
	
	public static final RegistryObject<Item> SHARD_LEGGINGS = ITEMS.register("shard_leggings", ()-> new PepsiteShardArmorItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),PepsiMcItem.PEPSITE_LEGGINGS.get())); 
	
	public static final RegistryObject<Item> SHARD_BOOTS = ITEMS.register("shard_boots", ()-> new  PepsiteShardArmorItem(new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB),PepsiMcItem.PEPSITE_BOOTS.get())); 
	
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

	public static final RegistryObject<BlockItem> AUTOMATED_BOTTLER = ITEMS.register("automated_bottler", ()-> new BlockItem(PepsiMcBlock.AUTOMATED_BOTTLER.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));

	public static final RegistryObject<BlockItem> AUTOMATED_CENTRIFUGE = ITEMS.register("automated_centrifuge", ()-> new BlockItem(PepsiMcBlock.AUTOMATED_CENTRIFUGE.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	public static final RegistryObject<BlockItem> AUTOMATED_FLAVOR_MACHINE = ITEMS.register("automated_flavor_machine", ()-> new BlockItem(PepsiMcBlock.AUTOMATED_FLAVOR_MACHINE.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	public static final RegistryObject<BlockItem> AUTOMATED_RECYCLER = ITEMS.register("automated_recycler", ()-> new BlockItem(PepsiMcBlock.AUTOMATED_RECYCLER.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));

	public static final RegistryObject<BlockItem> GENERATOR = ITEMS.register("generator", ()-> new BlockItem(PepsiMcBlock.GENERATOR.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> FLAVOR_MACHINE = ITEMS.register("flavor_machine", ()-> new BlockItem(PepsiMcBlock.FLAVOR_MACHINE.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> RECYCLER = ITEMS.register("recycler", ()-> new RecyclerBlockItem(PepsiMcBlock.RECYCLER.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	public static final RegistryObject<BlockItem> CENTRIFUGE = ITEMS.register("centrifuge", ()-> new CentrifugeBlockItem(PepsiMcBlock.CENTRIFUGE.get(), new Item.Properties()
			.tab(PepsiMcItemGroup.PEPSIMC_TAB)));
	
	
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}

	
}
