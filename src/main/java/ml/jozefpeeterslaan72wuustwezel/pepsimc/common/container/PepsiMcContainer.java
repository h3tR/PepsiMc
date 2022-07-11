package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class PepsiMcContainer {
	public static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "pepsimc");
	
	public static RegistryObject<MenuType<BottlerContainer>> BOTTLER_CONTAINER = CONTAINERS.register("bottlercontainer", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new BottlerContainer(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static RegistryObject<MenuType<RecyclerContainer>> RECYCLER_CONTAINER = CONTAINERS.register("recycler_container", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new RecyclerContainer(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static RegistryObject<MenuType<FlavorMachineContainer>> FLAVOR_MACHINE_CONTAINER = CONTAINERS.register("flavor_machine_container", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new FlavorMachineContainer(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static RegistryObject<MenuType<CentrifugeContainer>> CENTRIFUGE_CONTAINER = CONTAINERS.register("centrifuge_container", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new CentrifugeContainer(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static void register(IEventBus bus) {
		CONTAINERS.register(bus);
	}
}
