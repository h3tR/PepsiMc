package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.soundevent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcSoundEvent {
	public static DeferredRegister<SoundEvent> SOUND_EVENTS = 
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "pepsimc");
	
	public static final RegistryObject<SoundEvent> PEPSI_MAN_TUNE = 
			SOUND_EVENTS.register("pepsi_man_tune", ()-> new SoundEvent(new ResourceLocation("pepsimc","pepsi_man_tune")));
	
	public static void register(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
