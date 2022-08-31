package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import java.util.Optional;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.RecyclerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class RecyclerEntity extends ProcessingBlockEntity implements IAnimatable, MenuProvider {
    private AnimationFactory factory = new AnimationFactory(this);

	public RecyclerEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.RECYCLER_BLOCK_ENTITY.get(), pos, state);
	}


	@Override
	public void process(Level world) {
		Optional<RecyclerRecipe> recipe = world.getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, getSimpleInv(), world);

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.insertItem(2, iRecipe.getResultItem(), false);
			setChanged();
		});	

	}
	
	@Override
	public void processAll(Level world) {
		Optional<RecyclerRecipe> recipe = world.getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, getSimpleInv(), world);
		while (recipe.isPresent()) {
			recipe.ifPresent(iRecipe->{
				itemHandler.extractItem(0, 1, false);
				itemHandler.extractItem(1, 1, false);
				itemHandler.insertItem(2, iRecipe.getResultItem(), false);
				setChanged();
			});
			recipe = world.getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, getSimpleInv(), world);
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
					case 0: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.RECYCLABLE);
					case 1: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.RECYCLING_CATALYST);
					case 2: return stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.RECYCLED);
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
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.pepsimc.recycler", true));
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
		return new TranslatableComponent("block.pepsimc.recycler");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player plr) {
		return new RecyclerMenu(id,inv,this);
	}
}
