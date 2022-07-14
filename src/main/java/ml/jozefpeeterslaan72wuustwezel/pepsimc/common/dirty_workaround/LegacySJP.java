package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.dirty_workaround;

import java.util.function.Supplier;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class LegacySJP {
	//TODO
	/*extends SinglePoolElement {
	   public static final Codec<LegacySJP> CODEC = RecordCodecBuilder.create((p_236833_0_) -> {
	      return p_236833_0_.group(templateCodec(), processorsCodec(), projectionCodec()).apply(p_236833_0_, LegacySJP::new);
	   });

	   public LegacySJP(Either<ResourceLocation, StructureTemplate> p_i242007_1_, Supplier<StructureProcessorList> p_i242007_2_, StructureTemplatePool.Projection p_i242007_3_) {
	      super(p_i242007_1_, p_i242007_2_, p_i242007_3_);
	   }

	   protected StructurePlaceSettings getSettings(Rotation p_230379_1_, BoundingBox p_230379_2_, boolean p_230379_3_) {
	      StructurePlaceSettings placementsettings = super.getSettings(p_230379_1_, p_230379_2_, p_230379_3_);
	      placementsettings.popProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
	      placementsettings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
	      return placementsettings;
	   }

	   public StructurePoolElementType<?> getType() {
	      return StructurePoolElementType.LEGACY;
	   }

	   public String toString() {
	      return "LegacySingle[" + this.template + "]";
	   }
	}*/
	}