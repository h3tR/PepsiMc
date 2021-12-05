package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.RecyclerTile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RecyclerModel extends AnimatedGeoModel<RecyclerTile> {

	@Override
	public ResourceLocation getAnimationFileLocation(RecyclerTile animatable) {
		return new ResourceLocation("pepsimc", "animations/recycler.animation.json");

	}

	@Override
	public ResourceLocation getModelLocation(RecyclerTile object) {
		return new ResourceLocation("pepsimc", "geo/recycler.geo?json");

	}

	@Override
	public ResourceLocation getTextureLocation(RecyclerTile object) {
		return new ResourceLocation("pepsimc", "textures/blocks/recycler.png");
	}

}
