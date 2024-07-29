package com.GodTheHands.crazyparkour.command;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.macro.MacroConfigSaver;
import com.GodTheHands.crazyparkour.macro.MacroRunner;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandRun extends CommandBase {
    @Override
    public String getCommandName() {
        return "run";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.run.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0 || args.length > 1) {
            throw new WrongUsageException("commands.run.usage");
        }

        String temp = ConfigLoader.macro_name;
        ConfigLoader.macro_name = args[0];
        MacroRunner.load(MacroRunner.transformArray(MacroConfigSaver.loadMCSFromFile().passwords, MacroConfigSaver.loadMCSFromFile().dxs));
        ConfigLoader.macro_name = temp;
    }
}
