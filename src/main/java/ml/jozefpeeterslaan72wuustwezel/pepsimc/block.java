package ml.jozefpeeterslaan72wuustwezel.pepsimc;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class block {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "pepsimc");

	public static final RegistryObject<Block> PEPSITEBLOCK = BLOCKS.register("pepsite_block", ()-> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestLevel(2).strength(5, 1200).sound(SoundType.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> PEPSITEORE = BLOCKS.register("pepsite_ore", ()-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(2).strength(4.5f, 15).sound(SoundType.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE)));

}
