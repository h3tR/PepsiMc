package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.structures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.Vec3i;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;

public class ExcavationSite extends StructureFeature<NoneFeatureConfiguration>{

	public ExcavationSite() {
		super(NoneFeatureConfiguration.CODEC);
	}

	@Override
	public GenerationStep.Decoration step() {
	      return GenerationStep.Decoration.TOP_LAYER_MODIFICATION;
	   }
	
	@Override
	protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int chunkX, int chunkZ, Biome p_230363_8_, ChunkPos p_230363_9_, NoneFeatureConfiguration p_230363_10_) {
		
		BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
		
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),Heightmap.Types.WORLD_SURFACE_WG);

        BlockGetter columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
	   }
	
	@Override
	public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return ExcavationSite.Start::new;
		
	}

	
	 public static class Start extends StructureStart<NoneFeatureConfiguration> {
	        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ,
	                     BoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
	            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
	        }

	        @Override // generatePieces
	        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator,
	                                   StructureManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn,
	                                   NoneFeatureConfiguration config) {
	            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
	        	 int x = chunkX * 16;
	             int z = chunkZ * 16;

	            BlockPos centerPos = new BlockPos(x, 0, z);
	            
	            JigsawPlacement.addPieces(dynamicRegistryManager, 
	            		new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
	            				.get(new ResourceLocation("pepsimc", "excavation_site/start_pool")),
	            				10), 
	            		PoolElementStructurePiece::new,
	            		chunkGenerator,
	            		templateManagerIn,
	            		centerPos,
	            		this.pieces,
	            		this.random,
	            		false,
	            		true
	            		);

	            this.pieces.forEach(piece -> {
	            	piece.move(0, 1, 0);
	            });

	            Vec3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
	            int xOffset = centerPos.getX() - structureCenter.getX();
	            int zOffset = centerPos.getZ() - structureCenter.getZ();
	            for(StructurePiece structurePiece : this.pieces){
	                structurePiece.move(xOffset, 0, zOffset);

	            }

	            this.calculateBoundingBox();
	        }
	    }
	 
	 
     


}
