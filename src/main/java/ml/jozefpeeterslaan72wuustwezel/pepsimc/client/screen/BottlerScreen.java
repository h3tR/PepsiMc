package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.screen;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.BottlerContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.BottlerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.tileentity.BottlerTile;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.PepsimcNetwork;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.network.packet.BottlerCraftPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
public class BottlerScreen extends ContainerScreen<BottlerContainer>{

	public BottlerScreen(BottlerContainer BC, PlayerInventory plrInv, ITextComponent Text) {
		super(BC, plrInv, Text);
		// TODO Auto-generated constructor stub
	}

	private static final ResourceLocation GUI = new ResourceLocation("pepsimc","textures/gui/bottler_gui.png");
	

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float Ptick) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, Ptick);
		this.renderTooltip(stack, mouseX, mouseY);
		if (hasRecipe()) {
			
			if(!this.menu.slotHasItem(3))
			Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(RecipeResult(), this.getGuiLeft()+143, this.getGuiTop()+30);
			if(!this.menu.slotHasItem(4))
			Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(new ItemStack(Items.BUCKET), this.getGuiLeft()+143, this.getGuiTop()+51);
			for (int i = 0; i < 5; i++) {
	    	   RenderSystem.depthFunc(516+i);
				if(!this.menu.slotHasItem(3))
	    	   AbstractGui.fill(stack, this.getGuiLeft()+143, this.getGuiTop()+30, this.getGuiLeft()+159, this.getGuiTop()+46,822083583);
				if(!this.menu.slotHasItem(4))
				AbstractGui.fill(stack, this.getGuiLeft()+143, this.getGuiTop()+51, this.getGuiLeft()+159, this.getGuiTop()+67,822083583);
			}
			
		
	        RenderSystem.depthFunc(515);

			this.addButton(new BottlerScreen.ConfirmButton(this.getGuiLeft()+95,this.getGuiTop()+30,176,3,23,9));
			if(this.isHovering(95,30, 23, 9, mouseX, mouseY)) {

				if(this.createTooltip()!=null) {

				this.renderTooltip(stack, this.createTooltip(), mouseX,mouseY);
				}
			}
		}
		
	}
	
	private ITextComponent createTooltip() {
  	  BottlerTile TE = (BottlerTile) BottlerScreen.this.menu.TE;
        World world = TE.getLevel();
        Inventory inv = new Inventory(TE.itemHandler.getSlots());
        ArrayList<ITextComponent> text = new ArrayList<ITextComponent>();
		for(int i=0;i<TE.itemHandler.getSlots();i++) {
			inv.setItem(i, TE.itemHandler.getStackInSlot(i));
		}
		
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, inv, world);
		recipe.ifPresent(iRecipe->{
			text.add(iRecipe.getResultItem().getHoverName());
			
		});	
		if(text.size()>0) {
			return text.get(0);
			
		}
		return null;
    }
	private boolean hasRecipe() {
		BottlerTile TE = (BottlerTile) BottlerScreen.this.menu.TE;
        World world = TE.getLevel();
        Inventory inv = new Inventory(TE.itemHandler.getSlots());
        
		for(int i=0;i<TE.itemHandler.getSlots();i++) {
			inv.setItem(i, TE.itemHandler.getStackInSlot(i));
		}
		
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, inv, world);
		LogManager.getLogger().debug(recipe.toString());

		return recipe.isPresent();
		
	}
	private ItemStack RecipeResult() {
		BottlerTile TE = (BottlerTile) BottlerScreen.this.menu.TE;
        World world = TE.getLevel();
        Inventory inv = new Inventory(TE.itemHandler.getSlots());
        ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		for(int i=0;i<TE.itemHandler.getSlots();i++) {
			inv.setItem(i, TE.itemHandler.getStackInSlot(i));
		}
		
		Optional<BottlerRecipe> recipe = world.getRecipeManager().getRecipeFor(PepsiMcRecipeType.BOTTLER_RECIPE, inv, world);
		LogManager.getLogger().debug(recipe.toString());

		recipe.ifPresent(i->{
			result.add(i.getResultItem());
		});
		return result.get(0);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack stack, float Ptick, int X, int Y) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.getTextureManager().bind(GUI);
		int i = this.getGuiLeft();
		int j = this.getGuiTop();
		this.blit(stack, i, j, 0, 0, this.getXSize(), this.getYSize()+2);
		if(this.menu.slotHasItem(0)) {
			this.blit(stack, i+61, j+35, 176, 0, 3, 3);
		}
		if (this.menu.slotHasItem(1)) {
			this.blit(stack, i+69, j+35, 176, 0, 3, 3);
		}
		if (this.menu.slotHasItem(2)) {
			this.blit(stack, i+77, j+35, 176, 0, 3, 3);
		}
	}
	
	
	@OnlyIn(Dist.CLIENT)
	   abstract static class Button extends AbstractButton {
	      private boolean selected;

	      protected Button(int X, int Y, int SizeX,int SizeY) {
	         super(X, Y, SizeX, SizeY, StringTextComponent.EMPTY);
	      }

	      @SuppressWarnings("deprecation")
		public void renderButton(MatrixStack stack, int X, int Y, float Ptick) {
	         Minecraft.getInstance().getTextureManager().bind(BottlerScreen.GUI);
	         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	         int i = 219;
	         int j = 0;
	         if (!this.active) {
	            j += this.width * 2;
	         } else if (this.selected) {
	            j += this.width * 1;
	         } else if (this.isHovered()) {
	            j += this.width * 3;
	         }

	         this.blit(stack, this.x, this.y, j, i, this.width, this.height);
	         this.renderIcon(stack);
	      }

	      protected abstract void renderIcon(MatrixStack stack);

	      public boolean isSelected() {
	         return this.selected;
	      }

	      public void setSelected(boolean selected) {
	         this.selected = selected;
	      }
	   }
	 
	 @OnlyIn(Dist.CLIENT)
	   class ConfirmButton extends BottlerScreen.Button {
		 int iconX;
		 int iconY;
		 int SizeX;
		 int SizeY;
		
	      public ConfirmButton(int X, int Y, int iconX, int iconY, int SizeX, int SizeY) {
	    	  super(X, Y, SizeX, SizeY);
	    	  this.iconX = iconX;
	    	  this.iconY = iconY;
	    	  this.SizeX = SizeX;
	    	  this.SizeY = SizeY;
	      }
		
		        		      
	      public void onPress() {
	    	  BlockPos pos = BottlerScreen.this.menu.TE.getBlockPos();
	    	  int posar[] = {pos.getX(),pos.getY(),pos.getZ()};
	    	  PepsimcNetwork.CHANNEL.sendToServer(new BottlerCraftPacket(posar));
	      }

	     
	      protected void renderIcon(MatrixStack stack) {
		         this.blit(stack, this.x + 2, this.y + 2, this.iconX, this.iconY, this.SizeX, this.SizeY);
		      }
	   }
	 
	
}
	 

