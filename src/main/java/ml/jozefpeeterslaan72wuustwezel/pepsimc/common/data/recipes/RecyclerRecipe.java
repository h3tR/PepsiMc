package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;



import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class RecyclerRecipe implements Recipe<SimpleContainer>{
	private final ResourceLocation id;
	private final ItemStack out;
	private final NonNullList<Ingredient> in;
	
	public RecyclerRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In) {
		this.id = Id;
		this.out = Out;
		this.in = In;
	}
	
	@Override
	public boolean matches(SimpleContainer inv, Level Win) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1));
	}
	
	@Override
	public ItemStack getResultItem() {
		return out.copy();
	}
	
	
	@Override
	public ItemStack assemble(SimpleContainer inv) {
		return out;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.RECYCLER_SERIALIZER.get();
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
		return new ItemStack(PepsiMcBlock.RECYCLER.get());
	}

	public static class RecyclerRecipeType implements RecipeType<RecyclerRecipe>{
		private RecyclerRecipeType() { }
		public static final String ID = "recycler";
		public static final RecyclerRecipe.RecyclerRecipeType INSTANCE = new RecyclerRecipe.RecyclerRecipeType();
	}
	public static class Serializer implements RecipeSerializer<RecyclerRecipe>{
		public static final RecyclerRecipe.Serializer INSTANCE = new RecyclerRecipe.Serializer();
		@Override
		public RecyclerRecipe fromJson(ResourceLocation Id, JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonObject Catalyst = GsonHelper.getAsJsonObject(json, "catalyst");
			JsonObject Recycle = GsonHelper.getAsJsonObject(json, "recycle");
			NonNullList<Ingredient> In = NonNullList.withSize(2, Ingredient.EMPTY);
			In.set(0, Ingredient.fromJson(Recycle));
			In.set(1, Ingredient.fromJson(Catalyst));
			return new RecyclerRecipe(Id, Out, In);
		}

		@Nullable
		@Override
		public RecyclerRecipe fromNetwork(ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

			In.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
			
			ItemStack Out = buffer.readItem();

			return new RecyclerRecipe(Id, Out, In);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, RecyclerRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getResultItem(), false);

		}
		
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}

	@Override
	public RecipeType<?> getType() {
		return RecyclerRecipeType.INSTANCE;
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}
	
}
