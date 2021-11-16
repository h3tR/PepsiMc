package ml.jozefpeeterslaan72wuustwezel.pepsimc.block.fluid;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.PepsiMcItem;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcFluid {

	public static DeferredRegister<Fluid> FLUIDS = 
			DeferredRegister.create(ForgeRegistries.FLUIDS, "pepsimc");
	
	public static final RegistryObject<Source> PEPSI_FLUID = 
			FLUIDS.register("pepsi_fluid", 
					()-> new ForgeFlowingFluid.Source(PepsiMcFluid.PEPSI_PROPERTIES));
	
	public static final RegistryObject<FlowingFluid> PEPSI_FLOW = 
			FLUIDS.register("pepsi_flow", 
					()-> new ForgeFlowingFluid.Flowing(PepsiMcFluid.PEPSI_PROPERTIES));
	
	public static final RegistryObject<Source> PEPSI_MAX_FLUID = 
			FLUIDS.register("pepsi_max_fluid", 
					()-> new ForgeFlowingFluid.Source(PepsiMcFluid.PEPSI_MAX_PROPERTIES));
	
	public static final RegistryObject<FlowingFluid> PEPSI_MAX_FLOW = 
			FLUIDS.register("pepsi_max_flow", 
					()-> new ForgeFlowingFluid.Flowing(PepsiMcFluid.PEPSI_MAX_PROPERTIES));
	
	public static final ForgeFlowingFluid.Properties PEPSI_PROPERTIES = 
		new ForgeFlowingFluid.Properties(
			() -> PEPSI_FLUID.get(), 
			() -> PEPSI_FLOW.get(),
			FluidAttributes.builder(
				new ResourceLocation("block/water_still"),
				new ResourceLocation("block/water_flow"))
			.density(5)
			.luminosity(2)
			.gaseous()
			.viscosity(5)
			.color(0xff3d1f01))
		.levelDecreasePerBlock(2)
		.slopeFindDistance(6)
		.bucket(()->PepsiMcItem.PEPSI_FLUID_BUCKET.get())
		.block(()->PepsiMcBlock.PEPSI_FLUID_BLOCK.get());
	
	public static final ForgeFlowingFluid.Properties PEPSI_MAX_PROPERTIES = 
		new ForgeFlowingFluid.Properties(
			() -> PEPSI_MAX_FLUID.get(),
			() -> PEPSI_MAX_FLOW.get(),
			FluidAttributes.builder(
				new ResourceLocation("block/water_still"),
				new ResourceLocation("block/water_flow"))
			.density(6)
			.luminosity(2).gaseous().viscosity(4).color(0xff241201))
		.levelDecreasePerBlock(2)
		.slopeFindDistance(6)
		.bucket(()->PepsiMcItem.PEPSI_MAX_FLUID_BUCKET.get())
		.block(()->PepsiMcBlock.PEPSI_MAX_FLUID_BLOCK.get());
	
	public static void register(IEventBus bus) {
		FLUIDS.register(bus);
	}
}
