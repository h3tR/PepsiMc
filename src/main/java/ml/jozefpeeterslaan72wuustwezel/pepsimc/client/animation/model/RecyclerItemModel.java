package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@SuppressWarnings("rawtypes")
public class RecyclerItemModel extends AnimatedGeoModel {




	@Override
	public void setLivingAnimations(Object entity, Integer uniqueID, AnimationEvent customPredicate) {

	}

	@Override
	public ResourceLocation getModelResource(Object object) {
		return new ResourceLocation("pepsimc", "geo/recycler.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Object object) {
		return new ResourceLocation("pepsimc", "textures/blocks/recycler.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Object animatable) {
		return new ResourceLocation("pepsimc", "animations/recycler.animation.json");
	}
}
