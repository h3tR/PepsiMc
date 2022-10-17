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
import org.jetbrains.annotations.NotNull;

public class RecyclerRecipe extends ProcessingRecipe{
	static final ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "recycler");
	
	public RecyclerRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In, int ticks) {
		super(Id, Out, In, ticks);
	}

	@org.jetbrains.annotations.Nullable
	@Override
	public ItemStack getByproductItem() {
		return null;
	}

	@Override
	public boolean matches(Container inv, @NotNull Level Win) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1));
	}
	
	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.RECYCLER_SERIALIZER.get();
	}
	
	@Override
	public @NotNull ItemStack getToastSymbol() {
		return new ItemStack(PepsiMcBlock.RECYCLER.get());
	}
	
	public static class RecyclerRecipeType implements RecipeType<RecyclerRecipe>{
		private RecyclerRecipeType() { }
		public static final RecyclerRecipe.RecyclerRecipeType INSTANCE = new RecyclerRecipe.RecyclerRecipeType();
		@Override
		public String toString() {
			return RecyclerRecipe.TYPE_ID.toString();
		}
	}
	
	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<RecyclerRecipe>{

		@Override
		public @NotNull RecyclerRecipe fromJson(@NotNull ResourceLocation Id, @NotNull JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonObject Catalyst = GsonHelper.getAsJsonObject(json, "catalyst");
			JsonObject Recycle = GsonHelper.getAsJsonObject(json, "recycle");
			NonNullList<Ingredient> In = NonNullList.withSize(2, Ingredient.EMPTY);
			In.set(0, Ingredient.fromJson(Recycle));
			In.set(1, Ingredient.fromJson(Catalyst));
			return new RecyclerRecipe(Id, Out, In,GsonHelper.getAsInt(json, "ticks"));
		}

		@Nullable
		@Override
		public RecyclerRecipe fromNetwork(@NotNull ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

			In.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
			
			ItemStack Out = buffer.readItem();
			int ticks = buffer.readInt();
			return new RecyclerRecipe(Id, Out, In, ticks);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, RecyclerRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getResultItem(), false);
			buffer.writeInt(Recipe.ticks);
		}
		
	}


	@Override
	public @NotNull RecipeType<?> getType() {
		return RecyclerRecipeType.INSTANCE;
	}
	
}
