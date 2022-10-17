package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet;

import com.mojang.logging.LogUtils;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.FluidHandlerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GuiFluidTankTransferPacket {
    private final BlockPos entityPos;
    private final int mouseButton;
    public GuiFluidTankTransferPacket(BlockPos entityPos, int mouseButton) {
        this.entityPos = entityPos;
        this.mouseButton = mouseButton;
    }

    public static GuiFluidTankTransferPacket decode(FriendlyByteBuf buf) {
        return new GuiFluidTankTransferPacket(buf.readBlockPos(),buf.readInt());
    }

    public static void encode(GuiFluidTankTransferPacket packet, FriendlyByteBuf buf) {
        buf.writeBlockPos(packet.entityPos);
        buf.writeInt(packet.mouseButton);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        Player player = ctx.getSender();
        ctx.enqueueWork(() -> {
            FluidHandlerMenu menu = (FluidHandlerMenu)player.containerMenu;
            if(mouseButton == 0) {
                menu.transferItemFluidToFluidTank(player, player.containerMenu.getCarried());
                player.playSound(new SoundEvent(new ResourceLocation("item.bucket.empty")), 1, 1);
            }
            else {
                menu.transferTankFluidToFluidItem(player, player.containerMenu.getCarried());
                player.playSound(new SoundEvent(new ResourceLocation("item.bucket.fill")), 1, 1);
            }

        });
        ctx.setPacketHandled(true);
    }
}
