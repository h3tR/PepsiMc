package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Optional;

public class AutomatedBottlerEntity extends AutomatedProcessingBlockEntity {

    public AutomatedBottlerEntity(BlockEntityType<?> in, BlockPos pos, BlockState state) {
        super(in, pos, state);
    }

    @Override
    protected void tickServer(BlockState state) {

        super.tickServer(state);
    }
    protected Optional<BottlerRecipe> getRecipe(){
      return this.level.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(), this.level);
    }

    protected void finishProduct() {
        Optional<BottlerRecipe> recipe = getRecipe();
        recipe.ifPresent(h->{
            itemHandler.extractItem(0, 1, false);
            itemHandler.extractItem(1, 1, false);
            itemHandler.extractItem(2, 1, false);
            itemHandler.insertItem(3, h.getResultItem(), false);
            itemHandler.insertItem(4, new ItemStack(Items.BUCKET), false);
            setChanged();
        });
    }

    @Override
    protected ItemStackHandler createHandler() {

        return new ItemStackHandler(2) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public int getSlotLimit(int slot){
                return 64;
            }


            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0: return true;
                    default:
                        return false;

                }
            }
            @Override
            @Nonnull
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
}
}
