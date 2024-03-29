package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.menu;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class PepsiMcMenu {
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "pepsimc");
	
	public static final RegistryObject<MenuType<BottlerMenu>> BOTTLER_MENU= MENUS.register("bottler_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new BottlerMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()))));
	
	public static final RegistryObject<MenuType<RecyclerMenu>> RECYCLER_MENU = MENUS.register("recycler_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new RecyclerMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()))));
	
	public static final RegistryObject<MenuType<FlavorMachineMenu>> FLAVOR_MACHINE_MENU = MENUS.register("flavor_machine_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new FlavorMachineMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()))));
	
	public static final RegistryObject<MenuType<CentrifugeMenu>> CENTRIFUGE_MENU = MENUS.register("centrifuge_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new CentrifugeMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()))));

	public static final RegistryObject<MenuType<AutomatedBottlerMenu>> AUTOMATED_BOTTLER_MENU= MENUS.register("automated_bottler_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new AutomatedBottlerMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()),new SimpleContainerData(4))));

	public static RegistryObject<MenuType<AutomatedRecyclerMenu>> AUTOMATED_RECYCLER_MENU = MENUS.register("automated_recycler_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new AutomatedRecyclerMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()),new SimpleContainerData(4))));

	public static RegistryObject<MenuType<AutomatedFlavorMachineMenu>> AUTOMATED_FLAVOR_MACHINE_MENU = MENUS.register("automated_flavor_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new AutomatedFlavorMachineMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()),new SimpleContainerData(4))));

	public static RegistryObject<MenuType<AutomatedCentrifugeMenu>> AUTOMATED_CENTRIFUGE_MENU = MENUS.register("automated_centrifuge_machine_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new AutomatedCentrifugeMenu(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()),new SimpleContainerData(4))));

	public static final RegistryObject<MenuType<GeneratorContainer>> GENERATOR_CONTAINER = MENUS.register("gen_menu", ()-> IForgeMenuType.create((ID, inv, dat)-> new GeneratorContainer(ID, inv, inv.player.getCommandSenderWorld().getBlockEntity(dat.readBlockPos()))));

	public static void register(IEventBus bus) {
		MENUS.register(bus);
	}
}
