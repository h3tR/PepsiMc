package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.ProcessingCraftPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class ConfirmButton extends AbstractButton{
	      private boolean selected;
	      BlockPos Pos;
	      int IconX;
	      int IconY;
	      int SizeX;
	      int SizeY;
	      ResourceLocation GUI;
	      protected ConfirmButton(int X, int Y, int IconX, int IconY, int SizeX, int SizeY, BlockPos Pos, ResourceLocation GUI) {
	         super(X, Y, SizeX, SizeY, TextComponent.EMPTY);
	         this.IconX = IconX;
	         this.IconY= IconY; 
	         this.Pos = Pos;
	         this.SizeX = SizeX;
	         this.SizeY = SizeY;
	         this.GUI = GUI;
	      }

		public void renderButton(PoseStack stack, int X, int Y, float Ptick) {
	         Minecraft.getInstance().getTextureManager().bindForSetup(this.GUI);
	         RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
	         int i = 219;
	         int j = 0;
	         if (!this.active) {
	            j += this.width * 2;
	         } else if (this.selected) {
	            j += this.width * 1;
	         } else if (this.isHoveredOrFocused()) {
	            j += this.width * 3;
	         }
	         this.blit(stack, this.x, this.y, j, i, this.width, this.height);
	         this.renderIcon(stack);
	      }

		protected void renderIcon(PoseStack stack) {
    	  		this.blit(stack, this.x + 2, this.y + 2, this.IconX, this.IconY, this.SizeX, this.SizeY);
	      }

		public boolean isSelected() {
	        return this.selected;
	    }

	    public void setSelected(boolean selected) {
	        this.selected = selected;
	    }
	      
	    public void onPress() {
	    	  PepsimcNetwork.CHANNEL.sendToServer(new ProcessingCraftPacket(this.Pos,Screen.hasShiftDown()));
	    }

		@Override
		public void updateNarration(NarrationElementOutput p_169152_) {}

}
