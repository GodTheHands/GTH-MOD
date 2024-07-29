package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedCobblestoneWall extends Block {
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

    public BlockLockedCobblestoneWall() {
        super(Material.ground);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.FALSE).withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
        this.setUnlocalizedName("lockedCobblestoneWall");
        this.setBlockUnbreakable();
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getBlock() == BlockLoader.lockedCobblestoneWall) {
            boolean flag = worldIn.getBlockState(pos).getValue(NORTH);
            boolean flag1 = worldIn.getBlockState(pos).getValue(SOUTH);
            boolean flag2 = worldIn.getBlockState(pos).getValue(WEST);
            boolean flag3 = worldIn.getBlockState(pos).getValue(EAST);
            float f = 0.25F;
            float f1 = 0.75F;
            float f2 = 0.25F;
            float f3 = 0.75F;
            float f4 = 1.0F;

            if (flag) {
                f2 = 0.0F;
            }

            if (flag1) {
                f3 = 1.0F;
            }

            if (flag2) {
                f = 0.0F;
            }

            if (flag3) {
                f1 = 1.0F;
            }

            if (flag && flag1 && !flag2 && !flag3) {
                f4 = 0.8125F;
                f = 0.3125F;
                f1 = 0.6875F;
            } else if (!flag && !flag1 && flag2 && flag3) {
                f4 = 0.8125F;
                f2 = 0.3125F;
                f3 = 0.6875F;
            }

            this.setBlockBounds(f, 0.0F, f2, f1, f4, f3);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        this.maxY = 1.5D;
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        return block != Blocks.barrier && (block == this || block instanceof BlockFenceGate || ((block.getMaterial().isOpaque() && block.isFullCube()) && block.getMaterial() != Material.gourd));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return side != EnumFacing.DOWN || super.shouldSideBeRendered(worldIn, pos, side);
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, UP, NORTH, EAST, WEST, SOUTH);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase Player) {
        return this.getDefaultState().withProperty(UP, !worldIn.isAirBlock(pos.up())).withProperty(NORTH, this.canConnectTo(worldIn, pos.north())).withProperty(EAST, this.canConnectTo(worldIn, pos.east())).withProperty(SOUTH, this.canConnectTo(worldIn, pos.south())).withProperty(WEST, this.canConnectTo(worldIn, pos.west()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null)
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

        if (i < 31) {
            worldIn.setBlockState(pos, getStateFromMeta(i + 1), 3);
        }
        else {
            worldIn.setBlockState(pos, getStateFromMeta(0), 3);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(SOUTH))
        {
            i |= 1;
        }

        if (state.getValue(WEST))
        {
            i |= 2;
        }

        if (state.getValue(NORTH))
        {
            i |= 4;
        }

        if (state.getValue(EAST))
        {
            i |= 8;
        }

        if (state.getValue(UP))
        {
            i |= 16;
        }

        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SOUTH, (meta & 1) > 0).withProperty(WEST, (meta & 2) > 0).withProperty(NORTH, (meta & 4) > 0).withProperty(EAST, (meta & 8) > 0).withProperty(UP, (meta & 16) > 0);
    }
}