package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class ProcessingRecipe implements Recipe<Container> {
    public final int ticks;


    protected final ResourceLocation id;
    protected final ItemStack out;
    protected final NonNullList<Ingredient> in;
    protected ProcessingRecipe(ResourceLocation Id, ItemStack Out, NonNullList<Ingredient> In, int ticks) {
        this.ticks = ticks;
        this.id = Id;
        this.out = Out;
        this.in = In;
    }
    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return out.copy();
    }


    @Override
    public @NotNull ItemStack assemble(@NotNull Container inv) {
        return out;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return in;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Nullable
    public abstract ItemStack getByproductItem();

}
