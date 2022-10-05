package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.AutomatedProcessingBlockEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedProcessingMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AutomatedProcessingScreen<T extends AutomatedProcessingMenu> extends AbstractContainerScreen<T> {
    protected final Level world;
    protected final AutomatedProcessingBlockEntity entity;
    public AutomatedProcessingScreen(T Menu, Inventory PlrInv, Component Text) {
        super(Menu, PlrInv, Text);
        this.entity = (AutomatedProcessingBlockEntity) Menu.entity;
        this.world = this.entity.getLevel();
    }

    public abstract boolean hasRecipe();

    protected abstract ItemStack RecipeResult();
}
