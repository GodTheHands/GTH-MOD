package com.GodTheHands.crazyparkour.item;

import com.GodTheHands.crazyparkour.common.EventLoader;
import com.GodTheHands.crazyparkour.creativetab.CreativeTabsLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemCheckpointDye extends Item{
    public ItemCheckpointDye() {
        super();
        this.setUnlocalizedName("checkpointDye");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabsLoader.tabCrazyParkour);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        EventLoader.PlayerCPEvent CPEvent;
        CPEvent = new EventLoader.PlayerCPEvent(playerIn);
        EventLoader.EVENT_BUS.post(CPEvent);
        return itemStackIn;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) { return false; }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        EventLoader.PlayerCPEvent CPEvent;
        CPEvent = new EventLoader.PlayerCPEvent(player);
        EventLoader.EVENT_BUS.post(CPEvent);
        return false;
    }
}
