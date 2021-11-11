package ml.jozefpeeterslaan72wuustwezel.pepsimc.data.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcRecipeType {
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "pepsimc");
	
	public static final RegistryObject<BottlerRecipe.Serializer> BOTTLER_SERIALIZER = RECIPE_SERIALIZER.register("bottler", BottlerRecipe.Serializer::new);
	
	public static IRecipeType<BottlerRecipe> BOTTLER_RECIPE = new BottlerRecipe.BottlerRecipeType();
	
	public static void register(IEventBus bus) {
		RECIPE_SERIALIZER.register(bus);
    	Registry.register(Registry.RECIPE_TYPE, BottlerRecipe.TYPE_ID, BOTTLER_RECIPE);

	}
	
	
}
