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

public class CentrifugeRecipe implements Recipe<Container>{
	static ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "centrifuge");

	private final ResourceLocation id;
	private final ItemStack out;
	private final ItemStack extra;

	private final NonNullList<Ingredient> in;
	
	public CentrifugeRecipe(ResourceLocation Id, ItemStack Out, ItemStack Byproduct,NonNullList<Ingredient> In) {
		this.id = Id;
		this.out = Out;
		this.in = In;
		this.extra = Byproduct;
	}
	
	@Override
	public boolean matches(Container inv, Level Win) {
		return in.get(0).test(inv.getItem(0));
	}
	
	@Override
	public ItemStack getResultItem() {
		return out.copy();
	}
	
	public ItemStack getByproductItem() {
		return extra.copy();
	}
	
	
	@Override
	public ItemStack assemble(Container inv) {
		return out;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.CENTRIFUGE_SERIALIZER.get();
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
		public CentrifugeRecipe fromJson(ResourceLocation Id, JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			ItemStack Extra = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "byproduct"));
			JsonObject Extract = GsonHelper.getAsJsonObject(json, "extract");
			NonNullList<Ingredient> In = NonNullList.withSize(1, Ingredient.EMPTY);
			In.set(0, Ingredient.fromJson(Extract));
			return new CentrifugeRecipe(Id, Out,Extra, In);
		}

		@Nullable
		@Override
		public CentrifugeRecipe fromNetwork(ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
			
			for(int i = 0;i<In.size(); i++) {
				In.set(i, Ingredient.fromNetwork(buffer));
			}
			
			ItemStack Extra = buffer.readItem();
			ItemStack Out = buffer.readItem();

			return new CentrifugeRecipe(Id, Out, Extra, In);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, CentrifugeRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getByproductItem(), false);
			buffer.writeItemStack(Recipe.getResultItem(), false);

		}
		
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}

	@Override
	public RecipeType<?> getType() {
		return CentrifugeRecipeType.INSTANCE;
	}
	
	@Override
	public boolean isSpecial() {
		return true;
	}
	
}
