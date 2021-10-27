package ml.jozefpeeterslaan72wuustwezel.pepsimc.tileentity;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcTileEntity {
	public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "pepsimc");
	
	public static RegistryObject<TileEntityType<BottlerTile>> BOTTLER_TILE = TILE_ENTITIES.register("bottlertile", ()-> TileEntityType.Builder.of(BottlerTile::new, PepsiMcBlock.BOTTLER.get()).build(null));
}
