package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;




import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class CentrifugeRecipeCategory implements IRecipeCategory<CentrifugeRecipe>{
	public static final ResourceLocation UID = new ResourceLocation("pepsimc","centrifuge");
    private final IDrawable Background;
    private final IDrawable Icon;

	public CentrifugeRecipeCategory(IGuiHelper helper) {
		this.Background = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/centrifuge_gui.png"), 0,0, 176, 85);
		this.Icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(PepsiMcBlock.CENTRIFUGE.get()));
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
		return this.Background;
	}

	@Override
	public IDrawable getIcon() {
		return this.Icon;
	}

    @Override
    public void setRecipe(IRecipeLayoutBuilder LayoutBuilder, CentrifugeRecipe recipe, IFocusGroup focusGroup) {
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,80,9).addIngredients(recipe.getIngredients().get(0));
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,66,53).addItemStack(recipe.getResultItem());
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,94,53).addItemStack(recipe.getByproductItem());
    }

	@Override
	public Component getTitle() {
		return new TranslatableComponent("block.pepsimc.centrifuge");
	}
	

}
