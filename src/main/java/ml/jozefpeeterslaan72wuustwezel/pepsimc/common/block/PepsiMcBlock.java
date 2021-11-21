package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.LiquidBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.crops.SteviaCrop;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.fluid.PepsiMcFluid;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcBlock {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "pepsimc");

	
			// regular blocks
	public static final RegistryObject<FacedBlock> PEPSITEBLOCK = BLOCKS.register("pepsite_block",
			()->new FacedBlock(
					BlockBehaviour.Properties.of(Material.HEAVY_METAL)
					.requiresCorrectToolForDrops()
					.strength(5, 1200)
					.sound(SoundType.METAL)
					.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> PEPSITEORE = BLOCKS.register("pepsite_ore", 
			()-> new Block(
					BlockBehaviour.Properties.of(Material.STONE)
						.strength(4.5f, 15)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops()));

	
			// special blocks

	public static final RegistryObject<LiquidBlock> PEPSI_FLUID_BLOCK = BLOCKS.register("pepsi", 
			()-> new LiquidBlock(()->PepsiMcFluid.PEPSI_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noDrops().strength(100f).noCollission()));
	
	public static final RegistryObject<LiquidBlock> PEPSI_MAX_FLUID_BLOCK = BLOCKS.register("pepsi_max", 
			()-> new LiquidBlock(()->PepsiMcFluid.PEPSI_MAX_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).strength(100f).noDrops().noCollission()));
	
	public static final RegistryObject<Block> BOTTLER = BLOCKS.register("bottler",BottlerBlock::new);
	
	public static final RegistryObject<Block> STEVIA_PLANT = BLOCKS.register("stevia_plant", 
			()-> new FlowerBlock(MobEffects.MOVEMENT_SPEED,2,BlockBehaviour.Properties.copy(Blocks.POPPY)));

	 public static final RegistryObject<Block> STEVIA_CROP = BLOCKS.register("stevia_crop",
	            () -> new SteviaCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
	
	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
	}
}

