package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class AutomatedProcessingMenu extends ProcessingMenu {
    protected final ContainerData data;

    public AutomatedProcessingMenu(int ID, Inventory inv, BlockEntity entity, MenuType<?> container, int Size, ContainerData data) {
        super(ID, inv, entity, container, Size);
        this.data = data;
        this.addDataSlots(data);
    }



    public int getProgress(){ return  data.get(0); }
    public int getGoal(){ return  data.get(1); }

    public int getEnergy() {
        return data.get(2);
    }
    public int getMaxEnergy() {
        return data.get(3);
    }
}
