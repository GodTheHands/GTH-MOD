package com.GodTheHands.crazyparkour.client;

import com.GodTheHands.crazyparkour.common.CommonProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        new ItemRenderLoader();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new KeyLoader();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}