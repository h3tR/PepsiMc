package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;



import java.util.ArrayList;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;

public class BottlerRecipeCategory implements IRecipeCategory<BottlerRecipe>{
	public static final ResourceLocation UID = new ResourceLocation("pepsimc","bottler");
	private final IDrawable bg;
	private final IDrawable icon;

	public BottlerRecipeCategory(IGuiHelper helper) {
		this.bg = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/bottler_gui.png"), 0,0, 176, 85);
		this.icon = helper.createDrawableIngredient(new ItemStack(PepsiMcBlock.BOTTLER.get()));
	}
	
	@Override
	public ResourceLocation getUid() {
		// TODO Auto-generated method stub
		return UID;
	}

	@Override
	public Class<? extends BottlerRecipe> getRecipeClass() {
		// TODO Auto-generated method stub
		return BottlerRecipe.class;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return PepsiMcBlock.BOTTLER.get().getName().getString();
	}

	@Override
	public IDrawable getBackground() {
		// TODO Auto-generated method stub
		return this.bg;
	}

	@Override
	public IDrawable getIcon() {
		// TODO Auto-generated method stub
		return this.icon;
	}

	@Override
	public void setIngredients(BottlerRecipe recipe, IIngredients ingredients) {
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		result.add(recipe.getResultItem());
		result.add(new ItemStack(Items.BUCKET));
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, result);
		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, BottlerRecipe recipe, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 11, 14);
		recipeLayout.getItemStacks().init(1, true, 29, 14);
		recipeLayout.getItemStacks().init(2, true, 11, 42);
		recipeLayout.getItemStacks().init(3, false, 142, 29);
		recipeLayout.getItemStacks().init(4, false, 142, 50);

		recipeLayout.getItemStacks().set(ingredients);
		
	}
	
	
}
