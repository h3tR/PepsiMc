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

import java.util.Objects;

public class CentrifugeRecipe extends ProcessingRecipe{
	static ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "centrifuge");
	private final ItemStack extra;

	public CentrifugeRecipe(ResourceLocation Id, ItemStack Out, ItemStack Byproduct,NonNullList<Ingredient> In,int ticks) {
		super(Id, Out, In, ticks);
		this.extra = Byproduct;
	}
	
	@Override
	public boolean matches(Container inv, @NotNull Level Win) {
		return in.get(0).test(inv.getItem(0));
	}

	public ItemStack getByproductItem() {
		return extra.copy();
	}

	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.CENTRIFUGE_SERIALIZER.get();
	}

	
	@Override
	public @NotNull ItemStack getToastSymbol() {
		return new ItemStack(PepsiMcBlock.CENTRIFUGE.get());
	}
	
	public static class CentrifugeRecipeType implements RecipeType<CentrifugeRecipe>{
		private CentrifugeRecipeType() { }
		public static final CentrifugeRecipe.CentrifugeRecipeType INSTANCE = new CentrifugeRecipe.CentrifugeRecipeType();
		@Override
		public String toString() {
			return CentrifugeRecipe.TYPE_ID.toString();
		}
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CentrifugeRecipe>{


		@Override
		public @NotNull CentrifugeRecipe fromJson(@NotNull ResourceLocation Id, @NotNull JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			ItemStack Extra = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "byproduct"));
			JsonObject Extract = GsonHelper.getAsJsonObject(json, "extract");

			NonNullList<Ingredient> In = NonNullList.withSize(1, Ingredient.EMPTY);
			In.set(0, Ingredient.fromJson(Extract));
			return new CentrifugeRecipe(Id, Out, Extra, In,GsonHelper.getAsInt(json, "ticks"));
		}

		@Nullable
		@Override
		public CentrifugeRecipe fromNetwork(@NotNull ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            In.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
			
			ItemStack Extra = buffer.readItem();
			ItemStack Out = buffer.readItem();
			int ticks = buffer.readInt();

			return new CentrifugeRecipe(Id, Out, Extra, In, ticks);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, CentrifugeRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Objects.requireNonNull(Recipe.getByproductItem()), false);
			buffer.writeItemStack(Recipe.getResultItem(), false);
			buffer.writeInt(Recipe.ticks);

		}
		
	}


	@Override
	public @NotNull RecipeType<?> getType() {
		return CentrifugeRecipeType.INSTANCE;
	}
	

}
