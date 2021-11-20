package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class PepsiMcContainer {
	public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "pepsimc");
	
	public static RegistryObject<ContainerType<BottlerContainer>> BOTTLER_CONTAINER = CONTAINERS.register("bottlercontainer", ()-> IForgeContainerType.create((ID, inv, dat)->{
		BlockPos pos = dat.readBlockPos();
		World world = inv.player.getEntity().level;
		return new BottlerContainer(ID, world, pos, inv, inv.player);
	}));
	
	public static void register(IEventBus bus) {
		CONTAINERS.register(bus);
	}
}
