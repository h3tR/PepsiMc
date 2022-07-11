package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@SuppressWarnings("rawtypes")
public class CentrifugeModel extends AnimatedGeoModel {

	@Override
	public void setLivingAnimations(Object entity, Integer uniqueID, AnimationEvent customPredicate) {

	}

	@Override
	public ResourceLocation getModelResource(Object object) {
		return new ResourceLocation("pepsimc", "geo/centrifuge.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Object object) {
		return new ResourceLocation("pepsimc", "textures/blocks/centrifuge.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Object animatable) {
		return new ResourceLocation("pepsimc", "animations/centrifuge.animation.json");
	}
}
