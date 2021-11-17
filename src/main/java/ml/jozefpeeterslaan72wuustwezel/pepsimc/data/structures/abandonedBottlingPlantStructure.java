package ml.jozefpeeterslaan72wuustwezel.pepsimc.data.structures;

import org.apache.logging.log4j.LogManager;


import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class abandonedBottlingPlantStructure extends Structure<NoFeatureConfig>{

	public abandonedBottlingPlantStructure() {
		super(NoFeatureConfig.CODEC);
	}

	@Override
	public GenerationStage.Decoration step() {
	      return GenerationStage.Decoration.TOP_LAYER_MODIFICATION;
	   }
	
	@Override
	protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider p_230363_2_, long p_230363_3_, SharedSeedRandom p_230363_5_, int chunkX, int chunkZ, Biome p_230363_8_, ChunkPos p_230363_9_, NoFeatureConfig p_230363_10_) {
		
		BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
		
        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(),Heightmap.Type.WORLD_SURFACE_WG);

        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
        
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));

        return topBlock.getFluidState().isEmpty();
	   }
	
	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
    	LogManager.getLogger().info("start");

		return abandonedBottlingPlantStructure.Start::new;
		
	}

	
	 public static class Start extends StructureStart<NoFeatureConfig> {
	        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ,
	                     MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
	            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
	        }

	        @Override // generatePieces
	        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator,
	                                   TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn,
	                                   NoFeatureConfig config) {
	            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
	        	 int x = chunkX * 16;
	             int z = chunkZ * 16;

	            BlockPos centerPos = new BlockPos(x, 0, z);
	            
	            JigsawManager.addPieces(dynamicRegistryManager, 
	            		new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
	            				.get(new ResourceLocation("pepsimc", "abandoned_bottling_plant/start_pool")),
	            				10), 
	            		AbstractVillagePiece::new,
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

	            Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
	            int xOffset = centerPos.getX() - structureCenter.getX();
	            int zOffset = centerPos.getZ() - structureCenter.getZ();
	            for(StructurePiece structurePiece : this.pieces){
	                structurePiece.move(xOffset, 0, zOffset);

	            }

	            this.calculateBoundingBox();
	        }
	    }
	 
	 
     


}
