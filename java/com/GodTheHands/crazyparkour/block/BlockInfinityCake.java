package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfinityCake extends Block {
    public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);

    protected BlockInfinityCake()
    {
        super(Material.cake);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, 0));
        this.setUnlocalizedName("infinityCake");
        this.setBlockUnbreakable();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        float f = 0.0625F;
        float f1 = (float)(1 + (worldIn.getBlockState(pos).getValue(BITES)) * 2) / 16.0F;
        float f2 = 0.5F;
        this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        float f = 0.0625F;
        float f1 = 0.5F;
        this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        float f = 0.0625F;
        float f1 = (float)(1 + (state.getValue(BITES)) * 2) / 16.0F;
        float f2 = 0.5F;
        return new AxisAlignedBB(((float)pos.getX() + f1), pos.getY(), ((float)pos.getZ() + f), ((float)(pos.getX() + 1) - f), ((float)pos.getY() + f2), ((float)(pos.getZ() + 1) - f));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        return this.getCollisionBoundingBox(worldIn, pos, worldIn.getBlockState(pos));
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null)
            {
                worldIn.setBlockState(pos, this.getStateFromMeta((this.getMetaFromState(state) + 1) % 7));
                return true;
            }
        else {
            return false;
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BITES, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Items.cake;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(BITES));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, BITES);
    }
}
