package com.GodTheHands.crazyparkour.command;

import com.GodTheHands.crazyparkour.capability.CapabilityLoader;
import com.GodTheHands.crazyparkour.capability.IPositionHistory;
import com.GodTheHands.crazyparkour.item.ItemTeleportDye;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;

import java.util.List;

public class CommandScp extends CommandBase {
    @Override
    public String getCommandName()
    {
        return "scp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.scp.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length != 5) {
            throw new WrongUsageException("commands.scp.usage");
        }
        else {
            EntityPlayer player = CommandBase.getCommandSenderAsPlayer(sender);
            IPositionHistory position = player.getCapability(CapabilityLoader.positionHistory, null);
            position.setPosition(new Vec3(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])));
            position.setRotationYaw(Float.parseFloat(args[3]));
            position.setRotationPitch(Float.parseFloat(args[4]));
            player.worldObj.playSoundAtEntity(player, "crazyparkour:checkpoint.test", 1.0F, 1.0F);
            sender.addChatMessage(new ChatComponentTranslation("commands.scp.success"));
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1)
        {
            return CommandBase.getListOfStringsMatchingLastWord(args, String.valueOf(sender.getPosition().getX()));
        }
        else if (args.length == 2) {
            return CommandBase.getListOfStringsMatchingLastWord(args, String.valueOf(sender.getPosition().getY()));
        }
        else if (args.length == 3) {
            return CommandBase.getListOfStringsMatchingLastWord(args, String.valueOf(sender.getPosition().getZ()));
        }
        return null;
    }
}
