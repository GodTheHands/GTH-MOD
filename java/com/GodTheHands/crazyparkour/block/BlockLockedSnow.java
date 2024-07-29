package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedSnow extends Block {
    public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

    public BlockLockedSnow()
    {
        super(Material.snow);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LAYERS, 2));
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setBlockBoundsForItemRender();
        this.setUnlocalizedName("lockedSnow");
        this.setBlockUnbreakable();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = state.getValue(LAYERS) - 1;
        float f = 0.125F;
        return new AxisAlignedBB((double)pos.getX() + this.minX, (double)pos.getY() + this.minY, (double)pos.getZ() + this.minZ, (double)pos.getX() + this.maxX, (float)pos.getY() + (float)i * f, (double)pos.getZ() + this.maxZ);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        this.getBoundsForLayers(0);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        this.getBoundsForLayers(iblockstate.getValue(LAYERS));
    }

    protected void getBoundsForLayers(int p_150154_1_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (float)p_150154_1_ / 8.0F, 1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP || super.shouldSideBeRendered(worldIn, pos, side);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LAYERS, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(LAYERS);
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, LAYERS);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null)
        {
            worldIn.setBlockState(pos, state.withProperty(LAYERS, 1 + (state.getValue(LAYERS)) % 8));
            return true;
        }
        else {
            return false;
        }
    }
}
