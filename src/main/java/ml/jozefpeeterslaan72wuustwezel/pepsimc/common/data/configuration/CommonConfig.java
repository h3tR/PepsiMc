package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final int defaultFEUsage = 100;
    public static final int defaultFEStorage = 16384;
    public static final int defaultFluidStorage = 8000;

    public static final int defaultFETransferRate = 256;


    public static final ForgeConfigSpec.ConfigValue<Integer> BOTTLER_FE_USAGE_PER_TICK;
    public static final ForgeConfigSpec.ConfigValue<Integer> CENTRIFUGE_FE_USAGE_PER_TICK;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLAVOR_MACHINE_FE_USAGE_PER_TICK;
    public static final ForgeConfigSpec.ConfigValue<Integer> RECYCLER_FE_USAGE_PER_TICK;

    public static final ForgeConfigSpec.ConfigValue<Integer> BOTTLER_FE_STORAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BOTTLER_FLUID_STORAGE;

    public static final ForgeConfigSpec.ConfigValue<Integer> CENTRIFUGE_FE_STORAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLAVOR_MACHINE_FE_STORAGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> RECYCLER_FE_STORAGE;

    public static final ForgeConfigSpec.ConfigValue<Integer> BOTTLER_CONDUCTIVITY;
    public static final ForgeConfigSpec.ConfigValue<Integer> CENTRIFUGE_CONDUCTIVITY;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLAVOR_MACHINE_CONDUCTIVITY;
    public static final ForgeConfigSpec.ConfigValue<Integer> RECYCLER_CONDUCTIVITY;


    public static final ForgeConfigSpec.ConfigValue<Integer> STEVIA_SPAWNRATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> PEPSITE_ORE_SPAWNRATE;



    static{
        BUILDER.push("Configuration for PepsiMc");


        BOTTLER_FE_USAGE_PER_TICK = BUILDER.define("FE/t for Automated Bottler", defaultFEUsage);
        CENTRIFUGE_FE_USAGE_PER_TICK = BUILDER.define("FE/t for Automated Centrifuge", defaultFEUsage);
        FLAVOR_MACHINE_FE_USAGE_PER_TICK = BUILDER.define("FE/t for Automated Flavor Machine", defaultFEUsage);
        RECYCLER_FE_USAGE_PER_TICK = BUILDER.define("FE/t for Automated Recycler", defaultFEUsage);

        BOTTLER_FE_STORAGE = BUILDER.define("FE Storage for Automated Bottler", defaultFEStorage);
        BOTTLER_FLUID_STORAGE = BUILDER.define("Fluid Storage for Automated Bottler", defaultFluidStorage);

        CENTRIFUGE_FE_STORAGE = BUILDER.define("FE Storage for Automated Centrifuge", defaultFEStorage);
        FLAVOR_MACHINE_FE_STORAGE = BUILDER.define("FE Storage for Automated Flavor Machine", defaultFEStorage);
        RECYCLER_FE_STORAGE = BUILDER.define("FE Storage for Automated Recycler", defaultFEStorage);

        BOTTLER_CONDUCTIVITY = BUILDER.define("FE transfer rate for Automated Bottler", defaultFETransferRate);
        CENTRIFUGE_CONDUCTIVITY = BUILDER.define("FE transfer rate for Automated Centrifuge", defaultFETransferRate);
        FLAVOR_MACHINE_CONDUCTIVITY = BUILDER.define("FE transfer rate for Automated Flavor Machine", defaultFETransferRate);
        RECYCLER_CONDUCTIVITY = BUILDER.define("FE transfer rate for Automated Recycler", defaultFETransferRate);


        STEVIA_SPAWNRATE = BUILDER.define("Spawnrate for Stevia", 32);
        PEPSITE_ORE_SPAWNRATE = BUILDER.define("Spawnrate for Pepsite Ore & Deepslate variant", 8);


        BUILDER.pop();
        SPEC = BUILDER.build();

   }
}
