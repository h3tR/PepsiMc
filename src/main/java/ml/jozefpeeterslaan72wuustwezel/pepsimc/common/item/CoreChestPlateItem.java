package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import org.apache.logging.log4j.LogManager;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect.PepsiMcEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CoreChestPlateItem extends PepsiteArmorItem {

	private final MobEffect[] CommonEffects = {MobEffects.DAMAGE_BOOST,MobEffects.HEALTH_BOOST,MobEffects.DAMAGE_RESISTANCE,MobEffects.MOVEMENT_SPEED};
	public CoreChestPlateItem(ArmorMaterial material, EquipmentSlot slot, Properties properties,
			MobEffect UniqueEffect) {
		super(material, slot, properties, UniqueEffect);
		slotmap.put(EquipmentSlot.FEET, 0);
		slotmap.put(EquipmentSlot.LEGS, 1);
		slotmap.put(EquipmentSlot.CHEST, 2);
		slotmap.put(EquipmentSlot.HEAD, 3);
	}
	
	  @Override
	    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
	        if(!world.isClientSide()) {
	            if(entity instanceof Player) {
	                Player player = (Player)entity;
	                if(
		                player.getInventory().getArmor(slotmap.get(EquipmentSlot.FEET)).sameItem(new ItemStack(PepsiMcItem.PEPSITE_BOOTS.get()))&&
		                player.getInventory().getArmor(slotmap.get(EquipmentSlot.LEGS)).sameItem(new ItemStack(PepsiMcItem.PEPSITE_LEGGINGS.get()))&&
	                	player.getInventory().getArmor(slotmap.get(EquipmentSlot.CHEST)).sameItem(new ItemStack(this))&&
	                	player.getInventory().getArmor(slotmap.get(EquipmentSlot.HEAD)).sameItem(new ItemStack(PepsiMcItem.PEPSITE_HELMET.get()))) {
	                	for (int i = 0; i < CommonEffects.length; i++) {
	                		player.addEffect(new MobEffectInstance(CommonEffects[i],100));
	                	}
	                	player.addEffect(new MobEffectInstance(UniqueEffect,100));
	                	player.addEffect(new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(),3200));
	                }
	            }
	        }
	        super.inventoryTick(stack, world, entity, slot, selected);
	    }

}
