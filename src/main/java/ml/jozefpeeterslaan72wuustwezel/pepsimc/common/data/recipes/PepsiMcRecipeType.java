package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcRecipeType {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "pepsimc");
	
	public static final RegistryObject<BottlerRecipe.Serializer> BOTTLER_SERIALIZER = RECIPE_SERIALIZER.register("bottler", BottlerRecipe.Serializer::new);
	
	public static RecipeType<BottlerRecipe> BOTTLER_RECIPE = new BottlerRecipe.BottlerRecipeType();
	
	public static void register(IEventBus bus) {
		RECIPE_SERIALIZER.register(bus);
    	Registry.register(Registry.RECIPE_TYPE, BottlerRecipe.TYPE_ID, BOTTLER_RECIPE);

	}
	
	
}
