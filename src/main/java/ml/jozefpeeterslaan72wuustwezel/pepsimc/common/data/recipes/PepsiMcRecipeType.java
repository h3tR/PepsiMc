package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcRecipeType {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "pepsimc");
	
	public static final RegistryObject<RecipeSerializer<BottlerRecipe>> BOTTLER_SERIALIZER = RECIPE_SERIALIZER.register("bottler",()-> BottlerRecipe.Serializer.INSTANCE);
	
	public static final RegistryObject<RecipeSerializer<CentrifugeRecipe>> CENTRIFUGE_SERIALIZER = RECIPE_SERIALIZER.register("centrifuge",()-> CentrifugeRecipe.Serializer.INSTANCE);

	public static final RegistryObject<RecipeSerializer<FlavoringRecipe>> FLAVORING_SERIALIZER = RECIPE_SERIALIZER.register("flavor", ()->FlavoringRecipe.Serializer.INSTANCE);

	public static final RegistryObject<RecipeSerializer<RecyclerRecipe>> RECYCLER_SERIALIZER = RECIPE_SERIALIZER.register("recycler",()-> RecyclerRecipe.Serializer.INSTANCE);

	public static void register(IEventBus bus) {
		RECIPE_SERIALIZER.register(bus);
	}
}
