package com.GodTheHands.crazyparkour.command;

import com.GodTheHands.crazyparkour.macro.MacroConfigSaver;
import com.GodTheHands.crazyparkour.macro.MacroTranslator;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentTranslation;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class CommandMdGen extends CommandBase {
    @Override
    public String getCommandName() {
        return "mdgen";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.mdgen.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1 || args.length == 0) {
            throw new WrongUsageException("commands.mdgen.usage");
        }
        else {
            String result = "";

            MacroTranslator.load(MacroTranslator.transformArray(MacroConfigSaver.loadMCSFromFile().passwords, MacroConfigSaver.loadMCSFromFile().dxs));
            MacroTranslator.betterTranslate(Float.parseFloat(args[0]));

            for (int i = 0; i < MacroTranslator.behaviors.size(); i++) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("md.out", MacroTranslator.behaviors.get(i)));
                result += MacroTranslator.behaviors.get(i);
            };

            MacroTranslator.behaviors.clear();

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result), null);
        }
    }
}
