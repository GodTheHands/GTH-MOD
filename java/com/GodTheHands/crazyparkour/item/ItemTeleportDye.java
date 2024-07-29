package com.GodTheHands.crazyparkour.item;

import com.GodTheHands.crazyparkour.common.EventLoader;
import com.GodTheHands.crazyparkour.creativetab.CreativeTabsLoader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemTeleportDye extends Item {
    public ItemTeleportDye() {
        super();
        this.setUnlocalizedName("teleportDye");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabsLoader.tabCrazyParkour);
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        EventLoader.PlayerTPEvent TPEvent;
        TPEvent = new EventLoader.PlayerTPEvent(playerIn);
        EventLoader.EVENT_BUS.post(TPEvent);
        return itemStackIn;
    }

    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) { return false; }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) { return true; }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        EventLoader.PlayerTPEvent TPEvent;
        TPEvent = new EventLoader.PlayerTPEvent(player);
        EventLoader.EVENT_BUS.post(TPEvent);
        return false;
    }
}
