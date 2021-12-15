package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model.RecyclerItemModel;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.RecyclerBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RecyclerItemRender extends GeoItemRenderer<RecyclerBlockItem>{

	@SuppressWarnings("unchecked")
	public RecyclerItemRender() {
		super(new RecyclerItemModel());
	}
	
}
