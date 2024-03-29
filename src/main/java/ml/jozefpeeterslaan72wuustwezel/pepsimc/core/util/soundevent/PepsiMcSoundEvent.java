package ml.jozefpeeterslaan72wuustwezel.pepsimc.core.util.soundevent;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PepsiMcSoundEvent {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "pepsimc");
	
	public static final RegistryObject<SoundEvent> PEPSI_MAN_TUNE =
			SOUND_EVENTS.register("pepsi_man_tune", ()-> new SoundEvent(new ResourceLocation("pepsimc","pepsi_man_tune")));
	
	public static void register(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
