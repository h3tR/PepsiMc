package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

public class PepsiMcBlockStateProperties {
    public static final EnumProperty<BottlerActivity> BOTTLING = EnumProperty.create("bottling", BottlerActivity.class);


    public enum BottlerActivity implements StringRepresentable {
        NONE("none"),
        CAN("can"),
        BOTTLE("bottle");

        private final String name;

        BottlerActivity(String name) {
            this.name = name;
        }

        public String toString() {
            return this.getSerializedName();
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}
