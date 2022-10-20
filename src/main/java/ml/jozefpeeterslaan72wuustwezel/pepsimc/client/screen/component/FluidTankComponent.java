package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.helper.FluidTankRenderer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.FluidHandlerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.ProcessingMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.GuiFluidTankTransferPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FluidTankComponent extends AbstractWidget {
    private static final ResourceLocation RL = new ResourceLocation("pepsimc","textures/gui/component/fluid_tank.png");
    private final FluidHandlerMenu menu;
    FluidTankRenderer renderer;
    private final Screen screen;
    public FluidTankComponent(int x, int y, FluidHandlerMenu menu, Screen screen, int capacity){
        super(x,y, 16,36, TextComponent.EMPTY);
        this.menu = menu;
        this.screen = screen;
        this.renderer = new FluidTankRenderer(capacity, true, 12, 32);


    }

    @Override
    public void render(@NotNull PoseStack stack, int MouseX, int MouseY, float p_94672_){
        super.render(stack,MouseX,MouseY,p_94672_);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0,RL);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        //blits the Background
        blit(stack,x,y,0,0,this.width,this.height,20,36);
        //renders the fluid
        renderer.render(stack,x+2,y+2,menu.getFluidStack());
        //this.setBlitOffset(this.getBlitOffset()+50);
        RenderSystem.setShaderTexture(0,RL);
        //blits the Foreground
        blit(stack,x+2,y,16,0,4,this.height,20,36);
        if(this.isHovered){
            this.renderToolTip(stack,MouseX,MouseY);
        }

    }



    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if(mouseButton<=1) {
            assert Minecraft.getInstance().player != null;
            ItemStack holdItem = Minecraft.getInstance().player.containerMenu.getCarried();
            LazyOptional<IFluidHandlerItem> cap = holdItem.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
            if (cap.isPresent())
            {
                PepsimcNetwork.CHANNEL.send(PacketDistributor.SERVER.noArg(), new GuiFluidTankTransferPacket(((ProcessingMenu)this.menu).entity.getBlockPos(),mouseButton));
                return true;

            }
        }
        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    //Override so it doesn't render the button when super.render() is called
    @Override
    public void renderButton(@NotNull PoseStack p_93676_, int p_93677_, int p_93678_, float p_93679_) {}

    @Override
    public void renderToolTip(@NotNull PoseStack stack, int MouseX, int MouseY) {

        if(Screen.hasShiftDown())
            this.screen.renderTooltip(stack,new TextComponent( new TranslatableComponent(menu.getFluidStack().getFluid().getAttributes().getTranslationKey()).getString()+": "+menu.getFluidStack().getAmount()+" mB / "+ renderer.capacity+ "mB"),MouseX,MouseY);
        else
            this.screen.renderTooltip(stack,new TextComponent(new TranslatableComponent(menu.getFluidStack().getFluid().getAttributes().getTranslationKey()).getString()+": "+menu.getFluidStack().getAmount()+" mB"),MouseX,MouseY);

    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput NEO) {}
}
