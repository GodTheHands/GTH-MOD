package com.GodTheHands.crazyparkour.command;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandLoader
{
    public CommandLoader(FMLServerStartingEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new CommandScp());
        ClientCommandHandler.instance.registerCommand(new CommandTarget());
        ClientCommandHandler.instance.registerCommand(new CommandPrecision());
        ClientCommandHandler.instance.registerCommand(new CommandRun());
        ClientCommandHandler.instance.registerCommand(new CommandMacroDesc());
        ClientCommandHandler.instance.registerCommand(new CommandMdGen());
    }
}