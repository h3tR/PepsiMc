package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;



import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FlavoringRecipe extends ProcessingRecipe{
	static ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "flavor");
	
	public FlavoringRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In,int Ticks) {
		super(Id,Out,In,Ticks);
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public ItemStack getByproductItem() {
		return null;
	}

	@Override
	public boolean matches(Container inv, Level Win) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1));
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.FLAVORING_SERIALIZER.get();
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(PepsiMcBlock.FLAVOR_MACHINE.get());
	}
	
	public static class FlavoringRecipeType implements RecipeType<FlavoringRecipe>{
		private FlavoringRecipeType() { }
		public static final FlavoringRecipe.FlavoringRecipeType INSTANCE = new FlavoringRecipe.FlavoringRecipeType();
		@Override
		public String toString() {
			return FlavoringRecipe.TYPE_ID.toString();
		}
	}
	
	public static class Serializer  extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FlavoringRecipe>{
		@Override
		public FlavoringRecipe fromJson(ResourceLocation Id, JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonObject Flavor = GsonHelper.getAsJsonObject(json, "flavor");
			JsonObject Product = GsonHelper.getAsJsonObject(json, "product");

			NonNullList<Ingredient> In = NonNullList.withSize(2, Ingredient.EMPTY);
			In.set(0, Ingredient.fromJson(Product));
			In.set(1, Ingredient.fromJson(Flavor));
			return new FlavoringRecipe(Id, Out, In,GsonHelper.getAsInt(json, "ticks"));
		}

		@Nullable
		@Override
		public FlavoringRecipe fromNetwork(ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
			
			for(int i = 0;i<In.size(); i++) {
				In.set(i, Ingredient.fromNetwork(buffer));
			}
			
			ItemStack Out = buffer.readItem();
			int ticks = buffer.readInt();

			return new FlavoringRecipe(Id, Out, In,ticks);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, FlavoringRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getResultItem(), false);
			buffer.writeInt(Recipe.ticks);

		}
		
	}

	@Override
	public RecipeType<?> getType() {
		return FlavoringRecipeType.INSTANCE;
	}

	
}
