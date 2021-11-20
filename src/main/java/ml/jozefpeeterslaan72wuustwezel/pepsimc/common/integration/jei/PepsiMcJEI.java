package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;

import java.util.Objects;
import java.util.stream.Collectors;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class PepsiMcJEI implements IModPlugin{

	@Override
	public ResourceLocation getPluginUid() {
		// TODO Auto-generated method stub
		return new ResourceLocation("pepsimc","jei_plugin");
	}
	
	 @Override
	    public void registerCategories(IRecipeCategoryRegistration registration) {
	        registration.addRecipeCategories(
	                new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	    }

	    @Override
	    public void registerRecipes(IRecipeRegistration registration) {
	        @SuppressWarnings("resource")
			net.minecraft.item.crafting.RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
	        registration.addRecipes(rm.getAllRecipesFor(PepsiMcRecipeType.BOTTLER_RECIPE).stream()
	                        .filter(r -> r instanceof BottlerRecipe).collect(Collectors.toList()),
	                BottlerRecipeCategory.UID);
	    }
	    
	    @Override
	    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

	    	registration.addRecipeCatalyst(new ItemStack(PepsiMcItem.BOTTLER.get()),BottlerRecipeCategory.UID);
	    	IModPlugin.super.registerRecipeCatalysts(registration);
	    }
}
