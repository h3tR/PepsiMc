package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
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

	
	public <P extends BlockItem & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "controller", 1, this::predicate));

	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
