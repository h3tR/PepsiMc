package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IBottlerRecipe extends IRecipe<IInventory>{
	
	ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "bottler");
	
	@Override
	default IRecipeType<?> getType() {
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
