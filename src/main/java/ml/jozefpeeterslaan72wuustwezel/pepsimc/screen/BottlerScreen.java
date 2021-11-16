package ml.jozefpeeterslaan72wuustwezel.pepsimc.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

//import ml.jozefpeeterslaan72wuustwezel.pepsimc.tileentity.BottlerTile;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.container.BottlerContainer;
//import ml.jozefpeeterslaan72wuustwezel.pepsimc.container.PepsiMcContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BottlerScreen extends ContainerScreen<BottlerContainer>{

	public BottlerScreen(BottlerContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
		super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
		// TODO Auto-generated constructor stub
	}


	private final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/bottler_gui.png");
	
	@Override
	public void render(MatrixStack stack, int X, int Y, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, X, Y, Ptick);
		this.renderTooltip(stack, X, Y);
	}
	

	@Override
	protected void renderBg(MatrixStack stack, float Ptick, int X, int Y) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.getTextureManager().bind(GUI);
		int i = this.getGuiLeft();
		int j = this.getGuiTop();
		this.blit(stack, i, j, 0, 0, this.getXSize(), this.getYSize());
		/*if(PepsiMcContainer.BOTTLER_CONTAINER.get(). .slotHasItem(0)) {
			this.blit(stack, i+61, j+35, 173, 0, 3, 3);
		}
		if (BottlerContainer.slotHasItem(1)) {
			this.blit(stack, i+64, j+35, 173, 0, 3, 3);
		}
		if (BottlerContainer.slotHasItem(2)) {
			this.blit(stack, i+67, j+35, 173, 0, 3, 3);
		}*/
	}
	
}
