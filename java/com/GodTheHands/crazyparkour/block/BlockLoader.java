package com.GodTheHands.crazyparkour.block;

import com.GodTheHands.crazyparkour.block.BlockTeleporter.BlockTeleporter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLoader {
    public static Block fluidWater = new BlockFluidWater();
    public static Block fluidLava = new BlockFluidLava();
    public static Block lockedBed = new BlockLockedBed();
    public static Block noHeadPiston = new BlockNoHeadPiston();
    public static Block pistonArm = new BlockPistonArm();
    public static Block lockedLadder = new BlockLockedLadder();
    public static Block noHurtCactus = new BlockNoHurtCactus();
    public static Block infinityCake = new BlockInfinityCake();
    public static Block lockedVine = new BlockLockedVine();
    public static Block lockedFenceGate = new BlockLockedFenceGate();
    public static Block lockedWaterlily = new BlockLockedWaterlily();
    public static Block lockedEnchantingTable = new BlockLockedEnchantingTable();
    public static Block lockedBrewingStand = new BlockLockedBrewingStand();
    public static Block lockedDragonEgg = new BlockLockedDragonEgg();
    public static Block lockedCocoa = new BlockLockedCocoa();
    public static Block lockedChest = new BlockLockedChest();
    public static Block lockedFlowerPot = new BlockLockedFlowerPot();
    public static Block lockedAnvil = new BlockLockedAnvil();
    public static Block lockedDaylightDetector = new BlockLockedDaylightDetector();
    public static Block lockedHopper = new BlockLockedHopper();
    public static Block lockedTrapdoor = new BlockLockedTrapdoor();
    public static Block lockedCarpet = new BlockLockedCarpet();
    public static Block lockedRepeater = new BlockLockedRepeater();
    public static Block lockedSnow = new BlockLockedSnow();
    public static Block lockedFence = new BlockLockedFence();
    public static Block lockedCauldron = new BlockLockedCauldron();
    public static Block lockedCobblestoneWall = new BlockLockedCobblestoneWall();
    public static Block lockedGlassPane = new BlockLockedGlassPane();
    public static Block lockedStair = new BlockLockedStair();
    public static Block lockedStair1 = new BlockLockedStair1();
    public static Block lockedStair2 = new BlockLockedStair2();
    public static Block blockTeleporter = new BlockTeleporter();

    public BlockLoader(FMLPreInitializationEvent event)
    {
        register(fluidWater, "fluid_water");
        register(fluidLava, "fluid_lava");
        register(lockedBed, "locked_bed");
        register(noHeadPiston, "no_head_piston");
        register(pistonArm, "piston_arm");
        register(lockedLadder, "locked_ladder");
        register(noHurtCactus, "no_hurt_cactus");
        register(infinityCake, "infinity_cake");
        register(lockedVine, "locked_vine");
        register(lockedFenceGate, "locked_fence_gate");
        register(lockedWaterlily, "locked_waterlily");
        register(lockedEnchantingTable, "locked_enchanting_table");
        register(lockedBrewingStand, "locked_brewing_stand");
        register(lockedDragonEgg, "locked_dragon_egg");
        register(lockedCocoa, "locked_cocoa");
        register(lockedChest, "locked_chest");
        register(lockedFlowerPot, "locked_flower_pot");
        register(lockedAnvil, "locked_anvil");
        register(lockedDaylightDetector, "locked_daylight_detector");
        register(lockedHopper, "locked_hopper");
        register(lockedTrapdoor, "locked_trapdoor");
        register(lockedCarpet, "locked_carpet");
        register(lockedRepeater, "locked_repeater");
        register(lockedSnow, "locked_snow");
        register(lockedFence, "locked_fence");
        register(lockedCauldron, "locked_cauldron");
        register(lockedCobblestoneWall, "locked_cobblestone_wall");
        register(lockedGlassPane, "locked_glass_pane");
        register(lockedStair, "locked_stair");
        register(lockedStair1, "locked_stair_1");
        register(lockedStair2, "locked_stair_2");
        register(blockTeleporter, "block_teleporter");
    }

    private static void register(Block block, String name)
    {
        GameRegistry.registerBlock(block.setRegistryName(name));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(lockedBed);
        registerRender(noHeadPiston);
        registerRender(pistonArm);
        registerRender(lockedLadder);
        registerRender(noHurtCactus);
        registerRender(infinityCake);
        registerRender(lockedVine);
        registerRender(lockedFenceGate);
        registerRender(lockedWaterlily);
        registerRender(lockedEnchantingTable);
        registerRender(lockedBrewingStand);
        registerRender(lockedDragonEgg);
        registerRender(lockedCocoa);
        registerRender(lockedChest);
        registerRender(lockedFlowerPot);
        registerRender(lockedAnvil);
        registerRender(lockedDaylightDetector);
        registerRender(lockedHopper);
        registerRender(lockedTrapdoor);
        registerRender(lockedCarpet);
        registerRender(lockedRepeater);
        registerRender(lockedSnow);
        registerRender(lockedFence);
        registerRender(lockedCauldron);
        registerRender(lockedCobblestoneWall);
        registerRender(lockedGlassPane);
        registerRender(lockedStair);
        registerRender(lockedStair1);
        registerRender(lockedStair2);
        registerRender(blockTeleporter);
    }
}
