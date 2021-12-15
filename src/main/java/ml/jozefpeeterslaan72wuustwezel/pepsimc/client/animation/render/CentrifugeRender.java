package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model.CentrifugeModel;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity.CentrifugeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CentrifugeRender extends GeoBlockRenderer<CentrifugeEntity> {

	@SuppressWarnings("unchecked")
	public CentrifugeRender(Context rendererDispatcherIn) {
		super(rendererDispatcherIn, new CentrifugeModel());
	}
	
	@Override
	public RenderType getRenderType(CentrifugeEntity animatable, float partialTicks, PoseStack stack,
			MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
