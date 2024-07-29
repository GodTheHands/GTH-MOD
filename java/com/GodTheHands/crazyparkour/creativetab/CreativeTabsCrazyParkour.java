package com.GodTheHands.crazyparkour.creativetab;

import com.GodTheHands.crazyparkour.item.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsCrazyParkour extends CreativeTabs {
    public CreativeTabsCrazyParkour() { super("crazyparkour"); }

    @Override
    public Item getTabIconItem() { return ItemLoader.teleportDye; }
}
