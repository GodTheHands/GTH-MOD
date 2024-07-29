package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class BlockLockedStair2 extends Block {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyEnum<EnumHalf> HALF = PropertyEnum.create("half", EnumHalf.class);
    public static final PropertyEnum<EnumShape> SHAPE = PropertyEnum.create("shape", EnumShape.class);
    private static final int[][] field_150150_a = new int[][] {{4, 5}, {5, 7}, {6, 7}, {4, 6}, {0, 1}, {1, 3}, {2, 3}, {0, 2}};
    private final Block modelBlock;
    private final IBlockState modelState;
    private boolean hasRaytraced;
    private int rayTracePass;
    private static final int state = 3;

    public BlockLockedStair2()
    {
        super(Material.wood);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, EnumHalf.BOTTOM));
        this.modelBlock = new BlockStoneBrick().getDefaultState().getBlock();
        this.modelState = new BlockStoneBrick().getDefaultState();
        this.setBlockUnbreakable();
        this.setStepSound(this.modelBlock.stepSound);
        this.setLightOpacity(255);
        this.setUnlocalizedName("lockedStair2");
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        if (this.hasRaytraced)
        {
            this.setBlockBounds(0.5F * (float)(this.rayTracePass % 2), 0.5F * (float)(this.rayTracePass / 4 % 2), 0.5F * (float)(this.rayTracePass / 2 % 2), 0.5F + 0.5F * (float)(this.rayTracePass % 2), 0.5F + 0.5F * (float)(this.rayTracePass / 4 % 2), 0.5F + 0.5F * (float)(this.rayTracePass / 2 % 2));
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        if ( isOpaqueCube() )
            return true;
        // face is on the block being rendered, not this block.
        IBlockState iblockstate = world.getBlockState(pos);
        EnumHalf half = iblockstate.getValue(HALF);
        EnumFacing side = iblockstate.getValue(FACING);
        return side == face.getOpposite() || (half == EnumHalf.TOP && face == EnumFacing.DOWN) || (half == EnumHalf.BOTTOM && face == EnumFacing.UP);
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    public void setBaseCollisionBounds(IBlockAccess worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getValue(HALF) == EnumHalf.TOP)
        {
            this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    public boolean judgeInnerAndOuter(IBlockAccess blockAccess, BlockPos pos)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos);
        EnumFacing enumfacing = iblockstate.getValue(FACING);
        EnumHalf enumhalf = iblockstate.getValue(HALF);
        boolean flag = enumhalf == EnumHalf.TOP;
        float f = 0.5F;
        float f1 = 1.0F;

        if (flag)
        {
            f = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 1.0F;
        float f4 = 0.0F;
        float f5 = 0.5F;
        boolean flag1 = true;

        if (enumfacing == EnumFacing.EAST)
        {
            f2 = 0.5F;
            f5 = 1.0F;
            if ((flag && state == 4) || (!flag && state == 3))
            {
                f5 = 0.5F;
                flag1 = false;
            }
            else if ((!flag && state == 4) || (flag && state == 3))
            {
                f4 = 0.5F;
                flag1 = false;
            }
        }
        else if (enumfacing == EnumFacing.WEST)
        {
            f3 = 0.5F;
            f5 = 1.0F;
            if ((!flag && state == 4) || (flag && state == 3))
            {
                f5 = 0.5F;
                flag1 = false;
            }
            else if ((flag && state == 4) || (!flag && state == 3))
            {
                f4 = 0.5F;
                flag1 = false;
            }
        }
        else if (enumfacing == EnumFacing.SOUTH)
        {
            f4 = 0.5F;
            f5 = 1.0F;
            if ((!flag && state == 4) || (flag && state == 3))
            {
                f3 = 0.5F;
                flag1 = false;
            }
            else if ((flag && state == 4) || (!flag && state == 3))
            {
                f2 = 0.5F;
                flag1 = false;
            }
        }
        else if (enumfacing == EnumFacing.NORTH)
        {
            if ((flag && state == 4) || (!flag && state == 3))
            {
                f3 = 0.5F;
                flag1 = false;
            }
            else if ((!flag && state == 4) || (flag && state == 3))
            {
                f2 = 0.5F;
                flag1 = false;
            }
        }

        this.setBlockBounds(f2, f, f4, f3, f1, f5);

        return flag1;
    }

    public boolean judgeOuterAndInner(IBlockAccess blockAccess, BlockPos pos)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos);
        EnumFacing enumfacing = iblockstate.getValue(FACING);
        EnumHalf enumhalf = iblockstate.getValue(HALF);
        boolean flag = enumhalf == EnumHalf.TOP;
        float f = 0.5F;
        float f1 = 1.0F;

        if (flag)
        {
            f = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = 0.5F;
        float f5 = 1.0F;
        boolean flag1 = false;

        if (enumfacing == EnumFacing.EAST)
        {
            if ((flag && state == 2) || (!flag && state == 1))
            {
                f4 = 0.0F;
                f5 = 0.5F;
                flag1 = true;
            }
            else if ((!flag && state == 2) || (flag && state == 1))
            {
                f4 = 0.5F;
                f5 = 1.0F;
                flag1 = true;
            }
        }
        else if (enumfacing == EnumFacing.WEST)
        {
            f2 = 0.5F;
            f3 = 1.0F;
            if ((!flag && state == 2) || (flag && state == 1))
            {
                f4 = 0.0F;
                f5 = 0.5F;
                flag1 = true;
            }
            else if ((flag && state == 2) || (!flag && state == 1))
            {
                f4 = 0.5F;
                f5 = 1.0F;
                flag1 = true;
            }
        }
        else if (enumfacing == EnumFacing.SOUTH)
        {
            f4 = 0.0F;
            f5 = 0.5F;
            if ((!flag && state == 2) || (flag && state == 1))
            {
                flag1 = true;
            }
            else if ((flag && state == 2) || (!flag && state == 1))
            {
                f2 = 0.5F;
                f3 = 1.0F;
                flag1 = true;
            }
        }
        else if (enumfacing == EnumFacing.NORTH)
        {
            if ((flag && state == 2) || (!flag && state == 1))
            {
                flag1 = true;
            }
            else if ((!flag && state == 2) || (flag && state == 1))
            {
                f2 = 0.5F;
                f3 = 1.0F;
                flag1 = true;
            }
        }

        if (flag1)
        {
            this.setBlockBounds(f2, f, f4, f3, f1, f5);
        }

        return flag1;
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity)
    {
        this.setBaseCollisionBounds(worldIn, pos);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        boolean flag = this.judgeInnerAndOuter(worldIn, pos);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);

        if (flag && this.judgeOuterAndInner(worldIn, pos))
        {
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getMixedBrightnessForBlock(IBlockAccess worldIn, BlockPos pos)
    {
        return this.modelBlock.getMixedBrightnessForBlock(worldIn, pos);
    }

    @Override
    public Vec3 modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3 motion)
    {
        return this.modelBlock.modifyAcceleration(worldIn, pos, entityIn, motion);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return this.modelBlock.getBlockLayer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        return this.modelBlock.getSelectedBoundingBox(worldIn, pos);
    }

    @Override
    public boolean isCollidable()
    {
        return this.modelBlock.isCollidable();
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return this.modelBlock.canCollideCheck(state, hitIfLiquid);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn)
    {
        this.modelBlock.onEntityCollidedWithBlock(worldIn, pos, entityIn);
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return this.modelBlock.getMapColor(this.modelState);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing()).withProperty(SHAPE, EnumShape.STRAIGHT);
        return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double)hitY <= 0.5D) ? iblockstate.withProperty(HALF, EnumHalf.BOTTOM) : iblockstate.withProperty(HALF, EnumHalf.TOP);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end)
    {
        MovingObjectPosition[] amovingobjectposition = new MovingObjectPosition[8];
        IBlockState iblockstate = worldIn.getBlockState(pos);
        int i = iblockstate.getValue(FACING).getHorizontalIndex();
        boolean flag = iblockstate.getValue(HALF) == EnumHalf.TOP;
        int[] aint = field_150150_a[i + (flag?4:0)];
        this.hasRaytraced = true;

        for (int j = 0; j < 8; ++j)
        {
            this.rayTracePass = j;

            if (Arrays.binarySearch(aint, j) < 0)
            {
                amovingobjectposition[j] = super.collisionRayTrace(worldIn, pos, start, end);
            }
        }

        for (int k : aint)
        {
            amovingobjectposition[k] = null;
        }

        MovingObjectPosition movingobjectposition1 = null;
        double d1 = 0.0D;

        for (MovingObjectPosition movingobjectposition : amovingobjectposition)
        {
            if (movingobjectposition != null)
            {
                double d0 = movingobjectposition.hitVec.squareDistanceTo(end);

                if (d0 > d1)
                {
                    movingobjectposition1 = movingobjectposition;
                    d1 = d0;
                }
            }
        }

        return movingobjectposition1;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(HALF, (meta & 4) > 0 ? EnumHalf.TOP : EnumHalf.BOTTOM);
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(5 - (meta & 3)));
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(HALF) == EnumHalf.TOP)
        {
            i |= 4;
        }

        i = i | 5 - state.getValue(FACING).getIndex();
        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, HALF, SHAPE);
    }

    public enum EnumHalf implements IStringSerializable
    {
        TOP("top"),
        BOTTOM("bottom");

        private final String name;

        EnumHalf(String name)
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

    public enum EnumShape implements IStringSerializable
    {
        STRAIGHT("straight"),
        INNER_LEFT("inner_left"),
        INNER_RIGHT("inner_right"),
        OUTER_LEFT("outer_left"),
        OUTER_RIGHT("outer_right");

        private final String name;

        EnumShape(String name)
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
        else {
            return false;
        }
    }

    private void changePosition(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = this.getMetaFromState(state);

        if (i < 7) {
            worldIn.setBlockState(pos, getStateFromMeta(i + 1), 3);
        }
        else {
            worldIn.setBlockState(pos, getStateFromMeta(0), 3);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        this.judgeInnerAndOuter(worldIn, pos);

        switch (this.state) {
            case 0:
                state = state.withProperty(SHAPE, EnumShape.STRAIGHT);
                break;
            case 1:
                state = state.withProperty(SHAPE, EnumShape.INNER_LEFT);
                break;
            case 2:
                state = state.withProperty(SHAPE, EnumShape.INNER_RIGHT);
                break;
            case 3:
                state = state.withProperty(SHAPE, EnumShape.OUTER_LEFT);
                break;
            case 4:
                state = state.withProperty(SHAPE, EnumShape.OUTER_RIGHT);
                break;
        };

        return state;
    }
}