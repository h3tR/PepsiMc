package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.AutomatedProcessingScreen;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedProcessingMenu;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class ProgressBar extends AbstractWidget {

    private static final ResourceLocation RL = new ResourceLocation("pepsimc","textures/gui/component/progress_bar.png");
    private final AutomatedProcessingMenu menu;
    private final AutomatedProcessingScreen screen;

    public ProgressBar(int x, int y, AutomatedProcessingMenu menu, AutomatedProcessingScreen screen){
        super(x,y, 57,9, TextComponent.EMPTY);
        this.menu = menu;
        this.screen = screen;
    }

    @Override
    public void render(@NotNull PoseStack stack, int MouseX, int MouseY, float p_94672_){
        super.render(stack,MouseX,MouseY,p_94672_);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,RL);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        //Background
        blit(stack, x, y, 0, 0, 57, 9,106,9);

        if(menu.getGoal()>0) {
            float ProgressPixels = (float)47 / menu.getGoal() * menu.getProgress();
            float TotalPixels = (float)47*3 / menu.getGoal() * menu.getProgress();
            //brackets
            if(screen.hasRecipe()) {
                blit(stack, x + 3, y + 2, 57, 0, 2, 5, 106,9);
                blit(stack, x + 52, y + 2, 58, 0, 2, 5,106,9);
            }
            //Bar
            if(TotalPixels%3>0)
                blit(stack,x+5,y+3,58,1,(int)ProgressPixels+1,1,106,9);
            else
                blit(stack,x+5,y+3,58,1,(int)ProgressPixels,1,106,9);

            if(TotalPixels%3>1)
                blit(stack,x+5,y+4,58,1,(int)ProgressPixels+1,1,106,9);
            else
                blit(stack,x+5,y+4,58,1,(int)ProgressPixels,1,106,9);

            blit(stack,x+5,y+5,58,1,(int)ProgressPixels,1,106,9);
        }

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
        this.screen.renderTooltip(stack, new TextComponent(((int)((float)100 / menu.getGoal() * menu.getProgress()))+" %"), MouseX, MouseY);

    }
    @Override
    public void updateNarration(@NotNull NarrationElementOutput NEO) {}
}
