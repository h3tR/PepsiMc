package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;




import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.configuration.CommonConfig;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.util.FEValueHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import static net.minecraft.client.gui.GuiComponent.drawString;

public class RecyclerRecipeCategory implements IRecipeCategory<RecyclerRecipe>{
	public static final ResourceLocation UID = new ResourceLocation("pepsimc","recycler");
    private final IDrawable Background;
    private final IDrawable Icon;

	public RecyclerRecipeCategory(IGuiHelper helper) {
		this.Background = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/recycler_gui.png"), 0,0, 176, 85);
		this.Icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(PepsiMcBlock.RECYCLER.get()));
	}
	
	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public Class<? extends RecyclerRecipe> getRecipeClass() {
		return RecyclerRecipe.class;
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
	public void draw(RecyclerRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
		stack.scale(.5f,.5f,.5f);
		drawString(stack, Minecraft.getInstance().font,"FE/t: "+ FEValueHelper.getFEValue(CommonConfig.RECYCLER_FE_USAGE_PER_TICK.get()), 10, 138, 0xffffff);
		drawString(stack, Minecraft.getInstance().font,"Total ticks: "+ recipe.ticks, 10, 150, 0xffffff);
		drawString(stack, Minecraft.getInstance().font, "Total FE usage: "+FEValueHelper.getFEValue(CommonConfig.RECYCLER_FE_USAGE_PER_TICK.get()*recipe.ticks), 10, 162, 0xffffff);

	}

    @Override
    public void setRecipe(IRecipeLayoutBuilder LayoutBuilder, RecyclerRecipe recipe, IFocusGroup focusGroup) {
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,80,9).addIngredients(recipe.getIngredients().get(0));
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,42,31).addIngredients(recipe.getIngredients().get(1));
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,80,53).addItemStack(recipe.getResultItem());

    }
	@Override
	public Component getTitle() {
		return new TranslatableComponent("block.pepsimc.recycler");
	}
	

}
