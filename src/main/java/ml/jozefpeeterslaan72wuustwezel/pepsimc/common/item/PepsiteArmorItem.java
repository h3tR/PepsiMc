
package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import java.util.HashMap;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.common.effect.PepsiMcEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PepsiteArmorItem extends ArmorItem {
	protected final MobEffect UniqueEffect;
	protected final EquipmentSlot slot;
	protected final HashMap<EquipmentSlot, Integer> slotmap = new HashMap<>();
	
    public PepsiteArmorItem(ArmorMaterial material,
    						EquipmentSlot slot,
    						Properties properties,
    						MobEffect UniqueEffect) {
		super(material, slot, properties);
		this.UniqueEffect = UniqueEffect;
		this.slot = slot;
		slotmap.put(EquipmentSlot.FEET, 0);
		slotmap.put(EquipmentSlot.LEGS, 1);
		slotmap.put(EquipmentSlot.CHEST, 2);
		slotmap.put(EquipmentSlot.HEAD, 3);
	}
    

    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level world, @NotNull Entity entity, int slot, boolean selected) {
        if(!world.isClientSide()) {
            if(entity instanceof Player player) {
				if(player.getInventory().getArmor(slotmap.get(this.slot)).sameItem(new ItemStack(this))) {
                	player.addEffect(new MobEffectInstance(UniqueEffect,100));
                	player.addEffect(new MobEffectInstance(PepsiMcEffect.INSOMNIA.get(),3200));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}