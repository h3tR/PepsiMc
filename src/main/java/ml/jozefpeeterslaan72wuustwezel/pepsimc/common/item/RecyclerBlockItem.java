package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import java.util.function.Consumer;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.client.animation.render.RecyclerItemRender;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.NotNull;

public class RecyclerBlockItem extends AnimatedBlockItem {
	public RecyclerBlockItem(Block p_40565_, Properties p_40566_) {
		super(p_40565_,  p_40566_);
	}

	public void initializeClient(@NotNull Consumer<IItemRenderProperties> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IItemRenderProperties() {
			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return new RecyclerItemRender();
			}
		});
	}
}
