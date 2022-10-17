package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity;

import net.minecraftforge.fluids.FluidStack;

public interface TankOwner {
    public void setFluidStack(FluidStack fluidStack);

    public FluidStack getFluidStack();
}
