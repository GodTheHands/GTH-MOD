package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockLockedCocoa extends BlockDirectional {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);

    public BlockLockedCocoa()
    {
        super(Material.plants);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, 0));
        this.setUnlocalizedName("lockedCocoa");
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
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if (iblockstate.getBlock() == this) {
            EnumFacing enumfacing = iblockstate.getValue(FACING);
            int i = iblockstate.getValue(AGE);
            int j = 4 + i * 2;
            int k = 5 + i * 2;
            float f = (float)j / 2.0F;

            switch (enumfacing)
            {
                case SOUTH:
                    this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)k) / 16.0F, (15.0F - (float)j) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
                    break;
                case NORTH:
                    this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)k) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + (float)j) / 16.0F);
                    break;
                case WEST:
                    this.setBlockBounds(0.0625F, (12.0F - (float)k) / 16.0F, (8.0F - f) / 16.0F, (1.0F + (float)j) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
                    break;
                case EAST:
                    this.setBlockBounds((15.0F - (float)j) / 16.0F, (12.0F - (float)k) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
        worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (!facing.getAxis().isHorizontal())
        {
            facing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(AGE, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getSelectedBoundingBox(worldIn, pos);
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
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(AGE, (meta & 15) >> 2);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();
        i = i | state.getValue(AGE) << 2;
        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, AGE);
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
            this.changeTier(worldIn, pos, worldIn.getBlockState(pos));
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

    private void changeTier(World worldIn, BlockPos pos, IBlockState state) {
        int i = state.getValue(AGE);

        if (i < 2) {
            worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 3);
        }
        else {
            worldIn.setBlockState(pos, state.withProperty(AGE, 0), 3);
        }
    }
}
