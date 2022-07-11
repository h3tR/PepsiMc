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
import org.jetbrains.annotations.NotNull;

public class FlavoringRecipe implements Recipe<SimpleContainer>{
	private final ResourceLocation id;
	private final ItemStack out;
	private final NonNullList<Ingredient> in;
	
	public FlavoringRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In) {
		this.id = Id;
		this.out = Out;
		this.in = In;
	}

	@Override
	public @NotNull ItemStack getResultItem() {
		return out.copy();
	}

	
	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.FLAVORING_SERIALIZER.get();
	}
	
	@Override
	public @NotNull NonNullList<Ingredient> getIngredients() {
		return in;
	}
	
	@Override
	public @NotNull ResourceLocation getId() {
		return id;
	}
	
	@Override
	public @NotNull ItemStack getToastSymbol() {
		return new ItemStack(PepsiMcBlock.FLAVOR_MACHINE.get());
	}
	
	public static class FlavoringRecipeType implements RecipeType<FlavoringRecipe>{
		private FlavoringRecipeType() { }
		public static final String ID = "flavor";
		public static final FlavoringRecipeType INSTANCE = new FlavoringRecipeType();
	}
	
	public static class Serializer implements RecipeSerializer<FlavoringRecipe>{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public FlavoringRecipe fromJson(ResourceLocation Id, JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonObject Flavor = GsonHelper.getAsJsonObject(json, "flavor");
			JsonObject Product = GsonHelper.getAsJsonObject(json, "product");

			NonNullList<Ingredient> In = NonNullList.withSize(2, Ingredient.EMPTY);
			In.set(0, Ingredient.fromJson(Product));
			In.set(1, Ingredient.fromJson(Flavor));
			return new FlavoringRecipe(Id, Out, In);
		}

		@Nullable
		@Override
		public FlavoringRecipe fromNetwork(ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

			In.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
			
			ItemStack Out = buffer.readItem();

			return new FlavoringRecipe(Id, Out, In);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, FlavoringRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getResultItem(), false);

		}
		
	}

	@Override
	public boolean matches(SimpleContainer inv, Level __) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1));
	}

	@Override
	public ItemStack assemble(SimpleContainer __) {
		return out;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}

	@Override
	public RecipeType<?> getType() {
		return FlavoringRecipeType.INSTANCE;
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}
	
}
