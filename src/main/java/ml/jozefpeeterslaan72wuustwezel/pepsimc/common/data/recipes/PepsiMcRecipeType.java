package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcRecipeType {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "pepsimc");

	public static final RegistryObject<BottlerRecipe.Serializer> BOTTLER_SERIALIZER = RECIPE_SERIALIZER.register("bottler", BottlerRecipe.Serializer::new);
	
	public static final RegistryObject<CentrifugeRecipe.Serializer> CENTRIFUGE_SERIALIZER = RECIPE_SERIALIZER.register("centrifuge", CentrifugeRecipe.Serializer::new);

	public static final RegistryObject<FlavoringRecipe.Serializer> FLAVORING_SERIALIZER = RECIPE_SERIALIZER.register("flavor", FlavoringRecipe.Serializer::new);

	public static final RegistryObject<RecyclerRecipe.Serializer> RECYCLER_SERIALIZER = RECIPE_SERIALIZER.register("recycler", RecyclerRecipe.Serializer::new);

	public static void register(IEventBus bus) {
		RECIPE_SERIALIZER.register(bus);

	}
	
	
}
