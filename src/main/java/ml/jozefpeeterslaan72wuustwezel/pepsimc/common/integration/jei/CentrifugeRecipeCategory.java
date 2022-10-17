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
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.util.FEValueHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.minecraft.client.gui.GuiComponent.drawString;

public class CentrifugeRecipeCategory implements IRecipeCategory<CentrifugeRecipe>{
	public static final ResourceLocation UID = new ResourceLocation("pepsimc","centrifuge");
    private final IDrawable Background;
    private final IDrawable Icon;

	public CentrifugeRecipeCategory(IGuiHelper helper) {
		this.Background = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/centrifuge_gui.png"), 0,0, 176, 85);
		this.Icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(PepsiMcBlock.CENTRIFUGE.get()));
	}
	
	@Override
	public @NotNull ResourceLocation getUid() {
		return UID;
	}

	@Override
	public @NotNull Class<? extends CentrifugeRecipe> getRecipeClass() {
		return CentrifugeRecipe.class;
	}

	@Override
	public @NotNull IDrawable getBackground() {
		return this.Background;
	}

	@Override
	public @NotNull IDrawable getIcon() {
		return this.Icon;
	}

	@Override
	public void draw(@NotNull CentrifugeRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull PoseStack stack, double mouseX, double mouseY) {
		IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
		stack.scale(.5f,.5f,.5f);
		drawString(stack, Minecraft.getInstance().font,"FE/t: "+ FEValueHelper.getFEValue(CommonConfig.CENTRIFUGE_FE_USAGE_PER_TICK.get()), 10, 138, 0xffffff);
		drawString(stack, Minecraft.getInstance().font,"Total ticks: "+ recipe.ticks, 10, 150, 0xffffff);
		drawString(stack, Minecraft.getInstance().font, "Total FE usage: "+FEValueHelper.getFEValue(CommonConfig.CENTRIFUGE_FE_USAGE_PER_TICK.get()*recipe.ticks), 10, 162, 0xffffff);

	}

    @Override
    public void setRecipe(IRecipeLayoutBuilder LayoutBuilder, CentrifugeRecipe recipe, @NotNull IFocusGroup focusGroup) {
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,80,9).addIngredients(recipe.getIngredients().get(0));
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,66,53).addItemStack(recipe.getResultItem());
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,94,53).addItemStack(Objects.requireNonNull(recipe.getByproductItem()));
    }

	@Override
	public @NotNull Component getTitle() {
		return new TranslatableComponent("block.pepsimc.centrifuge");
	}
	

}
