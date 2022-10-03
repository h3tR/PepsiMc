package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.BatteryDisplay;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.ProgressBar;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedBottlerMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;


@OnlyIn(Dist.CLIENT)

public class AutomatedBottlerScreen extends AutomatedProcessingScreen<AutomatedBottlerMenu>{
	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/automated_bottler_gui.png");


	public AutomatedBottlerScreen(AutomatedBottlerMenu Menu, Inventory plrInv, Component Text) {
		super(Menu, plrInv, Text);


	}

	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new BatteryDisplay(this.getGuiLeft()+152,this.getGuiTop()+9,this.menu,this));
		this.addRenderableWidget(new ProgressBar(this.getGuiLeft()+68,this.getGuiTop()+33,this.menu,this));
	}


	
	@Override
	public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		//Render crafting related stuff
		if (hasRecipe()) {
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
	@Override
	public boolean hasRecipe() {
        Level world = entity.getLevel();
        SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
        
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		assert world != null;
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(BottlerRecipe.BottlerRecipeType.INSTANCE, inv, world);

		return recipe.isPresent();
		
	}
	@Override
	protected ItemStack RecipeResult() {
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
		}
	}

}
	 

