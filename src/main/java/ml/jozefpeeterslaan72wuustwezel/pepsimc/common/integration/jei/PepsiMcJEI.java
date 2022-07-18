package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;

import java.util.List;
import java.util.Objects;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class PepsiMcJEI implements IModPlugin{
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("pepsimc","jei_plugin");
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
	     registration.addRecipeCategories(
	                new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	     registration.addRecipeCategories(
	                new RecyclerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	     registration.addRecipeCategories(
	                new FlavoringRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	     registration.addRecipeCategories(
	                new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<BottlerRecipe> BottlerRecipes = rm.getAllRecipesFor(BottlerRecipe.BottlerRecipeType.INSTANCE);
		List<FlavoringRecipe> FlavoringRecipes = rm.getAllRecipesFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE);
		List<CentrifugeRecipe> CentrifugeRecipes = rm.getAllRecipesFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE);
		List<RecyclerRecipe> RecyclerRecipes = rm.getAllRecipesFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE);

		registration.addRecipes(new RecipeType<>(BottlerRecipeCategory.UID, BottlerRecipe.class), BottlerRecipes);
		registration.addRecipes(new RecipeType<>(FlavoringRecipeCategory.UID, FlavoringRecipe.class), FlavoringRecipes);
		registration.addRecipes(new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class), CentrifugeRecipes);
		registration.addRecipes(new RecipeType<>(RecyclerRecipeCategory.UID, RecyclerRecipe.class), RecyclerRecipes);

	}
}
