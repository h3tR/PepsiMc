package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.ProcessingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public abstract class AutomatedProcessingBlockEntity extends BlockEntity {

    public int Progress;
    public int Goal;
    private final int UsagePerTick;
    private Optional<? extends ProcessingRecipe> PreviousRecipe;
    public final CustomEnergyStorage energyStorage;

    public final ItemStackHandler itemHandler;

    private final LazyOptional<IEnergyStorage> energy;
    private final LazyOptional<IItemHandler> itemLazyOptional;

    private final BlockEntityType<?> type;

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int index) {
            return switch (index) {
                case 0 -> AutomatedProcessingBlockEntity.this.Progress;
                case 1 -> AutomatedProcessingBlockEntity.this.Goal;
                case 2 -> AutomatedProcessingBlockEntity.this.energyStorage.getEnergyStored();
                case 3 -> AutomatedProcessingBlockEntity.this.energyStorage.getMaxEnergyStored();
                default -> 0;
            };
        }

        public void set(int index, int value) {
            switch (index) {
                case 0 -> AutomatedProcessingBlockEntity.this.Progress = value;
                case 1 -> AutomatedProcessingBlockEntity.this.Goal = value;
                case 2 -> AutomatedProcessingBlockEntity.this.energyStorage.setEnergy(value);
            }

        }

        public int getCount() {
            return 4;
        }
    };
    private boolean Powered=false;
    public AutomatedProcessingBlockEntity(BlockEntityType<?> in, BlockPos pos, BlockState state, int energyCapacity, int energyMaxTransfer,  int usagePerTick) {
        super(in, pos, state);
        this.UsagePerTick = usagePerTick;
        this.itemHandler = createHandler();
        this.itemLazyOptional = LazyOptional.of(()->itemHandler);

        this.type = in;
        this.energyStorage = new CustomEnergyStorage(energyCapacity, energyMaxTransfer) {
            @Override
            protected void onEnergyChanged() {
                boolean nextPowered = this.hasSufficientPower();
                if (nextPowered != Powered) {
                    Powered = nextPowered;
                    assert level != null;
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(),3);
                }
                setChanged();
            }
        };
        energy = LazyOptional.of(() -> energyStorage);
    }



    protected abstract Optional<? extends ProcessingRecipe> getRecipe();
    protected abstract void finishProduct();

    protected abstract int getOutputSlot();

    protected abstract int getByProductSlot();
    protected abstract ItemStackHandler createHandler();
    protected abstract LazyOptional<IItemHandlerModifiable> getOutHandler();



    protected boolean isSlotFull(int slotIndex){
        return itemHandler.getStackInSlot(slotIndex).getCount() < itemHandler.getSlotLimit(slotIndex);
    }

    protected boolean isActive(){
        return energyStorage.getEnergyStored()>0 && getRecipe().isPresent()
                && (isSlotEmpty(getOutputSlot()) || (isSlotFull(getOutputSlot()))
                && getRecipe().get().getResultItem().sameItem(itemHandler.getStackInSlot(getOutputSlot()))
                &&  (getByProductSlot() < 0 ||isSlotEmpty(getByProductSlot()) || (isSlotFull(getByProductSlot()))
                && Objects.requireNonNull(getRecipe().get().getByproductItem()).sameItem(itemHandler.getStackInSlot(getByProductSlot()))));
    }
    protected boolean isSlotEmpty(int slotIndex){
        return itemHandler.getStackInSlot(slotIndex).getCount()<1;
    }

    public void tickServer() {
        if (getRecipe().isPresent() && (PreviousRecipe==null || PreviousRecipe.isEmpty() || getRecipe().get() != PreviousRecipe.get())) {
            Goal = getRecipe().get().ticks;
        }
        PreviousRecipe = getRecipe();
        if(isActive()) {
            Progress++;
            if(Progress>=Goal){
                Progress = 0;
               finishProduct();
            }
            energyStorage.consumeEnergy(this.UsagePerTick);
        }else{
            Progress = 0;
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energy.invalidate();
        itemLazyOptional.invalidate();
    }

    // getUpdatePacket() and onDataPacket() are for synchronizing on block updates
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        assert tag != null;
        Powered = tag.getBoolean("Powered");
        Goal = tag.getInt("Goal");
        Progress = tag.getInt("Progress");
      //  updateDataAccess();
    }


    // getUpdateTag() and handleUpdateTag() are for synchronizing the client side BE on chunk load
    // getUpdateTag() is called server side and collects data for the client
    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putBoolean("Powered", energyStorage.hasSufficientPower());
        tag.putInt("Progress", Progress);
        tag.putInt("Goal", Goal);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        Powered = tag.getBoolean("Powered");
        Goal = tag.getInt("Goal");
        Progress = tag.getInt("Progress");
        //updateDataAccess();
    }
    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }
        if (tag.contains("goal")) {
            Goal = tag.getInt("goal");
        }
        if (tag.contains("progress")) {
            Progress = tag.getInt("progress");
        }
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        nbt.put("energy",energyStorage.serializeNBT());
        nbt.putInt("progress",Progress);
        nbt.putInt("goal",Goal);
        super.saveAdditional(nbt);
    }

    protected SimpleContainer getSimpleInv() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());

        for(int i=0;i<itemHandler.getSlots();i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        return inv;
    }



    public NonNullList<ItemStack> getNNLInv(){
        NonNullList<ItemStack> toReturn = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);
        for(int i=0;i<itemHandler.getSlots();i++) {
            toReturn.set(i, itemHandler.getStackInSlot(i));
        }
        return toReturn;
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return type;
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(side == Direction.DOWN){
                return getOutHandler().cast();}
            else
                return itemLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }
    public static class CustomEnergyStorage extends EnergyStorage {
        int maxTransfer;
        public CustomEnergyStorage(int capacity, int maxTransfer) {
            super(capacity, maxTransfer);
            this.maxTransfer = maxTransfer;
        }
        public boolean hasSufficientPower() {
            return this.getEnergyStored() >= this.maxTransfer;
        }
        protected void onEnergyChanged() {

        }

        public void setEnergy(int energy) {
            this.energy = energy;
            onEnergyChanged();
        }

        public void addEnergy(int energy) {
            this.energy += energy;
            if (this.energy > getMaxEnergyStored()) {
                this.energy = getEnergyStored();
            }
            onEnergyChanged();
        }

        public void consumeEnergy(int energy) {
            this.energy -= energy;
            if (this.energy < 0) {
                this.energy = 0;
            }
            onEnergyChanged();

        }
    }
}