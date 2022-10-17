package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedProcessingMenu;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import static ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.util.FEValueHelper.getFEValue;


@OnlyIn(Dist.CLIENT)
public class BatteryDisplay extends AbstractWidget {
    private static final ResourceLocation RL = new ResourceLocation("pepsimc","textures/gui/component/battery_display.png");
    private final AutomatedProcessingMenu menu;
    private final Screen screen;
    public BatteryDisplay(int x, int y, AutomatedProcessingMenu menu, Screen screen){
        super(x,y, 13,20, TextComponent.EMPTY);
        this.menu = menu;
        this.screen = screen;
    }

    @Override
    public void render(@NotNull PoseStack stack, int MouseX, int MouseY, float p_94672_){
        super.render(stack,MouseX,MouseY,p_94672_);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,RL);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        //blits the battery Background
        blit(stack,x,y,0,0,this.width,this.height,49,20);

        int energy = menu.getEnergy();
        int maxEnergy = menu.getMaxEnergy();
        int chargeLevel = (int) Math.ceil((float)energy/maxEnergy*16);
        int chargeColor = (int)Math.ceil((float)chargeLevel/4)-1;
        if(chargeColor<0)
            chargeColor = 0;
        //blits the charge
        blit(stack,x+2,y+18-chargeLevel,this.width+chargeColor*9,16-chargeLevel,9,chargeLevel,49,20);
        if(this.isHovered){
            this.renderToolTip(stack,MouseX,MouseY);
        }

    }

    //Override so it doesn't play a sound when clicked for no reason
    @Override
    public boolean mouseClicked(double p_93641_, double p_93642_, int p_93643_) {return false;}

    //Override so it doesn't render the button when super.render() is called
    @Override
    public void renderButton(@NotNull PoseStack p_93676_, int p_93677_, int p_93678_, float p_93679_) {}

    @Override
    public void renderToolTip(@NotNull PoseStack stack, int MouseX, int MouseY) {
        if(Screen.hasShiftDown())
            this.screen.renderTooltip(stack,new TextComponent(getFEValue(menu.getEnergy())+" / "+getFEValue(menu.getMaxEnergy())),MouseX,MouseY);
        else
            this.screen.renderTooltip(stack,new TextComponent(getFEValue(menu.getEnergy())),MouseX,MouseY);

    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput NEO) {}
}
