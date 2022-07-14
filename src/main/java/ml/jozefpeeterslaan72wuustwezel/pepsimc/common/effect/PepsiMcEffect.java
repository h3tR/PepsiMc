package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcEffect {

	public static final DeferredRegister<MobEffect> EFFECTS = 
			DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "pepsimc");
	public static final DeferredRegister<Potion> POTIONS = //TODO
			DeferredRegister.create(ForgeRegistries.POTIONS, "pepsimc");
	
	public static RegistryObject<MobEffect> INSOMNIA = EFFECTS.register("insomnia",InsomniaEffect::new);
	
	
	public static void register(IEventBus bus) {
		EFFECTS.register(bus);
	}
	
}
