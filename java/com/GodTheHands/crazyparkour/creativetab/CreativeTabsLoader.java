package com.GodTheHands.crazyparkour.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader
{
    public static CreativeTabs tabCrazyParkour;

    public CreativeTabsLoader(FMLPreInitializationEvent event)
    {
        tabCrazyParkour = new CreativeTabsCrazyParkour();
    }
}