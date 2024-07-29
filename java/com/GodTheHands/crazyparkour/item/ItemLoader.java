package com.GodTheHands.crazyparkour.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
    public static Item teleportDye = new ItemTeleportDye();
    public static Item checkpointDye = new ItemCheckpointDye();
    public static Item parkourCollector = new ItemParkourCollector();
    public static Item gamemodeChanger = new ItemGamemodeChanger();
    public static Item bucketWater = new ItemWaterBucket();
    public static Item bucketLava = new ItemLavaBucket();

    public ItemLoader(FMLPreInitializationEvent event)
    {
        register(teleportDye, "teleport_dye");
        register(checkpointDye, "checkpoint_dye");
        register(parkourCollector, "parkour_collector");
        register(gamemodeChanger, "gamemode_changer");
        register(bucketWater, "bucket_water");
        register(bucketLava, "bucket_lava");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(teleportDye);
        registerRender(checkpointDye);
        registerRender(parkourCollector);
        registerRender(gamemodeChanger);
        registerRender(bucketWater);
        registerRender(bucketLava);
    }

    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item.setRegistryName(name));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
}
