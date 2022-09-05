package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;


import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.recipes.RecyclerRecipe;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu.AutomatedRecyclerMenu;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.tags.PepsiMcTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

public class AutomatedRecyclerEntity extends AutomatedProcessingBlockEntity implements IAnimatable, MenuProvider {
    private final AnimationFactory factory = new AnimationFactory(this);

	public AutomatedRecyclerEntity(BlockPos pos, BlockState state) {
		super(PepsiMcBlockEntity.RECYCLER_BLOCK_ENTITY.get(), pos, state,500,50);
	}

	@Override
	protected Optional<RecyclerRecipe> getRecipe() {
		LogManager.getLogger().debug(Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, getSimpleInv(), this.getLevel()).isPresent());
		return Objects.requireNonNull(this.getLevel()).getRecipeManager().getRecipeFor(RecyclerRecipe.RecyclerRecipeType.INSTANCE, getSimpleInv(), this.getLevel());
	}

	@Override
	protected void finishProduct() {
		Optional<RecyclerRecipe> recipe = getRecipe();

		recipe.ifPresent(iRecipe->{
			itemHandler.extractItem(0, 1, false);
			itemHandler.extractItem(1, 1, false);
			itemHandler.insertItem(2, iRecipe.getResultItem(), false);
			setChanged();
		});
	}

	@Override
	protected int getOutputSlot() {
		return 2;
	}

	@Override
	protected int getByProductSlot() {
		return -1;
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
				return switch (slot) {
					case 0 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.RECYCLABLE);
					case 1 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.RECYCLING_CATALYST);
					case 2 ->
							stack.getItem().getDefaultInstance().getTags().toList().contains(PepsiMcTags.Items.RECYCLED);
					default -> false;
				};
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

	@Override
	protected LazyOptional<IItemHandler> getOutHandler() {
		return LazyOptional.of(()->new IItemHandler(){

			@Override
			public int getSlots() {
				return 1;
			}

			@NotNull
			@Override
			public ItemStack getStackInSlot(int slot) {
				return itemHandler.getStackInSlot(slot+1);
			}

			@NotNull
			@Override
			public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
				return itemHandler.insertItem(slot+1, stack, simulate);
			}

			@NotNull
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				return itemHandler.extractItem(slot+1,amount,simulate);
			}

			@Override
			public int getSlotLimit(int slot) {
				return itemHandler.getSlotLimit(slot+1);
			}

			@Override
			public boolean isItemValid(int slot, @NotNull ItemStack stack) {
				return itemHandler.isItemValid(slot+1,stack);
			}
		});	}


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
		return new AutomatedRecyclerMenu(id,inv,this,dataAccess);
	}
}
