package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.item;

import com.google.common.base.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

@SuppressWarnings("deprecation")
public enum PepsiMcArmorMaterial implements ArmorMaterial{
	PEPSITE("pepsite", 150, new int[]{16, 16, 16, 16}, 5, SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F, 0.2F, () -> {
        return Ingredient.of(PepsiMcItem.PEPSITE_SHARD.get());
    }),
	COREPEPSITE("corepepsite", 300, new int[]{16, 16, 16, 16}, 5, SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F, 0.2F, () -> {
        return Ingredient.of(PepsiMcItem.PEPSITE_CORE.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{23, 25, 26, 21};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private PepsiMcArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
                             SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

	public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return "pepsimc:" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
