package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container.CentrifugeContainer;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.CentrifugeRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.PepsiMcRecipeType;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CentrifugeEntity extends ProcessingBlockEntity implements IAnimatable,MenuProvider {
    private AnimationFactory factory = new AnimationFactory(this);

	public CentrifugeEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.CENTRIFUGE_TILE.get(), pos, state);
	}

	@Override
	public void process(Level world) {
		Optional<CentrifugeRecipe> recipe = world.getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, getSimpleInv(), world);

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.insertItem(1, iRecipe.getResultItem(), false);
			itemHandler.insertItem(2, iRecipe.getByproductItem(), false);
			setChanged();
		});	

	}
	
	@Override
	public void processAll(Level world) {
		Optional<CentrifugeRecipe> recipe = world.getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, getSimpleInv(), world);
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.insertItem(1, iRecipe.getResultItem(), false);
				itemHandler.insertItem(2, iRecipe.getByproductItem(), false);

				setChanged();
			});	
			recipe = world.getRecipeManager().getRecipeFor(CentrifugeRecipe.CentrifugeRecipeType.INSTANCE, getSimpleInv(), world);
		}
		

	}
	
	@Override
	protected ItemStackHandler createHandler() {

		return new ItemStackHandler(3) {
			
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}

			@Override 
			public int getSlotLimit(int slot){
				return 64;	
			}
			

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				switch (slot) {
					case 0: return true;
					case 1: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.EXTRACTED);
					case 2: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.EXTRACTION_BYPRODUCT);
					default:
						return false;
				
				}
			}
			@Override
			@Nonnull
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				if(!isItemValid(slot, stack)) {
					return stack;
				}
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {		
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.pepsimc.centrifuge", true));
        return PlayState.CONTINUE;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void registerControllers(AnimationData data) {
		 data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		 return this.factory;
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("screen.pepsimc.centrifuge");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player plr) {
		return new CentrifugeContainer(id,inv,this);
	}
}
