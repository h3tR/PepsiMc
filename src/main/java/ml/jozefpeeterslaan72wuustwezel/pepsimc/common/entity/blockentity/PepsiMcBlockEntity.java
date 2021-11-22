package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcBlockEntity {
	public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, "pepsimc");
	
	public static RegistryObject<BlockEntityType<BottlerTile>> BOTTLER_TILE = BLOCK_ENTITIES.register("bottlertile", 
			()-> BlockEntityType.Builder.of(BottlerTile::new, PepsiMcBlock.BOTTLER.get()).build(null));
	
	public static void register(IEventBus bus) {
		BLOCK_ENTITIES.register(bus);
	}
}
