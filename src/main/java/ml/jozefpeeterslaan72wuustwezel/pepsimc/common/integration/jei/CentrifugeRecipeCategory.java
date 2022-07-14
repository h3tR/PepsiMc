package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;



import java.util.ArrayList;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class CentrifugeRecipeCategory {/*implements IRecipeCategory<CentrifugeRecipe>{
	//TODO
	public static final ResourceLocation UID = new ResourceLocation("pepsimc","centrifuge");
	private final IDrawable bg;
	private final IDrawable icon;

	public CentrifugeRecipeCategory(IGuiHelper helper) {
		this.bg = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/centrifuge_gui.png"), 0,0, 176, 85);
		this.icon = helper.createDrawableIngredient(new ItemStack(PepsiMcBlock.CENTRIFUGE.get()));
	}
	
	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends CentrifugeRecipe> getRecipeClass() {
		return CentrifugeRecipe.class;
	}

	@Override
	public IDrawable getBackground() {
		return this.bg;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setIngredients(CentrifugeRecipe recipe, IIngredients ingredients) {
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		result.add(recipe.getResultItem());
		result.add(recipe.getByproductItem());

		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutputs(VanillaTypes.ITEM, result);

		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CentrifugeRecipe recipe, IIngredients ingredients) {		
		recipeLayout.getItemStacks().init(0, true, 79, 8);
		recipeLayout.getItemStacks().init(1, false, 65, 52);
		recipeLayout.getItemStacks().init(2, false, 93, 52);

		recipeLayout.getItemStacks().set(ingredients);
		
	}

	@Override
	public Component getTitle() {
		return Component.translatable("block.pepsimc.centrifuge");
	}
	
	*/
}
