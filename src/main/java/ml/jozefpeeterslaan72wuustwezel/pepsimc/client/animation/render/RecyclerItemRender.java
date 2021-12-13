package ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.model.RecyclerItemModel;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.AnimatedBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RecyclerItemRender extends GeoItemRenderer<AnimatedBlockItem>{

	@SuppressWarnings("unchecked")
	public RecyclerItemRender() {
		super(new RecyclerItemModel());
		// TODO Auto-generated constructor stub
	}
	
}
