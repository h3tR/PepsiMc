package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedBottlerEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedBottlerMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
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

	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/bottler_gui.png");
	
	@Override
	protected void containerTick() {
		super.containerTick();
		if(hasRecipe()) {
			this.addRenderableWidget(new ConfirmButton(this.getGuiLeft()+95,this.getGuiTop()+30,176,3,23,9,entity.getBlockPos(),GUI));
		} else {
			this.clearWidgets();
		}
	}
	
	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		if (hasRecipe()) {
			if(this.isHovering(95,30, 23, 9, mouseX, mouseY)) {

				if(this.createTooltip()!=null) {

					this.renderTooltip(stack, this.createTooltip(), mouseX,mouseY);
				}
			}			
			if(!this.menu.slotHasItem(3))
			Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeResult(), this.getGuiLeft()+143, this.getGuiTop()+30);
			if(!this.menu.slotHasItem(4))
			Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(new ItemStack(Items.BUCKET), this.getGuiLeft()+143, this.getGuiTop()+51);
			for (int i = 0; i < 3; i++) {
		        RenderSystem.depthFunc(516+i);
				if(!this.menu.slotHasItem(3))
					GuiComponent.fill(stack, this.getGuiLeft()+143, this.getGuiTop()+30, this.getGuiLeft()+159, this.getGuiTop()+46,822083583);
				if(!this.menu.slotHasItem(4))
					GuiComponent.fill(stack, this.getGuiLeft()+143, this.getGuiTop()+51, this.getGuiLeft()+159, this.getGuiTop()+67,822083583);
			}
	        RenderSystem.depthFunc(515);

		}
		
	}
	
	private Component createTooltip() {
        ArrayList<Component> text = new ArrayList<Component>();
        if(RecipeResult() != null) {
        	text.add(RecipeResult().getHoverName());
			return text.get(0);
		}
		return null;
    }
	private boolean hasRecipe() {
        Level world = entity.getLevel();
        SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
        
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
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
		recipe.ifPresent(i->{result.add(i.getResultItem());});
		return result.get(0);
		
	}
	
	@Override
	protected void renderBg(PoseStack stack, float Ptick, int X, int Y) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, GUI);
        int x = this.getGuiLeft();
        int y = this.getGuiTop();
        this.blit(stack, x, y, 0, 0, 176, 167);
		if(this.menu.slotHasItem(0)) {
			this.blit(stack, x+61, y+35, 176, 0, 3, 3);
		}
		if (this.menu.slotHasItem(1)) {
			this.blit(stack, x+69, y+35, 176, 0, 3, 3);
		}
		if (this.menu.slotHasItem(2)) {
			this.blit(stack, x+77, y+35, 176, 0, 3, 3);
		}
	}

	@Override
	protected void renderLabels(PoseStack matrixStack, int p_97809_, int p_97810_) {
		drawString(matrixStack, Minecraft.getInstance().font,   menu.getProgress()+"/"+menu.getGoal(), 50, 10, 0xffffff);
		super.renderLabels(matrixStack, p_97809_, p_97810_);
	}
}
	 

