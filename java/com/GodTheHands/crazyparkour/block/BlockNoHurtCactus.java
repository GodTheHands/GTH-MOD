package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNoHurtCactus extends Block {
    protected BlockNoHurtCactus()
    {
        super(Material.cactus);
        this.setDefaultState(this.blockState.getBaseState());
        this.setUnlocalizedName("noHurtCactus");
        this.setBlockUnbreakable();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        float f = 0.0625F;
        return new AxisAlignedBB((float)pos.getX() + f, pos.getY(), (float)pos.getZ() + f, (float)(pos.getX() + 1) - f, (float)(pos.getY() + 1) - f, (float)(pos.getZ() + 1) - f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        float f = 0.0625F;
        return new AxisAlignedBB((float)pos.getX() + f, pos.getY(), (float)pos.getZ() + f, (float)(pos.getX() + 1) - f, pos.getY() + 1, (float)(pos.getZ() + 1) - f);
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this);
    }
}
