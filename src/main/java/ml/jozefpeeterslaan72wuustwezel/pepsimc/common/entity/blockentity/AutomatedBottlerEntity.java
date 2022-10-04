package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlockStateProperties;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedBottlerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.configuration.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class AutomatedBottlerEntity extends AutomatedProcessingBlockEntity implements MenuProvider {

    public AutomatedBottlerEntity(BlockPos pos, BlockState state) {
        super(PepsiMcBlockEntity.AUTOMATED_BOTTLER_BLOCK_ENTITY.get(), pos, state, CommonConfig.BOTTLER_FE_STORAGE.get(), CommonConfig.BOTTLER_CONDUCTIVITY.get(),CommonConfig.BOTTLER_FE_USAGE_PER_TICK.get());
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

    @Override
    protected int getByProductSlot() {
        return 4;
    }

    @Override
    public void tickServer() {
        super.tickServer();
        PepsiMcBlockStateProperties.BottlerActivity activity;
        boolean Powered;


        if (energyStorage.getEnergyStored() <= 0) {
            Powered = false;
            activity = PepsiMcBlockStateProperties.BottlerActivity.NONE;
        } else {
            Powered = true;
            if (isActive() && itemHandler.getStackInSlot(0).sameItem(new ItemStack(PepsiMcItem.EMPTY_BOTTLE.get())))
                activity = PepsiMcBlockStateProperties.BottlerActivity.BOTTLE;
            else if (isActive() && itemHandler.getStackInSlot(0).sameItem(new ItemStack(PepsiMcItem.EMPTY_CAN.get())))
                activity = PepsiMcBlockStateProperties.BottlerActivity.CAN;
            else
                activity = PepsiMcBlockStateProperties.BottlerActivity.NONE;
        }
        if (!getBlockState().equals(getBlockState().setValue(PepsiMcBlockStateProperties.BOTTLING, activity).setValue(BlockStateProperties.POWERED, Powered)))
            level.setBlock(worldPosition, getBlockState().setValue(PepsiMcBlockStateProperties.BOTTLING, activity).setValue(BlockStateProperties.POWERED, Powered), Block.UPDATE_ALL);
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
    protected LazyOptional<IItemHandler> getOutHandler() {
        return LazyOptional.of(()->new IItemHandler(){

            @Override
            public int getSlots() {
                return 2;
            }

            @NotNull
            @Override
            public ItemStack getStackInSlot(int slot) {
                return itemHandler.getStackInSlot(slot+3);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return itemHandler.insertItem(slot+3, stack, simulate);
            }

            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return itemHandler.extractItem(slot+3,amount,simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                return itemHandler.getSlotLimit(slot+3);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return itemHandler.isItemValid(slot+3,stack);
            }
        });
    }

    @Override
    public @NotNull Component getDisplayName() {
        return new TranslatableComponent("block.pepsimc.automated_bottler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player p_39956_) {
        return new AutomatedBottlerMenu(id,inv,this,dataAccess);
    }
}
