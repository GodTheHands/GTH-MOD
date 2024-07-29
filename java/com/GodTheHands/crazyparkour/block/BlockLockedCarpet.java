package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedCarpet extends Block {
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    protected BlockLockedCarpet()
    {
        super(Material.carpet);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.setBlockBoundsFromMeta();

        this.setUnlocalizedName("lockedCarpet");
        this.setBlockUnbreakable();
    }

    @Override
    public MapColor getMapColor(IBlockState state) { return state.getValue(COLOR).getMapColor();}

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
        this.setBlockBoundsFromMeta();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        this.setBlockBoundsFromMeta();
    }

    protected void setBlockBoundsFromMeta()
    {
        int i = 0;
        float f = (float)((1 + i)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
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
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) { return state.getValue(COLOR).getMetadata();}

    @Override
    protected BlockState createBlockState() { return new BlockState(this, COLOR);}

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.capabilities.isCreativeMode && playerIn.inventory.getCurrentItem() == null)
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
        int i = state.getValue(COLOR).getMetadata();

        if (i < 15) {
            worldIn.setBlockState(pos, state.withProperty(COLOR, EnumDyeColor.byMetadata(i + 1)), 3);
        }
        else {
            worldIn.setBlockState(pos, state.withProperty(COLOR, EnumDyeColor.byMetadata(0)), 3);
        }
    }
}
