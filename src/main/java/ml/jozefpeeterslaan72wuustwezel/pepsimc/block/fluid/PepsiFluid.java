package ml.jozefpeeterslaan72wuustwezel.pepsimc.block.fluid;

import java.util.Random;

import javax.annotation.Nullable;

import ml.jozefpeeterslaan72wuustwezel.pepsimc.block.PepsiMcBlock;
import ml.jozefpeeterslaan72wuustwezel.pepsimc.item.PepsiMcItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class PepsiFluid extends FlowingFluid {
	   public Fluid getFlowing() {
	      return PepsiMcFluid.PEPSI_FLOW.get();
	   }

	   public Fluid getSource() {
	      return PepsiMcFluid.PEPSI_FLUID.get();
	   }

	   public Item getBucket() {
	      return PepsiMcItem.PEPSI_FLUID_BUCKET.get();
	   }

	   @OnlyIn(Dist.CLIENT)
	   public void animateTick(World world, BlockPos pos, FluidState state, Random rand) {
	      if (!state.isSource() && !state.getValue(FALLING)) {
	         if (rand.nextInt(64) == 0) {
	        	 world.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
	         }
	      } else if (rand.nextInt(10) == 0) {
	    	  world.addParticle(ParticleTypes.UNDERWATER, (double)pos.getX() + rand.nextDouble(), (double)pos.getY() + rand.nextDouble(), (double)pos.getZ() + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
	      }

	   }

	   @Nullable
	   @OnlyIn(Dist.CLIENT)
	   public IParticleData getDripParticle() {
	      return ParticleTypes.DRIPPING_WATER;
	   }

	   protected boolean canConvertToSource() {
	      return true;
	   }

	   protected void beforeDestroyingBlock(IWorld p_205580_1_, BlockPos p_205580_2_, BlockState p_205580_3_) {
	      TileEntity tileentity = p_205580_3_.hasTileEntity() ? p_205580_1_.getBlockEntity(p_205580_2_) : null;
	      Block.dropResources(p_205580_3_, p_205580_1_, p_205580_2_, tileentity);
	   }

	   public int getSlopeFindDistance(IWorldReader p_185698_1_) {
	      return 4;
	   }

	   public BlockState createLegacyBlock(FluidState p_204527_1_) {
	      return PepsiMcBlock.PEPSI_FLUID_BLOCK.get().defaultBlockState().setValue(FlowingFluidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_204527_1_)));
	   }

	   public boolean isSame(Fluid fluid) {
	      return fluid == PepsiMcFluid.PEPSI_FLUID.get() || fluid == PepsiMcFluid.PEPSI_FLOW.get();
	   }

	   public int getDropOff(IWorldReader p_204528_1_) {
	      return 1;
	   }

	   public int getTickDelay(IWorldReader p_205569_1_) {
	      return 5;
	   }

	   public boolean canBeReplacedWith(FluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
	      return p_215665_5_ == Direction.DOWN && !p_215665_4_.is(FluidTags.WATER);
	   }

	   protected float getExplosionResistance() {
	      return 100.0F;
	   }

	   public static class Flowing extends PepsiFluid {
	      protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> p_207184_1_) {
	         super.createFluidStateDefinition(p_207184_1_);
	         p_207184_1_.add(LEVEL);
	      }

	      public int getAmount(FluidState state) {
	         return state.getValue(LEVEL);
	      }

	      public boolean isSource(FluidState p_207193_1_) {
	         return false;
	      }
	   }

	   public static class Source extends PepsiFluid {
	      public int getAmount(FluidState p_207192_1_) {
	         return 8;
	      }

	      public boolean isSource(FluidState p_207193_1_) {
	         return true;
	      }
	   }
	}
