package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events;

import javax.annotation.Nonnull;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events.loot.FruitAdditionModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pepsimc", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
	
	@SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new FruitAdditionModifier.Serializer().setRegistryName(
                		new ResourceLocation("pepsimc","lemon_from_acacia_leaves")),
                new FruitAdditionModifier.Serializer().setRegistryName(
                		new ResourceLocation("pepsimc","mango_from_jungme_leaves")),
                new FruitAdditionModifier.Serializer().setRegistryName(
                		new ResourceLocation("pepsimc","cherry_from_birch_leaves")));
    }
}
