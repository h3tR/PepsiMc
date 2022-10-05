package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.integration.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;

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
		List<CentrifugeRecipe> CentrifugeRecipes = rm.getAllRecipesFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE);
		List<FlavoringRecipe> FlavoringRecipes = rm.getAllRecipesFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE);
		List<RecyclerRecipe> RecyclerRecipes = rm.getAllRecipesFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE);

		registration.addRecipes(new RecipeType<>(BottlerRecipeCategory.UID, BottlerRecipe.class), BottlerRecipes);
		registration.addRecipes(new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class), CentrifugeRecipes);
		registration.addRecipes(new RecipeType<>(FlavoringRecipeCategory.UID, FlavoringRecipe.class), FlavoringRecipes);
		registration.addRecipes(new RecipeType<>(RecyclerRecipeCategory.UID, RecyclerRecipe.class), RecyclerRecipes);

		IIngredientManager manager =  registration.getIngredientManager();

		ArrayList<ItemStack> removeBlockItemList = new ArrayList<>();
		removeBlockItemList.add(new ItemStack(PepsiMcItem.GENERATOR.get()));

		ArrayList<Block> removeBlockList = new ArrayList<>();
		removeBlockList.add(PepsiMcBlock.GENERATOR.get());

		manager.removeIngredientsAtRuntime(VanillaTypes.ITEM, removeBlockItemList);

	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		IModPlugin.super.registerRecipeCatalysts(registration);
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.BOTTLER.get()),new RecipeType<>(BottlerRecipeCategory.UID, BottlerRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.AUTOMATED_BOTTLER.get()),new RecipeType<>(BottlerRecipeCategory.UID, BottlerRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.CENTRIFUGE.get()),new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.AUTOMATED_CENTRIFUGE.get()),new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.FLAVOR_MACHINE.get()),new RecipeType<>(FlavoringRecipeCategory.UID, FlavoringRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.AUTOMATED_FLAVOR_MACHINE.get()),new RecipeType<>(FlavoringRecipeCategory.UID, FlavoringRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.RECYCLER.get()),new RecipeType<>(RecyclerRecipeCategory.UID, RecyclerRecipe.class));
		registration.addRecipeCatalyst(new ItemStack(PepsiMcBlock.AUTOMATED_RECYCLER.get()),new RecipeType<>(RecyclerRecipeCategory.UID, RecyclerRecipe.class));


	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		IModPlugin.super.registerRecipeTransferHandlers(registration);
		registration.addRecipeTransferHandler(BottlerMenu.class,new RecipeType<>(BottlerRecipeCategory.UID, BottlerRecipe.class),36,3,0,36);
		registration.addRecipeTransferHandler(AutomatedBottlerMenu.class,new RecipeType<>(BottlerRecipeCategory.UID, BottlerRecipe.class),36,3,0,36);
		registration.addRecipeTransferHandler(CentrifugeMenu.class,new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class),36,1,0,36);
		registration.addRecipeTransferHandler(AutomatedCentrifugeMenu.class,new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class),36,1,0,36);
		registration.addRecipeTransferHandler(FlavorMachineMenu.class,new RecipeType<>(FlavoringRecipeCategory.UID, FlavoringRecipe.class),36,2,0,36);
		registration.addRecipeTransferHandler(AutomatedFlavorMachineMenu.class,new RecipeType<>(FlavoringRecipeCategory.UID, FlavoringRecipe.class),36,2,0,36);
		registration.addRecipeTransferHandler(RecyclerMenu.class,new RecipeType<>(RecyclerRecipeCategory.UID, RecyclerRecipe.class),36,2,0,36);
		registration.addRecipeTransferHandler(AutomatedRecyclerMenu.class,new RecipeType<>(RecyclerRecipeCategory.UID, RecyclerRecipe.class),36,2,0,36);
	}
}
