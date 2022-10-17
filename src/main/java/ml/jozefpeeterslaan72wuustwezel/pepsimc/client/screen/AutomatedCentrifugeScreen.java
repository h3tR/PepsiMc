package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.BatteryDisplay;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.ProgressBar;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedCentrifugeMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


public class AutomatedCentrifugeScreen extends AutomatedProcessingScreen<AutomatedCentrifugeMenu>{

	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/automated_centrifuge_gui.png");
	public AutomatedCentrifugeScreen(AutomatedCentrifugeMenu Menu, Inventory plrInv, Component Text) {
		super(Menu, plrInv, Text);
	}

	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new BatteryDisplay(this.getGuiLeft()+152,this.getGuiTop()+9,this.menu,this));
		this.addRenderableWidget(new ProgressBar(this.getGuiLeft()+59,this.getGuiTop()+41,this.menu,this));
		//this.addRenderableWidget(new Slider(this.getGuiLeft()+10,this.getGuiTop()+40,45,this.menu,this));
	}

	
	@Override
	public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		if (hasRecipe()) {
			if(!this.menu.slotHasItem(1))
				Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeResult(), this.getGuiLeft()+menu.slots.get(1+36).x, this.getGuiTop()+menu.slots.get(1+36).y);
			if(!this.menu.slotHasItem(2))
				Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeByproduct(), this.getGuiLeft()+menu.slots.get(2+36).x, this.getGuiTop()+menu.slots.get(2+36).y);
			for (int i = 0; i < 3; i++) {
				RenderSystem.depthFunc(516+i);
				if(!this.menu.slotHasItem(1))
					GuiComponent.fill(stack, this.getGuiLeft()+menu.slots.get(1+36).x, this.getGuiTop()+menu.slots.get(1+36).y, this.getGuiLeft()+menu.slots.get(1+36).x+16, this.getGuiTop()+menu.slots.get(1+36).y+16,822083583);
				if(!this.menu.slotHasItem(2))
					GuiComponent.fill(stack, this.getGuiLeft()+menu.slots.get(2+36).x, this.getGuiTop()+menu.slots.get(2+36).y, this.getGuiLeft()+menu.slots.get(2+36).x+16, this.getGuiTop()+menu.slots.get(2+36).y+16,822083583);
			}
			RenderSystem.depthFunc(515);
		}
	}



	@Override
	public boolean hasRecipe() {
		Level world = entity.getLevel();
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());

		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<CentrifugeRecipe> recipe = Objects.requireNonNull(world).getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, inv, world);

		return recipe.isPresent();
		
	}

	@Override
	protected ItemStack RecipeResult() {
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
	protected void renderBg(@NotNull PoseStack stack, float Ptick, int X, int Y) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		RenderSystem.setShaderTexture(0, GUI);
        int x = this.getGuiLeft();
        int y = this.getGuiTop();
        this.blit(stack, x, y, 0, 0, 176, 167);
	}

}
	 

