package com.GodTheHands.crazyparkour.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLockedFlowerPot extends Block {

    public BlockLockedFlowerPot()
    {
        super(Material.circuits);
        this.setDefaultState(this.blockState.getBaseState());
        this.setBlockBoundsForItemRender();
        this.setUnlocalizedName("lockedFlowerPot");
        this.setBlockUnbreakable();
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        float f = 0.375F;
        float f1 = f / 2.0F;
        this.setBlockBounds(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, f, 0.5F + f1);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
}
