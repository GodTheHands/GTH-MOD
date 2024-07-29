package com.GodTheHands.crazyparkour.command;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfigEntries;

public class CommandPrecision extends CommandBase {
    @Override
    public String getCommandName() {
        return "precision";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.precision.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0 || args.length > 1) {
            throw new WrongUsageException("commands.precision.usage");
        }
        else if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[0]) > 15) {
            throw new WrongUsageException("commands.precision.restriction");
        }
        else {
            Property prop;

            ConfigLoader.precisionDigit = Integer.parseInt(args[0]);

            prop = ConfigLoader.config.get("gamesettings", "precisionDigit", 5,
                    "Determine how many digit you want to display. ", 3, 15).setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
            prop.set(ConfigLoader.precisionDigit);

            ConfigLoader.config.save();
        }
    }
}
