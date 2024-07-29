package com.GodTheHands.crazyparkour.inventory;

import com.GodTheHands.crazyparkour.CrazyParkour;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler
{
    public static final int THINGS = 1;

    public GuiElementLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(CrazyParkour.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == THINGS) {
            return new ContainerDemo(player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == THINGS) {
            return new GuiContainerDemo(new ContainerDemo(player));
        }
        return null;
    }
}
