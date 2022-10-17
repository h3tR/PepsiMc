package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.TankOwner;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidSynchronizationPacket {
    private final BlockPos entityPos;
    private final FluidStack fluidStack;
    public FluidSynchronizationPacket(BlockPos entityPos, FluidStack fluidStack) {
        this.entityPos = entityPos;
        this.fluidStack = fluidStack;
    }

    public static FluidSynchronizationPacket decode(FriendlyByteBuf buf) {
        return new FluidSynchronizationPacket(buf.readBlockPos(),buf.readFluidStack());
    }

    public static void encode(FluidSynchronizationPacket packet, FriendlyByteBuf buf) {
        buf.writeBlockPos(packet.entityPos);
        buf.writeFluidStack(packet.fluidStack);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            assert Minecraft.getInstance().level != null;
            if(Minecraft.getInstance().level.getBlockEntity(entityPos) instanceof TankOwner blockEntity) {
                blockEntity.setFluidStack(this.fluidStack);
            }
        });
        ctx.setPacketHandled(true);
    }
}
