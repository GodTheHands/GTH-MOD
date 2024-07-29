package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedFenceGate extends BlockDirectional {
    public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");

    public BlockLockedFenceGate()
    {
        super(Material.wood, BlockPlanks.EnumType.OAK.getMapColor());
        this.setDefaultState(this.blockState.getBaseState().withProperty(IN_WALL, Boolean.FALSE));
        this.setUnlocalizedName("lockedFenceGate");
        this.setBlockUnbreakable();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing.Axis enumfacing$axis = state.getValue(FACING).getAxis();

        if (enumfacing$axis == EnumFacing.Axis.Z && (worldIn.getBlockState(pos.west()).getBlock() == Blocks.cobblestone_wall || worldIn.getBlockState(pos.east()).getBlock() == Blocks.cobblestone_wall) || enumfacing$axis == EnumFacing.Axis.X && (worldIn.getBlockState(pos.north()).getBlock() == Blocks.cobblestone_wall || worldIn.getBlockState(pos.south()).getBlock() == Blocks.cobblestone_wall))
        {
            state = state.withProperty(IN_WALL, Boolean.TRUE);
        }

        return state;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumFacing.Axis enumfacing$axis = state.getValue(FACING).getAxis();
        return enumfacing$axis == EnumFacing.Axis.Z ? new AxisAlignedBB(pos.getX(), pos.getY(), (float)pos.getZ() + 0.375F, pos.getX() + 1, (float)pos.getY() + 1.5F, (float)pos.getZ() + 0.625F) : new AxisAlignedBB((float)pos.getX() + 0.375F, pos.getY(), pos.getZ(), (float)pos.getX() + 0.625F, (float)pos.getY() + 1.5F, pos.getZ() + 1);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing.Axis enumfacing$axis = worldIn.getBlockState(pos).getValue(FACING).getAxis();

        if (enumfacing$axis == EnumFacing.Axis.Z)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
        }
        else
        {
            this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
        }
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
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) { return false; }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(IN_WALL, Boolean.FALSE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();

        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, IN_WALL);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null)
        {
            worldIn.setBlockState(pos, getStateFromMeta((getMetaFromState(state) + 1) % 4 + 2));
            return true;
        }
        else {
            return false;
        }
    }
}
