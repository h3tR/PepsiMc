package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import com.mojang.math.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ProcessingMachine extends BlockEntity {

    public final CustomEnergyStorage energyStorage = new CustomEnergyStorage(5, 1) {
        @Override
        protected void onEnergyChanged() {
            boolean nextPowered = this.hasSufficientPower();
            if (nextPowered != Powered) {
                Powered = nextPowered;
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(),3);
            }
            setChanged();
        }
    };

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public final ItemStackHandler itemHandler;
    private LazyOptional<IItemHandler> handler;
    private BlockEntityType<?> type;
    private boolean Powered=false;
    public ProcessingMachine(BlockEntityType<?> in, BlockPos pos, BlockState state) {
        super(in, pos, state);
        this.itemHandler = createHandler();
        this.handler = LazyOptional.of(()->itemHandler);
        this.type = in;
    }

    protected ItemStackHandler createHandler() {return null;}

    public void tickServer(BlockState state) {}

    @Override
    public void setRemoved() {
        super.setRemoved();
        energy.invalidate();
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
        Powered = tag.getBoolean("Powered");
    }


    // getUpdateTag() and handleUpdateTag() are for synchronizing the client side BE on chunk load
    // getUpdateTag() is called server side and collects data for the client
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putBoolean("Powered", energyStorage.hasSufficientPower());
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        Powered = tag.getBoolean("Powered");
    }
    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        nbt.put("energy",energyStorage.serializeNBT());
        super.saveAdditional(nbt);
    }

    protected SimpleContainer getSimpleInv() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());

        for(int i=0;i<itemHandler.getSlots();i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        return inv;
    }


    public boolean slotHasItem(int index) {
        return itemHandler.getStackInSlot(index).getCount() > 0;
    }

    public NonNullList<ItemStack> getNNLInv(){
        NonNullList<ItemStack> toReturn = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);
        for(int i=0;i<itemHandler.getSlots();i++) {
            toReturn.set(i, itemHandler.getStackInSlot(i));
        }
        return toReturn;
    }

    @Override
    public BlockEntityType<?> getType() {
        return type;
    }

    public class CustomEnergyStorage extends EnergyStorage {
        int maxTransfer;
        public CustomEnergyStorage(int capacity, int maxTransfer) {
            super(capacity, maxTransfer);
            this.maxTransfer = maxTransfer;
        }
        public boolean hasSufficientPower() {
            return energyStorage.getEnergyStored() >= energyStorage.maxTransfer;
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