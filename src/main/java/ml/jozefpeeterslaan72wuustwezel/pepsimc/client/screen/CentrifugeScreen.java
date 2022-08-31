package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import java.util.ArrayList;
import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.CentrifugeMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.CentrifugeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;


public class CentrifugeScreen extends AbstractContainerScreen<CentrifugeMenu>{

	private final CentrifugeEntity entity;
	private final Level world;
	public CentrifugeScreen(CentrifugeMenu Menu, Inventory plrInv, Component Text) {
		super(Menu, plrInv, Text);
		this.entity = (CentrifugeEntity) Menu.entity;
		this.world = this.entity.getLevel();
	}

	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/centrifuge_gui.png");

	public void containerTick() {
	      super.containerTick();
	      if (hasRecipe()) {
				this.addRenderableWidget(new ConfirmButton(this.getGuiLeft()+77,this.getGuiTop()+28,176,0,18,15,entity.getBlockPos(),GUI));
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
			if(this.isHovering(78,29, 18, 15, mouseX, mouseY)) {

				if(this.createTooltip()!=null) {

					this.renderTooltip(stack, this.createTooltip(), mouseX,mouseY);
				}
			}			
			if(!this.menu.slotHasItem(1))
			Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeResult(), this.getGuiLeft()+65, this.getGuiTop()+53);
			for (int i = 0; i < 3; i++) {
		        RenderSystem.depthFunc(516+i);
				if(!this.menu.slotHasItem(2))
					GuiComponent.fill(stack, this.getGuiLeft()+66, this.getGuiTop()+53, this.getGuiLeft()+81, this.getGuiTop()+69,822083583);
				
			}
	        RenderSystem.depthFunc(515);
			if(!this.menu.slotHasItem(2))
				Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeByproduct(), this.getGuiLeft()+94, this.getGuiTop()+53);
				for (int i = 0; i < 3; i++) {
			        RenderSystem.depthFunc(516+i);
					if(!this.menu.slotHasItem(2))
						GuiComponent.fill(stack, this.getGuiLeft()+94, this.getGuiTop()+53, this.getGuiLeft()+110, this.getGuiTop()+69,822083583);
					
				}
	        RenderSystem.depthFunc(515);
		}
	}
	
	private Component createTooltip() {
        ArrayList<Component> text = new ArrayList<>();
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
		
		Optional<CentrifugeRecipe> recipe = world.getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, inv, world);

		return recipe.isPresent();
		
	}
	private ItemStack RecipeResult() {
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
		ArrayList<ItemStack> result = new ArrayList<>();
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<CentrifugeRecipe> recipe = world.getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, inv, world);
		recipe.ifPresent(i-> result.add(i.getResultItem()));
		return result.get(0);
		
	}
	private ItemStack RecipeByproduct() {

        SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
        ArrayList<ItemStack> result = new ArrayList<>();
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<CentrifugeRecipe> recipe = world.getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, inv, world);
		recipe.ifPresent(i-> result.add(i.getByproductItem()));
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
	}
	

}
	 

