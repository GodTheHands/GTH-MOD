package com.GodTheHands.crazyparkour.block;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockLockedGlassPane extends Block {
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    public BlockLockedGlassPane()
    {
        super(Material.glass);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE).withProperty(COLOR, EnumDyeColor.WHITE));

        this.setUnlocalizedName("lockedGlassPane");
        this.setBlockUnbreakable();
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(COLOR).getMapColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SOUTH, (meta & 1) > 0).withProperty(WEST, (meta & 2) > 0).withProperty(NORTH, (meta & 4) > 0).withProperty(EAST, (meta & 8) > 0);
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

        return i;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, NORTH, EAST, WEST, SOUTH, COLOR);
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
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return worldIn.getBlockState(pos).getBlock() != this && super.shouldSideBeRendered(worldIn, pos, side);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity)
    {
        boolean flag = state.getValue(NORTH);
        boolean flag1 = state.getValue(SOUTH);
        boolean flag2 = state.getValue(WEST);
        boolean flag3 = state.getValue(EAST);

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
            else if (flag3)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
        {
            if (flag)
            {
                this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
            else if (flag1)
            {
                this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
        }
        else
        {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag = worldIn.getBlockState(pos).getValue(NORTH);
        boolean flag1 = worldIn.getBlockState(pos).getValue(SOUTH);
        boolean flag2 = worldIn.getBlockState(pos).getValue(WEST);
        boolean flag3 = worldIn.getBlockState(pos).getValue(EAST);

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2)
            {
                f = 0.0F;
            }
            else if (flag3)
            {
                f1 = 1.0F;
            }
        }
        else
        {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
        {
            if (flag)
            {
                f2 = 0.0F;
            }
            else if (flag1)
            {
                f3 = 1.0F;
            }
        }
        else
        {
            f2 = 0.0F;
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public final boolean canPaneConnectToBlock(Block blockIn)
    {
        return blockIn.isFullBlock() || blockIn == this || blockIn == Blocks.glass || blockIn == Blocks.stained_glass || blockIn == Blocks.stained_glass_pane || blockIn instanceof BlockPane;
    }

    public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir)
    {
        BlockPos off = pos.offset(dir);
        Block block = world.getBlockState(off).getBlock();
        return canPaneConnectToBlock(block) || block.isSideSolid(world, off, dir.getOpposite());
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE).withProperty(NORTH, canPaneConnectTo(worldIn, pos, EnumFacing.NORTH)).withProperty(SOUTH, canPaneConnectTo(worldIn, pos, EnumFacing.SOUTH)).withProperty(WEST, canPaneConnectTo(worldIn, pos, EnumFacing.WEST)).withProperty(EAST, canPaneConnectTo(worldIn, pos, EnumFacing.EAST));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(COLOR, EnumDyeColor.byMetadata(ConfigLoader.colorForGlassPane));
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

        if (i < 15) {
            worldIn.setBlockState(pos, getStateFromMeta(i + 1), 3);
        }
        else {
            worldIn.setBlockState(pos, getStateFromMeta(0), 3);
        }
    }
}
