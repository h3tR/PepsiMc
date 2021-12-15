package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@SuppressWarnings("rawtypes")
public class CentrifugeModel extends AnimatedGeoModel {

	@Override
	public ResourceLocation getModelLocation(Object object) {
		return new ResourceLocation("pepsimc", "geo/centrifuge.geo.json");

	}

	@Override
	public ResourceLocation getTextureLocation(Object object) {
		return new ResourceLocation("pepsimc", "textures/blocks/centrifuge.png");
	}
	
	@Override
	public ResourceLocation getAnimationFileLocation(Object animatable) {
		return new ResourceLocation("pepsimc", "animations/centrifuge.animation.json");

	}
}
