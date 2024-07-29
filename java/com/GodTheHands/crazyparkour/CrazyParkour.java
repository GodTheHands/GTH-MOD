package com.GodTheHands.crazyparkour;

import com.GodTheHands.crazyparkour.common.CommonProxy;
import com.GodTheHands.crazyparkour.hud.FileManager;
import com.GodTheHands.crazyparkour.hud.HUDManager;
import com.GodTheHands.crazyparkour.hud.ScreenInstances;
import com.GodTheHands.crazyparkour.macro.MacroFileManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = CrazyParkour.MODID, name = CrazyParkour.NAME, version = CrazyParkour.VERSION, acceptedMinecraftVersions = "1.8.9", guiFactory = "com.GodTheHands.crazyparkour.inventory.ConfigGui")
public class CrazyParkour
{
    public static HUDManager hudManager;

    public static final String MODID = "crazyparkour";
    public static final String NAME = "GTH MOD";
    public static final String VERSION = "1.0.8";

    @SidedProxy(clientSide = "com.GodTheHands.crazyparkour.client.ClientProxy",
            serverSide = "com.GodTheHands.crazyparkour.common.CommonProxy")
    public static CommonProxy proxy;

    @Instance(CrazyParkour.MODID)
    public static CrazyParkour instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FileManager.init();
        MacroFileManager.init();
        hudManager = HUDManager.getInstance();
        ScreenInstances.register(hudManager);

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        proxy.serverStarting(event);
    }
}