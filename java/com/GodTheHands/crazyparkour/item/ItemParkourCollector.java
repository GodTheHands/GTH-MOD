package com.GodTheHands.crazyparkour.item;

import com.GodTheHands.crazyparkour.CrazyParkour;
import com.GodTheHands.crazyparkour.creativetab.CreativeTabsLoader;
import com.GodTheHands.crazyparkour.inventory.GuiElementLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemParkourCollector extends Item {
    public ItemParkourCollector() {
        super();
        this.setUnlocalizedName("parkourCollector");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabsLoader.tabCrazyParkour);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        if (!worldIn.isRemote) {
            playerIn.worldObj.playSoundAtEntity(playerIn, "crazyparkour:parkourcollector.test", 1.0F, 1.0F);
            BlockPos pos = playerIn.getPosition();
            int id = GuiElementLoader.THINGS;
            playerIn.openGui(CrazyParkour.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return itemStackIn;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) { return false; }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) { return true; }
}
