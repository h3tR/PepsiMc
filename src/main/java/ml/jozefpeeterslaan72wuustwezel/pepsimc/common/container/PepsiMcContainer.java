package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class PepsiMcContainer {
	public static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, "pepsimc");
	
	public static RegistryObject<MenuType<BottlerContainer>> BOTTLER_CONTAINER = CONTAINERS.register("bottlercontainer", ()-> IForgeContainerType.create((ID, inv, dat)->{
		BlockPos pos = dat.readBlockPos();
		Level world = inv.player.getCommandSenderWorld();
		return new BottlerContainer(ID, world, pos, inv, inv.player);
	}));
	
	public static void register(IEventBus bus) {
		CONTAINERS.register(bus);
	}
}
