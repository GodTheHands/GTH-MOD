package com.GodTheHands.crazyparkour.command;

import com.GodTheHands.crazyparkour.PBsystem.OffsetShower;
import com.GodTheHands.crazyparkour.PBsystem.TargetBlock;
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

public class CommandMacroDesc extends CommandBase {
    @Override
    public String getCommandName() {
        return "md";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.md.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            throw new WrongUsageException("commands.target.usage");
        }
        else {
            String result = "";

            MacroTranslator.load(MacroTranslator.transformArray(MacroConfigSaver.loadMCSFromFile().passwords, MacroConfigSaver.loadMCSFromFile().dxs));
            MacroTranslator.translateMacro();

            for (int i = 0; i < MacroTranslator.behaviors.size(); i++) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("md.out", MacroTranslator.behaviors.get(i)));
                result += MacroTranslator.behaviors.get(i);
            };

            MacroTranslator.behaviors.clear();

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result), null);
        }
    }
}
