package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcEffect {

	public static final DeferredRegister<Effect> EFFECTS = 
			DeferredRegister.create(ForgeRegistries.POTIONS, "pepsimc");
	public static final DeferredRegister<Potion> POTIONS = 
			DeferredRegister.create(ForgeRegistries.POTION_TYPES, "pepsimc");
	
	public static RegistryObject<Effect> INSOMNIA = EFFECTS.register("insomnia",InsomniaEffect::new);
	
	
	public static void register(IEventBus bus) {
		EFFECTS.register(bus);
	}
	
}
