package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockNoHeadPiston extends Block {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockNoHeadPiston()
    {
        super(Material.barrier);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setStepSound(soundTypePiston);
        this.setUnlocalizedName("noHeadPiston");
        this.setBlockUnbreakable();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, getFacingFromEntity(pos, placer));
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            EnumFacing enumfacing = iblockstate.getValue(FACING);

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                    case DOWN:
                        this.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                        break;
                    case UP:
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                        break;
                    case NORTH:
                        this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                        break;
                    case SOUTH:
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                        break;
                    case WEST:
                        this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                        break;
                    case EAST:
                        this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
                }
            }
        }
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    public static EnumFacing getFacing(int meta)
    {
        int i = meta & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entityIn)
    {
        if (MathHelper.abs((float)entityIn.posX - (float)clickedBlock.getX()) < 2.0F && MathHelper.abs((float)entityIn.posZ - (float)clickedBlock.getZ()) < 2.0F)
        {
            double d0 = entityIn.posY + (double)entityIn.getEyeHeight();

            if (d0 - (double)clickedBlock.getY() > 2.0D)
            {
                return EnumFacing.UP;
            }

            if ((double)clickedBlock.getY() - d0 > 0.0D)
            {
                return EnumFacing.DOWN;
            }
        }

        return entityIn.getHorizontalFacing().getOpposite();
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();

        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING);
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
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.UP), 3);
                break;
            case UP:
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.DOWN), 3);
                break;
            case DOWN:
                worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.NORTH), 3);
                break;
        }
    }
}
