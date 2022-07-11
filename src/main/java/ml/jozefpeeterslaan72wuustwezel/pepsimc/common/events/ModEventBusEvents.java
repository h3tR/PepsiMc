package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events.loot.FruitAdditionModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = "pepsimc", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
	
	@SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
		event.register(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, helper->{
			helper.register(new ResourceLocation("pepsimc","lemon_from_acacia_leaves"),new FruitAdditionModifier.Serializer());
			helper.register(new ResourceLocation("pepsimc","mango_from_jungle_leaves"),new FruitAdditionModifier.Serializer());
			helper.register(new ResourceLocation("pepsimc","cherry_from_birch_leaves"),new FruitAdditionModifier.Serializer());
		});
		event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper->{
			helper.register(new ResourceLocation("pepsimc", FlavoringRecipe.FlavoringRecipeType.ID),FlavoringRecipe.FlavoringRecipeType.INSTANCE);
			helper.register(new ResourceLocation("pepsimc", FlavoringRecipe.FlavoringRecipeType.ID),FlavoringRecipe.FlavoringRecipeType.INSTANCE);
			helper.register(new ResourceLocation("pepsimc", FlavoringRecipe.FlavoringRecipeType.ID),FlavoringRecipe.FlavoringRecipeType.INSTANCE);
			helper.register(new ResourceLocation("pepsimc", FlavoringRecipe.FlavoringRecipeType.ID),FlavoringRecipe.FlavoringRecipeType.INSTANCE);

		});
	}


}
