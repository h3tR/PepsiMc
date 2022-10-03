package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.BatteryDisplay;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.ProgressBar;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.AutomatedRecyclerEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedRecyclerMenu;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;


public class AutomatedRecyclerScreen extends AutomatedProcessingScreen<AutomatedRecyclerMenu>{

	private final AutomatedRecyclerEntity entity;
	private int shuttery = 0;
	private final Level world;
	public AutomatedRecyclerScreen(AutomatedRecyclerMenu Menu, Inventory plrInv, Component Text) {
		super(Menu, plrInv, Text);
		this.entity = (AutomatedRecyclerEntity) Menu.entity;
		this.world = this.entity.getLevel();
	}
	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/automated_recycler_gui.png");

	@Override
	public void containerTick() {
	      super.containerTick();
		  if(menu.slotHasItem(2)&&shuttery<16)
			  shuttery++;
		  else if (!menu.slotHasItem(2)&&shuttery>0)
			  shuttery--;
	}

	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new BatteryDisplay(this.getGuiLeft()+152,this.getGuiTop()+9,this.menu,this));
		this.addRenderableWidget(new ProgressBar(this.getGuiLeft()+60,this.getGuiTop()+38,this.menu,this));
	}
	
	@Override
	public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		RenderSystem.setShaderTexture(0,GUI);
		int oldOffset = this.getBlitOffset();
		this.setBlitOffset(290);
		this.blit(stack,this.getGuiLeft()+menu.slots.get(2+36).x,this.getGuiTop()+menu.slots.get(2+36).y,176,shuttery,16,16);
		this.setBlitOffset(oldOffset);
	}
	

	@Override
	public boolean hasRecipe() {
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		Optional<RecyclerRecipe> recipe = world.getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, inv, world);

		return recipe.isPresent();
		
	}
	@Override
	public ItemStack RecipeResult() {
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
		ArrayList<ItemStack> result = new ArrayList<>();
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<RecyclerRecipe> recipe = world.getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, inv, world);
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
	}

	@Override
	protected void renderLabels(@NotNull PoseStack matrixStack, int p_97809_, int p_97810_) {
		super.renderLabels(matrixStack, p_97809_, p_97810_);
	}

}
	 

