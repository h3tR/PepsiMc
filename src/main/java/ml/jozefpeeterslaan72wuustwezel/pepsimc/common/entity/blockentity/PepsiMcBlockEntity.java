package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.blockentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcBlockEntity {
	public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, "pepsimc");
	
	public static RegistryObject<BlockEntityType<BottlerEntity>> BOTTLER_TILE = BLOCK_ENTITIES.register("bottlertile", 
			()-> BlockEntityType.Builder.of(BottlerEntity::new, PepsiMcBlock.BOTTLER.get()).build(null));
	
	public static RegistryObject<BlockEntityType<ExtractorEntity>> EXTRACTOR_TILE = BLOCK_ENTITIES.register("extractortile", 
			()-> BlockEntityType.Builder.of(ExtractorEntity::new, PepsiMcBlock.EXTRACTOR.get()).build(null));
	
	public static RegistryObject<BlockEntityType<FlavorMachineBlock>> FLAVOR_MACHINE_TILE = BLOCK_ENTITIES.register("flavor_machinetile", 
			()-> BlockEntityType.Builder.of(FlavorMachineBlock::new, PepsiMcBlock.FLAVOR_MACHINE.get()).build(null));
	
	public static RegistryObject<BlockEntityType<RecyclerEntity>> RECYCLER_TILE = BLOCK_ENTITIES.register("recyclertile", 
			()-> BlockEntityType.Builder.of(RecyclerEntity::new, PepsiMcBlock.RECYCLER.get()).build(null));
	
	public static void register(IEventBus bus) {
		BLOCK_ENTITIES.register(bus);
	}
}
