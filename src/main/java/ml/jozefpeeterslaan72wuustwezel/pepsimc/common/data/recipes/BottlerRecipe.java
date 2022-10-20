package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;



import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import com.mojang.serialization.JsonOps;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.Container;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;

public class BottlerRecipe extends ProcessingRecipe{
	static ResourceLocation TYPE_ID = new ResourceLocation("pepsimc", "bottler");
	private final FluidStack fluid;
	public BottlerRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In,  FluidStack fluidStack, int ticks) {
		super(Id, Out, In, ticks);

		fluid = fluidStack;
	}
	
	@Override
	public boolean matches(Container inv, @NotNull Level Win) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1));
	}
	public boolean isBucketFluid(ItemStack itemStack){
		if(!(itemStack.getItem() instanceof BucketItem bucket))
			return false;
		return bucket.getFluid().isSame(getFluid().getFluid());
	}


	public FluidStack getFluid() {
		return fluid;
	}

	public ItemStack getByproductItem() {
		return new ItemStack(Items.BUCKET);
	}


	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return PepsiMcRecipeType.BOTTLER_SERIALIZER.get();
	}
	

	
	@Override
	public @NotNull ItemStack getToastSymbol() {
		return new ItemStack(PepsiMcBlock.BOTTLER.get());
	}
	
	public static class BottlerRecipeType implements RecipeType<BottlerRecipe>{
		private BottlerRecipeType() { }
		public static final BottlerRecipeType INSTANCE = new BottlerRecipeType();
		@Override
		public String toString() {
			return BottlerRecipe.TYPE_ID.toString();
		}
	}
	
	public static class Serializer  extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<BottlerRecipe>{


		@Override
		public @NotNull BottlerRecipe fromJson(@NotNull ResourceLocation Id, @NotNull JsonObject json) {
			ItemStack Out = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			JsonObject Label = GsonHelper.getAsJsonObject(json, "label");
			JsonObject Container = GsonHelper.getAsJsonObject(json, "container");
			FluidStack fluid = FluidJSONUtil.readFluid(GsonHelper.getAsJsonObject(json, "fluid"));
			NonNullList<Ingredient> In = NonNullList.withSize(3, Ingredient.EMPTY);
			In.set(1, Ingredient.fromJson(Label));
			In.set(0, Ingredient.fromJson(Container));
			return new BottlerRecipe(Id, Out, In, fluid, GsonHelper.getAsInt(json, "ticks"));
		}

		@Nullable
		@Override
		public BottlerRecipe fromNetwork(@NotNull ResourceLocation Id, FriendlyByteBuf buffer) {
			NonNullList<Ingredient> In = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            In.replaceAll(ignored -> Ingredient.fromNetwork(buffer));
			
			ItemStack Out = buffer.readItem();
			FluidStack fluidStack = buffer.readFluidStack();
			int ticks = buffer.readInt();
			return new BottlerRecipe(Id, Out, In, fluidStack, ticks );
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, BottlerRecipe Recipe) {
			buffer.writeInt(Recipe.getIngredients().size());
			for(Ingredient ing : Recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(Recipe.getResultItem(), false);
			buffer.writeFluidStack(Recipe.getFluid());
			buffer.writeInt(Recipe.ticks);
		}
		
	}
	public static class FluidJSONUtil {
		public static FluidStack readFluid(JsonObject json) {
			return FluidStack.CODEC.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
		}

	}


	@Override
	public @NotNull RecipeType<?> getType() {
		return BottlerRecipeType.INSTANCE;
	}
	

	
}
