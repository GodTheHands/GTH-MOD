package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedTrapdoor extends Block {
    public static boolean disableValidation = false;
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyEnum<DoorHalf> HALF = PropertyEnum.create("half", DoorHalf.class);

    public BlockLockedTrapdoor()
    {
        super(Material.iron);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.FALSE).withProperty(HALF, DoorHalf.BOTTOM));
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

        this.setUnlocalizedName("lockedTrapdoor");
        this.setBlockUnbreakable();
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
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return !worldIn.getBlockState(pos).getValue(OPEN);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getSelectedBoundingBox(worldIn, pos);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        this.setBounds(worldIn.getBlockState(pos));
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.40625F, 0.0F, 1.0F, 0.59375F, 1.0F);
    }

    public void setBounds(IBlockState state)
    {
        if (state.getBlock() == this)
        {
            boolean flag = state.getValue(HALF) == DoorHalf.TOP;
            Boolean obool = state.getValue(OPEN);
            EnumFacing enumfacing = state.getValue(FACING);

            if (flag)
            {
                this.setBlockBounds(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
            }

            if (obool)
            {
                if (enumfacing == EnumFacing.NORTH)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 1.0F);
                }

                if (enumfacing == EnumFacing.SOUTH)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.1875F);
                }

                if (enumfacing == EnumFacing.WEST)
                {
                    this.setBlockBounds(0.8125F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }

                if (enumfacing == EnumFacing.EAST)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
                }
            }
        }
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.collisionRayTrace(worldIn, pos, start, end);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (facing.getAxis().isHorizontal())
        {
            iblockstate = iblockstate.withProperty(FACING, facing).withProperty(OPEN, Boolean.FALSE);
            iblockstate = iblockstate.withProperty(HALF, hitY > 0.5F ? DoorHalf.TOP : DoorHalf.BOTTOM);
        }

        return iblockstate;
    }

    protected static EnumFacing getFacing(int meta)
    {
        switch (meta & 3)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
            default:
                return EnumFacing.EAST;
        }
    }

    protected static int getMetaForFacing(EnumFacing facing)
    {
        switch (facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(OPEN, (meta & 4) != 0).withProperty(HALF, (meta & 8) == 0 ? DoorHalf.BOTTOM : DoorHalf.TOP);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | getMetaForFacing(state.getValue(FACING));

        if (state.getValue(OPEN))
        {
            i |= 4;
        }

        if (state.getValue(HALF) == DoorHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, OPEN, HALF);
    }

    public enum DoorHalf implements IStringSerializable
    {
        TOP("top"),
        BOTTOM("bottom");

        private final String name;

        DoorHalf(String name)
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
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null && !playerIn.isSneaking())
        {
            this.changePosition(worldIn, pos, worldIn.getBlockState(pos));
            return true;
        }
        else if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null && playerIn.isSneaking()) {
            worldIn.setBlockState(pos, state.withProperty(OPEN, !state.getValue(OPEN)), 3);
            return true;
        }
        else if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() != null && !playerIn.isSneaking()) {
            if (state.getValue(HALF) == DoorHalf.TOP) {
                worldIn.setBlockState(pos, state.withProperty(HALF, DoorHalf.BOTTOM), 3);
            }
            else {
                worldIn.setBlockState(pos, state.withProperty(HALF, DoorHalf.TOP), 3);
            }
            return true;
        }
        else {
            return false;
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
