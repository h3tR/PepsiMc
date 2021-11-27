package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;


import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class BottlerRecipe implements IBottlerRecipe{
	
	private final ResourceLocation id;
	private final ItemStack out;
	private final NonNullList<Ingredient> in;
	
	public BottlerRecipe(ResourceLocation id, ItemStack out, NonNullList<Ingredient> in) {
		this.id = id;
		this.out = out;
		this.in = in;
	}
	
	@Override
	public boolean matches(IInventory inv, World Win) {
		return in.get(0).test(inv.getItem(0))&&in.get(1).test(inv.getItem(1))&&in.get(2).test(inv.getItem(2));
	}
	
	@Override
	public ItemStack getResultItem() {
		return out.copy();
	}
	
	@Override
	public ItemStack assemble(IInventory inv) {
		return out;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
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
	
	public static class BottlerRecipeType implements IRecipeType<BottlerRecipe>{
		@Override
		public String toString() {
			return BottlerRecipe.TYPE_ID.toString();
		}
	}
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BottlerRecipe>{


		@Override
		public BottlerRecipe fromJson(ResourceLocation id, JsonObject json) {
			ItemStack out = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
			JsonObject label = JSONUtils.getAsJsonObject(json, "label");
			JsonObject container = JSONUtils.getAsJsonObject(json, "container");
			JsonObject fluid = JSONUtils.getAsJsonObject(json, "fluid");
			NonNullList<Ingredient> in = NonNullList.withSize(3, Ingredient.EMPTY);
			in.set(1, Ingredient.fromJson(label));
			in.set(0, Ingredient.fromJson(container));
			in.set(2, Ingredient.fromJson(fluid));
			return new BottlerRecipe(id, out, in);
		}

		@Nullable
		@Override
		public BottlerRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
			NonNullList<Ingredient> in = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
			
			for(int i = 0;i<in.size(); i++) {
				in.set(i, Ingredient.fromNetwork(buffer));
			}
			
			ItemStack out = buffer.readItem();

			return new BottlerRecipe(id, out, in);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, BottlerRecipe recipe) {
			buffer.writeInt(recipe.getIngredients().size());
			for(Ingredient ing : recipe.getIngredients()) {
				ing.toNetwork(buffer);
			}
			buffer.writeItemStack(recipe.getResultItem(), false);

		}
		
	}
	
}
