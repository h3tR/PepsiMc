package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;


import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class BottlerRecipe implements IBottlerRecipe{
	
	private final ResourceLocation id;
	private final ItemStack out;
	private final NonNullList<Ingredient> in;
	
	public BottlerRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In) {
		this.id = Id;
		this.out = Out;
		this.in = In;
	}
	
	@Override
	public boolean matches(Container inv, Level Win) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1))&&in.get(2).test(inv.getItem(2));
	}
	
	@Override
	public ItemStack getResultItem() {
		return out.copy();
	}
	
	@Override
	public ItemStack assemble(Container inv) {
		return out;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.BOTTLER_SERIALIZER.get();
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return in;
	}
	
	@Override
	public ResourceLocation getId() {
		return id;
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(PepsiMcBlock.BOTTLER.get());
	}
	
	public static class BottlerRecipeType implements RecipeType<BottlerRecipe>{
		@Override
		public String toString() {
			return BottlerRecipe.TYPE_ID.toString();
		}
	}
	
	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<BottlerRecipe>{


		@Override
		public BottlerRecipe fromJson(ResourceLocation Id, JsonObject json) {
			ItemStack Out = ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonObject Label = GsonHelper.getAsJsonObject(json, "label");
			JsonObject Container = GsonHelper.getAsJsonObject(json, "container");
			JsonObject Fluid = GsonHelper.getAsJsonObject(json, "fluid");
			NonNullList<Ingredient> In = NonNullList.withSize(3, Ingredient.EMPTY);
			In.set(1, Ingredient.fromJson(Label));
			In.set(0, Ingredient.fromJson(Container));
			In.set(2, Ingredient.fromJson(Fluid));
			return new BottlerRecipe(Id, Out, In);
		}

		@Nullable
		@Override
		public BottlerRecipe fromNetwork(ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(3, Ingredient.EMPTY);
			
			for(int i = 0;i<In.size(); i++) {
				In.set(i, Ingredient.fromNetwork(buffer));
			}
			
			ItemStack Out = buffer.readItem();

			return new BottlerRecipe(Id, Out, In);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, BottlerRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getResultItem(), false);

		}
		
	}
	
}
