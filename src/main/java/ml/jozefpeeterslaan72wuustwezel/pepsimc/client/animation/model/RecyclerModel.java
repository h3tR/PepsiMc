package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.RecyclerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RecyclerModel extends AnimatedGeoModel<RecyclerEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(RecyclerEntity animatable) {
		return new ResourceLocation("pepsimc", "animations/recycler.animation.json");

	}

	@Override
	public ResourceLocation getModelLocation(RecyclerEntity object) {
		return new ResourceLocation("pepsimc", "geo/recycler.geo.json");

	}

	@Override
	public ResourceLocation getTextureLocation(RecyclerEntity object) {
		return new ResourceLocation("pepsimc", "textures/blocks/recycler.png");
	}

}
