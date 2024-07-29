package com.GodTheHands.crazyparkour.common;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.block.BlockTeleporter.TileEntityLoader;
import com.GodTheHands.crazyparkour.capability.CapabilityLoader;
import com.GodTheHands.crazyparkour.command.CommandLoader;
import com.GodTheHands.crazyparkour.fluid.FluidLoader;
import com.GodTheHands.crazyparkour.inventory.GuiElementLoader;
import com.GodTheHands.crazyparkour.item.ItemLoader;
import com.GodTheHands.crazyparkour.creativetab.CreativeTabsLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        new ConfigLoader(event);
        new CapabilityLoader(event);
        new CreativeTabsLoader(event);
        new FluidLoader(event);
        new ItemLoader(event);
        new BlockLoader(event);
        new TileEntityLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
        new EventLoader();
        new GuiElementLoader();
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

    public void serverStarting(FMLServerStartingEvent event)
    {
        new CommandLoader(event);
    }
}