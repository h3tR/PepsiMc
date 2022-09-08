package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.AutomatedProcessingScreen;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedProcessingMenu;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class Slider  extends AbstractWidget {

    private static final ResourceLocation RL = new ResourceLocation("pepsimc","textures/gui/component/slider.png");
    private final AutomatedProcessingMenu menu;
    private final AutomatedProcessingScreen screen;
    private int sliderx;
    public int value;

    // Width must be greater than 8
    public Slider(int x, int y, int width, AutomatedProcessingMenu menu, AutomatedProcessingScreen screen) {
        super(x, y, width, 6, TextComponent.EMPTY);
        this.menu = menu;
        this.screen = screen;
        this.sliderx = (width-8)/2;
        this.value = sliderx;
    }

    @Override
    public void render(@NotNull PoseStack stack, int MouseX, int MouseY, float p_94672_){
        super.render(stack,MouseX,MouseY,p_94672_);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,RL);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        //Background
        for (int i = 0; i < width; i++) {
            blit(stack, x+i, y+2, 9, 0, 1, 2,10,6);
        }

        //Slider
        blit(stack,x+sliderx,y,0,0,9,6,10,6);

        if(this.isHovered){
            this.renderToolTip(stack,MouseX,MouseY);
        }
    }

    @Override
    public boolean mouseDragged(double MouseX, double MouseY, int b, double DragX, double DragY) {
        LogManager.getLogger().debug("MouseX: "+MouseX+", DragX: "+DragX+", DragY: "+DragY);
        int iDX = (int)DragX;int iMX = (int)MouseX; int iMY = (int)MouseY;
        if(iMX>=x&&iMX<x+width&&iMY>=y&&iMY<y+6) {
            if(iDX<x+4)
                sliderx = 4;
            else if(iDX>x+width-4)
                sliderx = width-4;
            else
                sliderx = x-iDX;
        }
        value = sliderx;
        return true;

    }

    //Override so it doesn't render the button when super.render() is called
    @Override
    public void renderButton(@NotNull PoseStack p_93676_, int p_93677_, int p_93678_, float p_93679_) {}

    @Override
    public void updateNarration(@NotNull NarrationElementOutput NEO) {}
}
