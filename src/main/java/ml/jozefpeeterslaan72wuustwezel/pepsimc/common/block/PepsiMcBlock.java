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
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcBlock {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "pepsimc");

	
			// regular blocks
	public static final RegistryObject<HorizontalFacedBlock> PEPSITE_BLOCK = BLOCKS.register("pepsite_block",
			()->new HorizontalFacedBlock(
					BlockBehaviour.Properties.of(Material.HEAVY_METAL)
					.requiresCorrectToolForDrops()
					.strength(5, 1200)
					.sound(SoundType.METAL)
					.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> RAW_PEPSITE_BLOCK = BLOCKS.register("raw_pepsite_block", 
			()-> new Block(
					BlockBehaviour.Properties.of(Material.METAL)
						.strength(4.5f, 15)
						.sound(SoundType.METAL)
						.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> PEPSITE_ORE = BLOCKS.register("pepsite_ore", 
			()-> new Block(
					BlockBehaviour.Properties.of(Material.STONE)
						.strength(4.5f, 15)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops()));
	
	public static final RegistryObject<Block> DEEPSLATE_PEPSITE_ORE = BLOCKS.register("deepslate_pepsite_ore", 
			()-> new Block(
					BlockBehaviour.Properties.of(Material.STONE)
						.strength(4.5f, 15)
						.sound(SoundType.DEEPSLATE)
						.requiresCorrectToolForDrops()));

	
			// special blocks

	public static final RegistryObject<LiquidBlock> PEPSI_FLUID_BLOCK = BLOCKS.register("pepsi",
			()-> new LiquidBlock(PepsiMcFluid.PEPSI_FLUID, BlockBehaviour.Properties.of(Material.WATER).noDrops().strength(100f).noCollission()));
	
	public static final RegistryObject<LiquidBlock> PEPSI_MAX_FLUID_BLOCK = BLOCKS.register("pepsi_max",
			()-> new LiquidBlock(PepsiMcFluid.PEPSI_MAX_FLUID, BlockBehaviour.Properties.of(Material.WATER).strength(100f).noDrops().noCollission()));
	

	public static final RegistryObject<Block> AUTOMATED_BOTTLER = BLOCKS.register("automated_bottler",AutomatedBottlerBlock::new);
	public static final RegistryObject<Block> AUTOMATED_CENTRIFUGE = BLOCKS.register("automated_centrifuge",AutomatedCentrifugeBlock::new);
	public static final RegistryObject<Block> AUTOMATED_FLAVOR_MACHINE = BLOCKS.register("automated_flavor_machine",AutomatedFlavorMachineBlock::new);
	public static final RegistryObject<Block> AUTOMATED_RECYCLER = BLOCKS.register("automated_recycler",AutomatedRecyclerBlock::new);

	public static final RegistryObject<Block> BOTTLER = BLOCKS.register("bottler",BottlerBlock::new);
	public static final RegistryObject<Block> FLAVOR_MACHINE = BLOCKS.register("flavor_machine",FlavorMachineBlock::new);
	public static final RegistryObject<Block> RECYCLER = BLOCKS.register("recycler",RecyclerBlock::new);
	public static final RegistryObject<Block> CENTRIFUGE = BLOCKS.register("centrifuge",CentrifugeBlock::new);

	public static final RegistryObject<Block> GENERATOR = BLOCKS.register("generator",GeneratorBlock::new);


	public static final RegistryObject<Block> STEVIA_PLANT = BLOCKS.register("stevia_plant", 
			()-> new FlowerBlock(MobEffects.MOVEMENT_SPEED,2,BlockBehaviour.Properties.copy(Blocks.POPPY)));

	 public static final RegistryObject<Block> STEVIA_CROP = BLOCKS.register("stevia_crop",
	            () -> new SteviaCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
	
	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
	}
}

