package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlockStateProperties;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedBottlerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.FluidSynchronizationPacket;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.configuration.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

import static ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags.Items.PEPSI_TYPE_FLUID;

public class AutomatedBottlerEntity extends AutomatedProcessingBlockEntity implements MenuProvider, TankOwner {
    public final FluidTank fluidTank;

    private final LazyOptional<IFluidHandler> fluid;

    public AutomatedBottlerEntity(BlockPos pos, BlockState state) {
        super(PepsiMcBlockEntity.AUTOMATED_BOTTLER_BLOCK_ENTITY.get(), pos, state, CommonConfig.BOTTLER_FE_STORAGE.get(), CommonConfig.BOTTLER_CONDUCTIVITY.get(),CommonConfig.BOTTLER_FE_USAGE_PER_TICK.get());
        fluidTank = new FluidTank(CommonConfig.BOTTLER_FLUID_STORAGE.get()){
            @Override
            protected void onContentsChanged() {
                assert level != null;
                setChanged(level,AutomatedBottlerEntity.this.worldPosition,AutomatedBottlerEntity.this.getBlockState());
                if(!level.isClientSide())
                    PepsimcNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), new FluidSynchronizationPacket(worldPosition,this.fluid));
                super.onContentsChanged();
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid().is(PEPSI_TYPE_FLUID);
            }
        };
        fluid = LazyOptional.of(()->fluidTank);
    }
    @Override
    protected Optional<BottlerRecipe> getRecipe(){
      return Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, getSimpleInv(),this.getLevel());
    }

    @Override
    protected boolean isActive() {
        return super.isActive() &&
                getRecipe().get().getFluid().getFluid() == this.getFluidStack().getFluid()
                && getRecipe().get().getFluid().getAmount() <= this.getFluidStack().getAmount();

    }

    @Override
    protected void finishProduct() {
        Optional<BottlerRecipe> recipe = getRecipe();
        recipe.ifPresent(iRecipe->{
            itemHandler.extractItem(0, 1, false);
            itemHandler.extractItem(1, 1, false);
            fluidTank.drain(recipe.get().getFluid().getAmount(), IFluidHandler.FluidAction.EXECUTE);
            itemHandler.insertItem(2, iRecipe.getResultItem(), false);
            setChanged();
        });
    }
    public void setFluidStack(FluidStack fluidStack){
        this.fluidTank.setFluid(fluidStack);
    }


    public FluidStack getFluidStack(){
        return this.fluidTank.getFluid();
    }

    @Override
    protected int getOutputSlot() {
        return 2;
    }

    @Override
    protected int getByProductSlot() {
        return -1;
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
            Objects.requireNonNull(level).setBlock(worldPosition, getBlockState().setValue(PepsiMcBlockStateProperties.BOTTLING, activity).setValue(BlockStateProperties.POWERED, Powered), Block.UPDATE_ALL);
    }

    @Override
    protected ItemStackHandler createHandler() {

        return new ItemStackHandler(3) {

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
                return switch (slot) {
                    case 0 -> stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_CONTAINER);
                    case 1 -> stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.BOTTLING_LABEL);
                    case 2 -> stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.PEPSI_VARIANT);
                    default -> super.isItemValid(slot,stack);
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
    protected LazyOptional<IItemHandlerModifiable> getOutHandler() {
        return LazyOptional.of(()->new IItemHandlerModifiable(){

            @Override
            public void setStackInSlot(int slot, @NotNull ItemStack stack) {
                itemHandler.setStackInSlot(slot+2,stack);
            }

            @Override
            public int getSlots() {
                return 1;
            }

            @NotNull
            @Override
            public ItemStack getStackInSlot(int slot) {
                return itemHandler.getStackInSlot(slot+2);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return itemHandler.insertItem(slot+2, stack, simulate);
            }

            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return itemHandler.extractItem(slot+2,amount,simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                return itemHandler.getSlotLimit(slot+2);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return itemHandler.isItemValid(slot+2,stack);
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
        PepsimcNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), new FluidSynchronizationPacket(worldPosition,this.getFluidStack()));

        return new AutomatedBottlerMenu(id,inv,this,dataAccess);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt = fluidTank.writeToNBT(nbt);
        super.saveAdditional(nbt);

    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        fluidTank.readFromNBT(nbt);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(side != Direction.DOWN && side != Direction.UP && cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
           return fluid.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        fluid.invalidate();
    }
}
