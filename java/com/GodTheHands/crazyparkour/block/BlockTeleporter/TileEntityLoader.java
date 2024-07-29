package com.GodTheHands.crazyparkour.block.BlockTeleporter;

import com.GodTheHands.crazyparkour.CrazyParkour;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
    public TileEntityLoader(FMLPreInitializationEvent event) {
        registerTileEntity(TileEntityBlockTeleporter.class, "BlockTeleporter");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        GameRegistry.registerTileEntity(tileEntityClass, CrazyParkour.MODID + ":" + id);
    }
}
