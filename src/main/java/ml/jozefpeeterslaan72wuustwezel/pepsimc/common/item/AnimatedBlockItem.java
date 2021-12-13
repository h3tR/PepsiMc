package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import java.util.function.Consumer;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render.RecyclerItemRender;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AnimatedBlockItem extends BlockItem implements IAnimatable {

	public AnimationFactory factory = new AnimationFactory(this);
	public AnimatedBlockItem(Block p_40565_, Properties p_40566_) {
		super(p_40565_, p_40566_);
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IItemRenderProperties() {
			private final BlockEntityWithoutLevelRenderer renderer = new RecyclerItemRender();

			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return renderer;
			}
		});
	}
	
	public <P extends BlockItem & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<AnimatedBlockItem>(this, "controller", 1, this::predicate));

	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
