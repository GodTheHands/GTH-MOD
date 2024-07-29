package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedBed extends BlockDirectional {
    public static final PropertyEnum<EnumPartType> PART = PropertyEnum.create("part", EnumPartType.class);

    public BlockLockedBed()
    {
        super(Material.barrier);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PART, EnumPartType.FOOT));
        this.setBedBounds();
        this.setUnlocalizedName("lockedBed");
        this.setBlockUnbreakable();
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
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        this.setBedBounds();
    }

    private void setBedBounds()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Items.bed;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, EnumPartType.HEAD).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(PART, EnumPartType.FOOT).withProperty(FACING, enumfacing);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();

        if (state.getValue(PART) == EnumPartType.HEAD)
        {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, PART);
    }

    public enum EnumPartType implements IStringSerializable
    {
        HEAD("head"),
        FOOT("foot");

        private final String name;

        EnumPartType(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null && !playerIn.isSneaking()) {
            this.changePosition(worldIn, pos, worldIn.getBlockState(pos));
            return true;
        }
        else if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null && playerIn.isSneaking())
        {
            this.changeState(worldIn, pos, worldIn.getBlockState(pos));
            return true;
        }
        else {
            return false;
        }
    }

    private void changeState(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumPartType part = state.getValue(PART);

        switch (part) {
            case HEAD:
                worldIn.setBlockState(pos, state.withProperty(PART, EnumPartType.FOOT), 3);
                break;
            case FOOT:
                worldIn.setBlockState(pos, state.withProperty(PART, EnumPartType.HEAD), 3);
                break;
        }
    }

    private void changePosition(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumFacing currentFacing = state.getValue(FACING);

        switch (currentFacing) {
            case NORTH:
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.EAST), 3);
                break;
            case EAST:
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.SOUTH), 3);
                break;
            case SOUTH:
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.WEST), 3);
                break;
            case WEST:
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.NORTH), 3);
                break;
        }
    }
}
