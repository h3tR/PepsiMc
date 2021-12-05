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
	
	public static final RegistryObject<ExtractorRecipe.Serializer> EXTRACTOR_SERIALIZER = RECIPE_SERIALIZER.register("extractor", ExtractorRecipe.Serializer::new);

	public static final RegistryObject<FlavoringRecipe.Serializer> FLAVORING_SERIALIZER = RECIPE_SERIALIZER.register("flavor", FlavoringRecipe.Serializer::new);

	public static final RegistryObject<RecyclerRecipe.Serializer> RECYCLER_SERIALIZER = RECIPE_SERIALIZER.register("recycler", RecyclerRecipe.Serializer::new);

	
	public static RecipeType<BottlerRecipe> BOTTLER_RECIPE = new BottlerRecipe.BottlerRecipeType();
	
	public static RecipeType<ExtractorRecipe> EXTRACTOR_RECIPE = new ExtractorRecipe.ExtractorRecipeType();

	public static RecipeType<FlavoringRecipe> FLAVORING_RECIPE = new FlavoringRecipe.FlavoringRecipeType();

	public static RecipeType<RecyclerRecipe> RECYCLER_RECIPE = new RecyclerRecipe.RecyclerRecipeType();

	
	public static void register(IEventBus bus) {
		RECIPE_SERIALIZER.register(bus);
    	Registry.register(Registry.RECIPE_TYPE, BottlerRecipe.TYPE_ID, BOTTLER_RECIPE);
    	Registry.register(Registry.RECIPE_TYPE, RecyclerRecipe.TYPE_ID, RECYCLER_RECIPE);
    	//Registry.register(Registry.RECIPE_TYPE, ExtractorRecipe.TYPE_ID, EXTRACTOR_RECIPE);
    //	Registry.register(Registry.RECIPE_TYPE, FlavoringRecipe.TYPE_ID, FLAVORING_RECIPE);

	}
	
	
}
