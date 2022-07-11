package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.entity.villager.villagerPOI.PepsiMcVillagerPOI;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item.PepsiMcItem;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PepsiMcProfession {/*
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.PROFESSIONS, "pepsimc");

	public static final RegistryObject<VillagerProfession> BOTTLING_OPERATOR = 
			VILLAGER_PROFESSION.register("bottling_plant_operator",()->
			new VillagerProfession("bottling_plant_operator",
					PepsiMcVillagerPOI.BOTTLING_OPERATOR.get(),
					ImmutableSet.of(PepsiMcItem.CAFFEINE.get(),PepsiMcItem.STEVIA.get()),
					ImmutableSet.of(PepsiMcBlock.PEPSITE_BLOCK.get()),
					(SoundEvent)null));
	
	public static void register(IEventBus bus) {
		VILLAGER_PROFESSION.register(bus);
	}*/

}
