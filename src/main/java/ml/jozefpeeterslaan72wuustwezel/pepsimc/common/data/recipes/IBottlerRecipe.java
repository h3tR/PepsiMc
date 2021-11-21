package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public interface IBottlerRecipe extends Recipe<Container>{
	
	ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "bottler");
	
	@Override
	default RecipeType<?> getType() {
		return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
	}
	
	@Override
	default boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	default boolean isSpecial() {
		// TODO Auto-generated method stub
		return true;
	}
}
