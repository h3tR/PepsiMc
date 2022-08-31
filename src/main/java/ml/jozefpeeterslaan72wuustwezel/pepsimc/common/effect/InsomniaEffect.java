package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid="pepsimc",bus = Bus.FORGE)
public class InsomniaEffect extends MobEffect {

	protected InsomniaEffect() {
		super(MobEffectCategory.HARMFUL, 22222222);
	}
	@SubscribeEvent
	public static void SleepEvent(PlayerSleepInBedEvent event) {
		if(event.getPlayer().hasEffect(PepsiMcEffect.INSOMNIA.get())) {
			event.setResult(BedSleepingProblem.OTHER_PROBLEM);
			event.getPlayer().displayClientMessage(new TranslatableComponent("block.minecraft.bed.insomnia_effect"),true);
		}
	}
	
}
