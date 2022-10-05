package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.BatteryDisplay;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen.component.ProgressBar;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.FlavoringRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.blockentity.AutomatedFlavorMachineEntity;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedFlavorMachineMenu;
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
import java.util.Optional;


public class AutomatedFlavorMachineScreen extends AutomatedProcessingScreen<AutomatedFlavorMachineMenu>{

	private final AutomatedFlavorMachineEntity entity;
	private int mixerstate = 0;
	private final Level world;
	public AutomatedFlavorMachineScreen(AutomatedFlavorMachineMenu Menu, Inventory plrInv, Component Text) {
		super(Menu, plrInv, Text);
		this.entity = (AutomatedFlavorMachineEntity) Menu.entity;
		this.world = this.entity.getLevel();
	}

	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/automated_flavor_machine_gui.png");

	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new BatteryDisplay(this.getGuiLeft()+152,this.getGuiTop()+9,this.menu,this));
		this.addRenderableWidget(new ProgressBar(this.getGuiLeft()+78,this.getGuiTop()+46,this.menu,this));
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		if(hasRecipe()&&menu.getEnergy()>0&&entity.itemHandler.getStackInSlot(2).getCount()<64)
		{
			mixerstate++;
			if(mixerstate>2)
				mixerstate = 0;
		}
	}

	@Override
	public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		if (hasRecipe()) {
			if(!this.menu.slotHasItem(2))

				Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeResult(), this.getGuiLeft()+menu.slots.get(2+36).x, this.getGuiTop()+menu.slots.get(2+36).y);
			for (int i = 0; i < 3; i++) {
		        RenderSystem.depthFunc(516+i);
				if(!this.menu.slotHasItem(2))
					GuiComponent.fill(stack, this.getGuiLeft()+menu.slots.get(2+36).x, this.getGuiTop()+menu.slots.get(2+36).y, this.getGuiLeft()+menu.slots.get(2+36).x+16, this.getGuiTop()+menu.slots.get(2+36).y+16,822083583);
				
			}
	        RenderSystem.depthFunc(515);
		}
	}
	public boolean hasRecipe() {
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<FlavoringRecipe> recipe = world.getRecipeManager().getRecipeFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE, inv, world);

		return recipe.isPresent();
		
	}
	public ItemStack RecipeResult() {
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
		ArrayList<ItemStack> result = new ArrayList<>();
		for(int i=0;i<entity.itemHandler.getSlots();i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		Optional<FlavoringRecipe> recipe = world.getRecipeManager().getRecipeFor(FlavoringRecipe.FlavoringRecipeType.INSTANCE, inv, world);
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
		this.blit(stack,x+menu.slots.get(1+36).x,y+menu.slots.get(1+36).y,176+mixerstate*16,0,16,16);
	}

	@Override
	protected void renderLabels(@NotNull PoseStack matrixStack, int p_97809_, int p_97810_) {
		super.renderLabels(matrixStack, p_97809_, p_97810_);
	}
}
	 

