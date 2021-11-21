package ml.jozefpeeterslaan72wuustwezel.pepsimc.common.data.structures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
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
	protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource source, long p_160457_, WorldgenRandom random, ChunkPos pos, Biome p_160460_, ChunkPos p_160461_, C p_160462_, LevelHeightAccessor heightAccessor) {
		
		BlockPos centerOfChunk = new BlockPos((pos.x << 4) + 7, 0, (pos.z << 4) + 7);
		
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),Heightmap.Types.WORLD_SURFACE_WG,heightAccessor);

        NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(),heightAccessor);
        
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
	   }
	
	@Override
	public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return ExcavationSite.Start::new;
		
	}

	
	public static class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos pos, int referenceIn, long seedIn) {
            super(structureIn, pos, referenceIn, seedIn);
        }

        @Override // generatePieces
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator,
                                   StructureManager templateManagerIn, ChunkPos pos, Biome biomeIn,
                                   NoneFeatureConfiguration config,LevelHeightAccessor heightaccessor) {
            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)

        	BlockPos centerOfChunk = new BlockPos((pos.x << 4) + 7, 0, (pos.z << 4) + 7);
      
            
            JigsawPlacement.addPieces(dynamicRegistryManager, 
            		new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
            				.get(new ResourceLocation("pepsimc", "excavation_site/start_pool")),
            				10), 
            		JigsawPlacement.PieceFactory,
            		chunkGenerator,
            		templateManagerIn,
            		centerOfChunk,
            		this.pieces,
            		this.random,
            		false,
            		true,
            		heightaccessor
            		);

            this.pieces.forEach(piece -> {
            	piece.move(0, 1, 0);
            });

            Vec3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
            int xOffset = centerOfChunk.getX() - structureCenter.getX();
            int zOffset = centerOfChunk.getZ() - structureCenter.getZ();
            for(StructurePiece structurePiece : this.pieces){
                structurePiece.move(xOffset, 0, zOffset);

            }

            this.createBoundingBox();
        }


    }
	 
	 
     


}
