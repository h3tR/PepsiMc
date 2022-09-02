package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedProcessingMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


@OnlyIn(Dist.CLIENT)
public class BatteryDisplay extends AbstractWidget {
    private static final ResourceLocation RL = new ResourceLocation("pepsimc","textures/gui/battery_display.png");
    private final AutomatedProcessingMenu menu;

    public BatteryDisplay(int x, int y, AutomatedProcessingMenu menu){
        super(x,y, 13,20, CommonComponents.GUI_DONE);
        this.menu = menu;
    }

    @Override
    public void render(@NotNull PoseStack stack, int MouseX, int MouseY, float p_94672_){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,RL);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);

        //blits the battery Background
        this.blit(stack,x,y,0,0,this.width,this.height);

        int energy = menu.getEnergy();
        int maxEnergy = menu.getMaxEnergy();
        int chargeLevel = (int) Math.ceil((float)energy/maxEnergy*16);

        //blits the charge
        this.blit(stack,x+2,y+18-chargeLevel,this.width+(chargeLevel/4-1)*9,16-chargeLevel,9,chargeLevel);


    }

    @Override
    public void updateNarration(NarrationElementOutput NEO) {}
}
