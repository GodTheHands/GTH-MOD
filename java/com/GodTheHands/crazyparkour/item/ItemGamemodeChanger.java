package com.GodTheHands.crazyparkour.item;

import com.GodTheHands.crazyparkour.common.EventLoader;
import com.GodTheHands.crazyparkour.creativetab.CreativeTabsLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGamemodeChanger extends Item {
    public ItemGamemodeChanger() {
        super();
        setUnlocalizedName("gamemodeChanger");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabsLoader.tabCrazyParkour);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        EventLoader.PlayerChangeModeEvent CMEvent;
        CMEvent = new EventLoader.PlayerChangeModeEvent(playerIn);
        EventLoader.EVENT_BUS.post(CMEvent);
        return itemStackIn;
    }
}
