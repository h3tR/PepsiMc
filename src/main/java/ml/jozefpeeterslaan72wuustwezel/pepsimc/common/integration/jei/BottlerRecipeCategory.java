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
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.util.FEValueHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.client.gui.GuiComponent.drawString;

public class BottlerRecipeCategory implements IRecipeCategory<BottlerRecipe>{

    private final IDrawable Background;
    private final IDrawable Icon;

    public BottlerRecipeCategory(IGuiHelper helper) {
        this.Background = helper.createDrawable(new ResourceLocation("pepsimc","textures/gui/jei/bottler_gui.png"), 0,0, 176, 85);
        this.Icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(PepsiMcBlock.BOTTLER.get()));
    }
    public static final ResourceLocation UID = new ResourceLocation("pepsimc","bottler");
    @Override
    public @NotNull Component getTitle() {
        return new TranslatableComponent("block.pepsimc.bottler");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return Background;
    }

    @Override
    public void draw(@NotNull BottlerRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull PoseStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
        stack.scale(.5f,.5f,.5f);
        drawString(stack, Minecraft.getInstance().font,"FE/t: "+ FEValueHelper.getFEValue(CommonConfig.BOTTLER_FE_USAGE_PER_TICK.get()), 10, 138, 0xffffff);
        drawString(stack, Minecraft.getInstance().font,"Total ticks: "+ recipe.ticks, 10, 150, 0xffffff);
        drawString(stack, Minecraft.getInstance().font, "Total FE usage: "+FEValueHelper.getFEValue(CommonConfig.BOTTLER_FE_USAGE_PER_TICK.get()*recipe.ticks), 10, 162, 0xffffff);

    }

    @Override
    public @NotNull IDrawable getIcon() {
        return Icon;
    }

    @Override
    public @NotNull ResourceLocation getUid() {
        return UID;
    }

    @Override
    public @NotNull Class<? extends BottlerRecipe> getRecipeClass() {
        return BottlerRecipe.class;
    }



    @Override
    public void setRecipe(IRecipeLayoutBuilder LayoutBuilder, BottlerRecipe recipe, @NotNull IFocusGroup focusGroup) {
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,12,15).addIngredients(recipe.getIngredients().get(0));
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,30,15).addIngredients(recipe.getIngredients().get(1));
        LayoutBuilder.addSlot(RecipeIngredientRole.INPUT,12,43).addIngredients(recipe.getIngredients().get(2));
        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,143,30).addItemStack(recipe.getResultItem());

        LayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT,143,51).addItemStack(new ItemStack(Items.BUCKET,1));


    }


}
