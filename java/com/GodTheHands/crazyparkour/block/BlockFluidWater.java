package com.GodTheHands.crazyparkour.block;

import com.GodTheHands.crazyparkour.fluid.FluidLoader;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.Random;

public class BlockFluidWater extends BlockFluidClassic {
    public BlockFluidWater()
    {
        super(FluidLoader.fluidWater, Material.water);
        this.setUnlocalizedName("fluidWater");
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {}

    @Override
    public Vec3 getFlowVector(IBlockAccess world, BlockPos pos)
    {
        return new Vec3(0.0D, 0.0D, 0.0D);
    }
}
