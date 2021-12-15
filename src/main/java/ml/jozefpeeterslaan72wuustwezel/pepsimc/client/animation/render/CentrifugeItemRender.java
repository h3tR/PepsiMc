package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model.CentrifugeItemModel;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.CentrifugeBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CentrifugeItemRender extends GeoItemRenderer<CentrifugeBlockItem>{

	@SuppressWarnings("unchecked")
	public CentrifugeItemRender() {
		super(new CentrifugeItemModel());
	}
	
}