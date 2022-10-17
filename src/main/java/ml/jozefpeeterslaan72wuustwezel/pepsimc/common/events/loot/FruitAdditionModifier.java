package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.events.loot;

import java.util.List;
import java.util.Objects;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class FruitAdditionModifier extends LootModifier {
	private final Item addition;
	private final int count;
	protected FruitAdditionModifier(LootItemCondition[] conditionsIn,Item addition,int count) {
		super(conditionsIn);
		this.addition = addition;
		this.count = count;
	}

	@NotNull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(new ItemStack(this.addition,this.count));
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<FruitAdditionModifier> {

        @Override
        public FruitAdditionModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
        	Item addition = ForgeRegistries.ITEMS.getValue(
					new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            int count = GsonHelper.getAsInt(object,"count");
            return new FruitAdditionModifier(conditionsIn,addition,count);
        }

        @Override
        public JsonObject write(FruitAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.addition)).toString());
            json.addProperty("count", instance.count);
            return json;
        }
    }
}
