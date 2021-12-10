package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@SuppressWarnings("rawtypes")
public class RecyclerModel extends AnimatedGeoModel {

	@Override
	public ResourceLocation getModelLocation(Object object) {
		return new ResourceLocation("pepsimc", "geo/recycler.geo.json");

	}

	@Override
	public ResourceLocation getTextureLocation(Object object) {
		return new ResourceLocation("pepsimc", "textures/blocks/recycler.png");
	}
	
	@Override
	public ResourceLocation getAnimationFileLocation(Object animatable) {
		return new ResourceLocation("pepsimc", "animations/recycler.animation.json");

	}
}
