package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedBottlerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class AutomatedBottlerEntity extends AutomatedProcessingBlockEntity implements MenuProvider {

    public AutomatedBottlerEntity(BlockPos pos, BlockState state) {
        super(PepsiMcBlockEntity.AUTOMATED_BOTTLER_BLOCK_ENTITY.get(), pos, state, 500, 5);
    }

    protected Optional<BottlerRecipe> getRecipe(){
      return this.getLevel().getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(),this.getLevel());
    }

    protected void finishProduct() {
        Optional<BottlerRecipe> recipe = getRecipe();
        recipe.ifPresent(iRecipe->{
            itemHandler.extractItem(0, 1, false);
            itemHandler.extractItem(1, 1, false);
            itemHandler.extractItem(2, 1, false);
            itemHandler.insertItem(3, iRecipe.getResultItem(), false);
            itemHandler.insertItem(4, iRecipe.getByproductItem(), false);
            setChanged();
        });
    }

    @Override
    protected int getOutputSlot() {
        return 3;
    }

    @Nullable
    @Override
    protected int getByProductSlot() {
        return 4;
    }


    @Override
    protected ItemStackHandler createHandler() {

        return new ItemStackHandler(5) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public int getSlotLimit(int slot){
                if(slot == 4)
                    return 16;
                return 64;
            }


            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return switch (slot) {
                    case 0 ->
                            stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_CONTAINER);
                    case 1 ->
                            stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LABEL);
                    case 2 ->
                            stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LIQUID);
                    case 3 ->
                            stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLED_LIQUID);
                    case 4 -> stack.getItem() == Items.BUCKET;
                    default -> false;
                };
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

    @Override
    public @NotNull Component getDisplayName() {
        return new TranslatableComponent("block.pepsimc.bottler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player p_39956_) {
        return new AutomatedBottlerMenu(id,inv,this,dataAccess);
    }
}
