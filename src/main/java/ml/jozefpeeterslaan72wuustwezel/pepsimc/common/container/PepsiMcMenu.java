package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class PepsiMcMenu {
	public static DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "pepsimc");
	
	public static RegistryObject<MenuType<BottlerMenu>> BOTTLER_MENU= MENUS.register("bottler_menu", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new BottlerMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static RegistryObject<MenuType<RecyclerMenu>> RECYCLER_MENU = MENUS.register("recycler_menu", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new RecyclerMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static RegistryObject<MenuType<FlavorMachineMenu>> FLAVOR_MACHINE_MENU = MENUS.register("flavor_machine_menu", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new FlavorMachineMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static RegistryObject<MenuType<CentrifugeMenu>> CENTRIFUGE_MENU = MENUS.register("centrifuge_menu", ()-> IForgeMenuType.create((ID, inv, dat)->{
		return new CentrifugeMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()));
	}));
	
	public static void register(IEventBus bus) {
		MENUS.register(bus);
	}
}
