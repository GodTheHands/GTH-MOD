package com.GodTheHands.crazyparkour.block.BlockTeleporter;

import com.GodTheHands.crazyparkour.capability.CapabilityLoader;
import com.GodTheHands.crazyparkour.capability.IPositionHistory;
import com.GodTheHands.crazyparkour.capability.IPositionStorage;
import com.GodTheHands.crazyparkour.creativetab.CreativeTabsLoader;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTeleporter extends BlockContainer {
    public static BlockPos collPos = new BlockPos(0, 0, 0);

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBlockTeleporter();
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    public BlockTeleporter()
    {
        super(Material.barrier);
        this.setUnlocalizedName("blockTeleporter");
        this.setBlockUnbreakable();
        this.setCreativeTab(CreativeTabsLoader.tabCrazyParkour);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.0625F, 0.9375F);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean isFullCube() {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.375F, 0.0F, 1.0F, 0.625F, 1.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.capabilities.isCreativeMode) {
            Minecraft.getMinecraft().displayGuiScreen(new TeleporterGUI(worldIn, pos, state));
        }
        return true;
    }

    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.isRemote)
        {
            if (pos.getX() == collPos.getX() && pos.getY() == collPos.getY() && pos.getZ() == collPos.getZ()) {
                return;
            }

            collPos = pos;

            if (worldIn.getTileEntity(pos) == null || entityIn == Minecraft.getMinecraft().thePlayer) {
                return;
            }

            TileEntityBlockTeleporter tebt = (TileEntityBlockTeleporter)worldIn.getTileEntity(pos);
            IPositionStorage IPS = tebt.getCapability(CapabilityLoader.positionStorage, null);
            IPositionHistory IPH = Minecraft.getMinecraft().thePlayer.getCapability(CapabilityLoader.positionHistory, null);
            IPH.setPosition(new Vec3(Double.parseDouble(IPS.getX()), Double.parseDouble(IPS.getY()), Double.parseDouble(IPS.getZ())));
            IPH.setRotationYaw(Float.parseFloat(IPS.getYaw()));
            IPH.setRotationPitch(Float.parseFloat(IPS.getPitch()));

            entityIn.worldObj.playSoundAtEntity(entityIn, "crazyparkour:setteleporter.test", 1.0F, 1.0F);
            entityIn.addChatMessage(new ChatComponentTranslation("commands.scp2.success"));
        }
    }
}
