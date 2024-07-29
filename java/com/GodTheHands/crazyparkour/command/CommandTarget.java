package com.GodTheHands.crazyparkour.command;

import com.GodTheHands.crazyparkour.PBsystem.OffsetShower;
import com.GodTheHands.crazyparkour.PBsystem.TargetBlock;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;

public class CommandTarget extends CommandBase {
    @Override
    public String getCommandName() {
        return "target";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.target.usage";
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
            if (Minecraft.getMinecraft().thePlayer.worldObj.getBlockState(Minecraft.getMinecraft().objectMouseOver.getBlockPos()).getBlock() != Blocks.air) {
                TargetBlock.updateStates(Minecraft.getMinecraft().objectMouseOver.getBlockPos());
                OffsetShower.clearPB();

                sender.addChatMessage(new ChatComponentTranslation("commands.target.success"));
            }
        }
    }
}
