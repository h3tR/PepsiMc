package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;

import java.util.Objects;
import java.util.stream.Collectors;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

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
	}

	    @Override
	    public void registerRecipes(IRecipeRegistration registration) {
	        @SuppressWarnings("resource")
			net.minecraft.world.item.crafting.RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
	        registration.addRecipes(rm.getAllRecipesFor(PepsiMcRecipeType.BOTTLER_RECIPE).stream()
	                        .filter(r -> r instanceof BottlerRecipe).collect(Collectors.toList()),
	                BottlerRecipeCategory.UID);
	        registration.addRecipes(rm.getAllRecipesFor(PepsiMcRecipeType.RECYCLER_RECIPE).stream()
                    .filter(r -> r instanceof RecyclerRecipe).collect(Collectors.toList()),
            RecyclerRecipeCategory.UID);
	        registration.addRecipes(rm.getAllRecipesFor(PepsiMcRecipeType.FLAVORING_RECIPE).stream()
                    .filter(r -> r instanceof FlavoringRecipe).collect(Collectors.toList()),
            FlavoringRecipeCategory.UID);
	    }
	    
	    @Override
	    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
	    	registration.addRecipeCatalyst(new ItemStack(PepsiMcItem.BOTTLER.get()),BottlerRecipeCategory.UID);
	    	registration.addRecipeCatalyst(new ItemStack(PepsiMcItem.RECYCLER.get()),RecyclerRecipeCategory.UID);
	    	registration.addRecipeCatalyst(new ItemStack(PepsiMcItem.FLAVOR_MACHINE.get()),FlavoringRecipeCategory.UID);
	    	IModPlugin.super.registerRecipeCatalysts(registration);
	    }
}
