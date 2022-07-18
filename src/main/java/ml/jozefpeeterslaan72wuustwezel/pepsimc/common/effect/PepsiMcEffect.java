package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcEffect {

	public static final DeferredRegister<MobEffect> EFFECTS = 
			DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "pepsimc");
	public static final DeferredRegister<Potion> POTIONS = //TODO
			DeferredRegister.create(ForgeRegistries.POTIONS, "pepsimc");
	
	public static RegistryObject<MobEffect> INSOMNIA = EFFECTS.register("insomnia",InsomniaEffect::new);

	public static RegistryObject<Potion> INSOMNIA_POTION = POTIONS.register("insomnia_potion",()->new Potion(new MobEffectInstance(INSOMNIA.get(), 3600)));


	public static void register(IEventBus bus) {
		EFFECTS.register(bus);
		POTIONS.register(bus);
	}

	public static void registerPotionRecipes(){
		Item[] PotionTypes={Items.POTION,Items.SPLASH_POTION,Items.LINGERING_POTION};
		for (int i=0;i<PotionTypes.length;i++) {
			BrewingRecipeRegistry.addRecipe(
					Ingredient.of(PotionUtils.setPotion(new ItemStack(PotionTypes[i]), Potions.WATER)),
					Ingredient.of(new ItemStack(PepsiMcItem.CAFFEINE.get())),
					PotionUtils.setPotion(new ItemStack(PotionTypes[i]), PepsiMcEffect.INSOMNIA_POTION.get()));
		}
	}
}
