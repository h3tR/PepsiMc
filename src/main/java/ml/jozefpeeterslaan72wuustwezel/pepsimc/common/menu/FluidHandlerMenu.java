package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface FluidHandlerMenu {
    void transferItemFluidToFluidTank(Player plr, ItemStack handlerItem);
    void transferTankFluidToFluidItem(Player plr, ItemStack itemStack);

    public void setFluidStack(FluidStack fluidStack);

    public FluidStack getFluidStack();

}
