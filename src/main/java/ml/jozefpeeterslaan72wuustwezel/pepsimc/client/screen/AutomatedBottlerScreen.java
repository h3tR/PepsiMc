package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.BatteryDisplay;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedBottlerEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedBottlerMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@OnlyIn(Dist.CLIENT)

public class AutomatedBottlerScreen extends AbstractContainerScreen<AutomatedBottlerMenu>{
	private final AutomatedBottlerEntity entity;
	private final Level world;
	public AutomatedBottlerScreen(AutomatedBottlerMenu Menu, Inventory plrInv, Component Text) {
		super(Menu, plrInv, Text);
		this.entity = (AutomatedBottlerEntity) Menu.entity;
		this.world = this.entity.getLevel();

	}

	@Override
	protected void containerTick() {
		super.containerTick();

}

	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new BatteryDisplay(this.getGuiLeft()+152,this.getGuiTop()+9,this.menu,this));
	}

	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/automated_bottler_gui.png");

	
	@Override
	public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		//Render crafting related stuff
		if (hasRecipe()) {
			if(this.isHovering(63,33, 57, 9, mouseX, mouseY))
				this.renderTooltip(stack, new TextComponent(((int)((float)100 / menu.getGoal() * menu.getProgress()))+" %"), mouseX,mouseY);

			if(!this.menu.slotHasItem(3))
				Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeResult(), this.getGuiLeft()+menu.slots.get(3+36).x, this.getGuiTop()+menu.slots.get(3+36).y);
			if(!this.menu.slotHasItem(4))
				Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(new ItemStack(Items.BUCKET), this.getGuiLeft()+menu.slots.get(4+36).x, this.getGuiTop()+menu.slots.get(4+36).y);
			for (int i = 0; i < 3; i++) {
		        RenderSystem.depthFunc(516+i);
				if(!this.menu.slotHasItem(3))
					GuiComponent.fill(stack, this.getGuiLeft()+menu.slots.get(3+36).x, this.getGuiTop()+menu.slots.get(3+36).y, this.getGuiLeft()+menu.slots.get(3+36).x+16, this.getGuiTop()+menu.slots.get(3+36).y+16,822083583);
				if(!this.menu.slotHasItem(4))
					GuiComponent.fill(stack, this.getGuiLeft()+menu.slots.get(4+36).x, this.getGuiTop()+menu.slots.get(4+36).y, this.getGuiLeft()+menu.slots.get(4+36).x+16, this.getGuiTop()+menu.slots.get(4+36).y+16,822083583);
			}
	        RenderSystem.depthFunc(515);
		}
		//render progress bar

	}
	private boolean hasRecipe() {
        Level world = entity.getLevel();
        SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
        
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		assert world != null;
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, inv, world);

		return recipe.isPresent();
		
	}
	private ItemStack RecipeResult() {
        SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
        ArrayList<ItemStack> result = new ArrayList<>();
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, inv, world);
		recipe.ifPresent(i-> result.add(i.getResultItem()));
		return result.get(0);
		
	}
	
	@Override
	protected void renderBg(@NotNull PoseStack stack, float Ptick, int X, int Y) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, GUI);
        int x = this.getGuiLeft();
        int y = this.getGuiTop();
        this.blit(stack, x, y, 0, 0, 176, 167);
		if(this.menu.slotHasItem(0)) {
			this.blit(stack, x+12, y+56, 176, 0, 3, 3);
		}
		if (this.menu.slotHasItem(1)) {
			this.blit(stack, x+20, y+56, 176, 0, 3, 3);
		}
		if (this.menu.slotHasItem(2)) {
			this.blit(stack, x+28, y+56, 176, 0, 3, 3);
		}
		if(menu.getGoal()>0) {
			float ProgressPixels = (float)47 / menu.getGoal() * menu.getProgress();
			float TotalPixels = (float)47*3 / menu.getGoal() * menu.getProgress();
			//brackets
			if(hasRecipe()) {
				this.blit(stack, x + 71, y + 35, 176, 3, 2, 5);
				this.blit(stack, x + 120, y + 35, 177, 3, 2, 5);
			}
			//Bar
			if(TotalPixels%3>0)
				this.blit(stack,x+73,y+36,177,4,(int)ProgressPixels+1,1);
			else
				this.blit(stack,x+73,y+36,177,4,(int)ProgressPixels,1);

			if(TotalPixels%3>1)
				this.blit(stack,x+73,y+37,177,4,(int)ProgressPixels+1,1);
			else
				this.blit(stack,x+73,y+37,177,4,(int)ProgressPixels,1);

			this.blit(stack,x+73,y+38,177,4,(int)ProgressPixels,1);

			/* for fluid progress bar

			Fluid fluid = new FluidStack(PepsiMcFluid.PEPSI_FLUID.get().getSource(), 1).getFluid();
			int color = fluid.getAttributes().getColor();
			TextureAtlasSprite fluidSprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluid.getAttributes().getStillTexture());
			RenderSystem.setShaderColor((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, (color >> 24 & 0xFF) / 255.0F);
			drawTiledSprite(stack, 109, 71 - ProgressPixels, ProgressPixels, 10, ProgressPixels, fluidSprite, 16, 16, 0);*/

		}
	}

	@Override
	protected void renderLabels(@NotNull PoseStack matrixStack, int p_97809_, int p_97810_) {
		super.renderLabels(matrixStack, p_97809_, p_97810_);
	}

	//Code blatantly plagiarised from https://github.com/mekanism/Mekanism/blob/a395dafc0ebe46c0e2cc0cd2c98edbbcf9f5a61a/src/main/java/mekanism/client/gui/GuiUtils.java
	public static void drawTiledSprite(@NotNull PoseStack matrix, int xPosition, int yPosition, int yOffset, int desiredWidth, int desiredHeight, TextureAtlasSprite sprite,
									   int textureWidth, int textureHeight, int zLevel) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
		int xTileCount = desiredWidth / textureWidth;
		int xRemainder = desiredWidth - (xTileCount * textureWidth);
		int yTileCount = desiredHeight / textureHeight;
		int yRemainder = desiredHeight - (yTileCount * textureHeight);
		int yStart = yPosition + yOffset;
		float uMin = sprite.getU0();
		float uMax = sprite.getU1();
		float vMin = sprite.getV0();
		float vMax = sprite.getV1();
		float uDif = uMax - uMin;
		float vDif = vMax - vMin;
		RenderSystem.enableBlend();
		BufferBuilder vertexBuffer = Tesselator.getInstance().getBuilder();
		vertexBuffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		Matrix4f matrix4f = matrix.last().pose();
		for (int xTile = 0; xTile <= xTileCount; xTile++) {
			int width = (xTile == xTileCount) ? xRemainder : textureWidth;
			if (width == 0) {
				break;
			}
			int x = xPosition + (xTile * textureWidth);
			int maskRight = textureWidth - width;
			int shiftedX = x + textureWidth - maskRight;
			float uLocalDif = uDif * maskRight / textureWidth;
			float uLocalMin;
			float uLocalMax;
			uLocalMin = uMin;
			uLocalMax = uMax - uLocalDif;
			for (int yTile = 0; yTile <= yTileCount; yTile++) {
				int height = (yTile == yTileCount) ? yRemainder : textureHeight;
				if (height == 0) {
					//Note: We don't want to fully break out because our height will be zero if we are looking to
					// draw the remainder, but there is no remainder as it divided evenly
					break;
				}
				int y = yStart - ((yTile + 1) * textureHeight);
				int maskTop = textureHeight - height;
				float vLocalDif = vDif * maskTop / textureHeight;
				float vLocalMin;
				float vLocalMax;
				vLocalMin = vMin;
				vLocalMax = vMax - vLocalDif;

				vertexBuffer.vertex(matrix4f, x, y + textureHeight, zLevel).uv(uLocalMin, vLocalMax).endVertex();
				vertexBuffer.vertex(matrix4f, shiftedX, y + textureHeight, zLevel).uv(uLocalMax, vLocalMax).endVertex();
				vertexBuffer.vertex(matrix4f, shiftedX, y + maskTop, zLevel).uv(uLocalMax, vLocalMin).endVertex();
				vertexBuffer.vertex(matrix4f, x, y + maskTop, zLevel).uv(uLocalMin, vLocalMin).endVertex();
			}
		}
		vertexBuffer.end();
		BufferUploader.end(vertexBuffer);
		RenderSystem.disableBlend();
	}

}
	 

