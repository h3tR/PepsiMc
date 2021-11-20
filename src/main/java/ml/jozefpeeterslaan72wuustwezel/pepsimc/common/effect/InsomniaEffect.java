package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect;

import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid="pepsimc",bus = Bus.FORGE)
public class InsomniaEffect extends Effect {

	protected InsomniaEffect() {
		super(EffectType.HARMFUL, 22222222);
		// TODO Auto-generated constructor stub
	}
	@SubscribeEvent
	public static void SleepEvent(PlayerSleepInBedEvent event) {
		if(event.getPlayer().hasEffect(PepsiMcEffect.INSOMNIA.get())) {
			event.setResult(SleepResult.NOT_POSSIBLE_NOW);
		}
	}
	
}
