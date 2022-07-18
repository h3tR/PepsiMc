package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;




import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class FlavoringRecipeCategory implements IRecipeCategory<FlavoringRecipe>{
	public static final ResourceLocation UID = new ResourceLocation("pepsimc","flavor");
    private final IDrawable Background;
    private final IDrawable Icon;

	public FlavoringRecipeCategory(IGuiHelper helper) {
		this.Background = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/flavor_machine_gui.png"), 0,0, 176, 85);
		this.Icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(PepsiMcBlock.FLAVOR_MACHINE.get()));
	}
	
	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends FlavoringRecipe> getRecipeClass() {
		return FlavoringRecipe.class;
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
	public Component getTitle() {
		return new TranslatableComponent("block.pepsimc.flavor_machine");
	}
    @Override
    public void setRecipe(IRecipeLayoutBuilder LayoutBuilder, FlavoringRecipe recipe, IFocusGroup focusGroup) {
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,80,20).addIngredients(recipe.getIngredients().get(0));
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,42,42).addIngredients(recipe.getIngredients().get(1));
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,80,64).addItemStack(recipe.getResultItem());

    }
	
}
