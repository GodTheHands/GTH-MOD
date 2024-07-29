package com.GodTheHands.crazyparkour.PBsystem;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.block.BlockLockedVine;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.component.TimerComponent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;

import java.text.DecimalFormat;

public class OffsetShower extends TargetBlock {
    public static double offsetX;
    public static double offsetZ;
    private static double prevPB;
    public static double currentPB;

    public static void clearPB() {
        prevPB = currentPB = 0;
    }

    public static double calculatePB() {
        if (offsetX >= 0 && offsetZ >= 0) {
            return MathHelper.sqrt_double(offsetX * offsetX + offsetZ * offsetZ);
        }
        else if (offsetX < 0 && offsetZ < 0) {
            return -MathHelper.sqrt_double(offsetX * offsetX + offsetZ * offsetZ);
        }
        else {
            return (offsetX < 0 ? offsetX : offsetZ);
        }
    }

    public static String roundByScale(double floatNumber) {
        int scale = ConfigLoader.precisionDigit;

        if (scale == 0) {
            return new DecimalFormat("0").format(floatNumber);
        }

        StringBuilder formatStr = new StringBuilder("0.");

        for (int i = 0; i < scale; i++) {
            formatStr.append("0");
        }
        return new DecimalFormat(formatStr.toString()).format(floatNumber);
    }

    public static void show() {
        if (targetBlock == null) { return;}

        if (minecraft.thePlayer.prevPosY > getMaxY() && minecraft.thePlayer.posY <= getMaxY()) {
            if (Math.abs(minecraft.thePlayer.posX - getMaxX()) <= Math.abs(minecraft.thePlayer.posX - getMinX())) {
                offsetX = getMaxX() - minecraft.thePlayer.prevPosX;
            }
            else {
                offsetX = minecraft.thePlayer.prevPosX - getMinX();
            }

            if (!ConfigLoader.auto_judgement) {
                if (Math.abs(minecraft.thePlayer.posZ - getMaxZ()) <= Math.abs(minecraft.thePlayer.posZ - getMinZ())) {
                    offsetZ = getMaxZ() - minecraft.thePlayer.prevPosZ;
                }
                else {
                    offsetZ = minecraft.thePlayer.prevPosZ - getMinZ();
                }
            }
            else {
                if (Math.abs(minecraft.thePlayer.prevPosZ - getMaxZ()) <= Math.abs(minecraft.thePlayer.prevPosZ - getMinZ())) {
                    offsetZ = getMaxZ() - TimerComponent.prevPosZ;
                }
                else {
                    offsetZ = TimerComponent.prevPosZ - getMinZ();
                }
            }

            if (calculatePB() >= -1.5D) {
                currentPB = calculatePB();

                if (currentPB > prevPB || prevPB == 0) {
                     minecraft.thePlayer.addChatMessage(new ChatComponentTranslation("show.offsetX", roundByScale(offsetX)));
                     minecraft.thePlayer.addChatMessage(new ChatComponentTranslation("show1.offsetZ1", roundByScale(offsetZ)));
                     prevPB = currentPB;
                 }
            }
        }
        else if (minecraft.thePlayer.prevPosY < getMaxY() && minecraft.thePlayer.prevPosY >= getMinY() && targetBlock.isLadder(worldIn, blockPos, null)) { //Ladder & Vine
            if (targetBlock.getBlockBoundsMaxZ() - targetBlock.getBlockBoundsMinZ() == 1.0) { //Grab X
                if (Math.abs(minecraft.thePlayer.posX - getMaxX()) <= Math.abs(minecraft.thePlayer.posX - getMinX())) {
                    offsetX = getMaxX() - minecraft.thePlayer.prevPosX + 0.7 - targetBlock.getBlockBoundsMaxX();

                    if (targetBlock == BlockLoader.lockedVine && worldIn.getBlockState(blockPos).getBlock() == BlockLoader.lockedVine && worldIn.getBlockState(blockPos).getValue(BlockLockedVine.EAST)) {
                        offsetX = 0.4 - offsetX;
                    }
                } else {
                    offsetX = minecraft.thePlayer.prevPosX - getMinX() - 0.3 + targetBlock.getBlockBoundsMinX();

                    if (targetBlock == BlockLoader.lockedVine && worldIn.getBlockState(blockPos).getBlock() == BlockLoader.lockedVine && worldIn.getBlockState(blockPos).getValue(BlockLockedVine.EAST)) {
                        offsetX = 0.4 - offsetX;
                    }
                }

                if (Math.abs(minecraft.thePlayer.posZ - getMaxZ()) <= Math.abs(minecraft.thePlayer.posZ - getMinZ())) {
                    offsetZ = getMaxZ() - minecraft.thePlayer.prevPosZ + 0.7 - targetBlock.getBlockBoundsMaxZ();
                } else {
                    offsetZ = minecraft.thePlayer.prevPosZ - getMinZ() - 0.3 + targetBlock.getBlockBoundsMinZ();
                }
            } else if (targetBlock.getBlockBoundsMaxX() - targetBlock.getBlockBoundsMinX() == 1.0) { //Grab Z
                if (Math.abs(minecraft.thePlayer.posX - getMaxX()) <= Math.abs(minecraft.thePlayer.posX - getMinX())) {
                    offsetX = getMaxX() - minecraft.thePlayer.prevPosX + 0.7 - targetBlock.getBlockBoundsMaxX();
                } else {
                    offsetX = minecraft.thePlayer.prevPosX - getMinX() - 0.3 + targetBlock.getBlockBoundsMinX();
                }

                if (Math.abs(minecraft.thePlayer.posZ - getMaxZ()) <= Math.abs(minecraft.thePlayer.posZ - getMinZ())) {
                    offsetZ = getMaxZ() - minecraft.thePlayer.prevPosZ + 0.7 - targetBlock.getBlockBoundsMaxZ();

                    if (targetBlock == BlockLoader.lockedVine) {
                        offsetZ = 0.4 - offsetZ;
                    }
                } else {
                    offsetZ = minecraft.thePlayer.prevPosZ - getMinZ() - 0.3 + targetBlock.getBlockBoundsMinZ();

                    if (targetBlock == BlockLoader.lockedVine) {
                        offsetZ = 0.4 - offsetZ;
                    }
                }
            }

            if (calculatePB() >= -1.5D) {
                currentPB = calculatePB();

                if (currentPB > prevPB || prevPB == 0) {
                    minecraft.thePlayer.addChatMessage(new ChatComponentTranslation("show.offsetX", roundByScale(offsetX)));
                    minecraft.thePlayer.addChatMessage(new ChatComponentTranslation("show.offsetZ1", roundByScale(offsetZ)));
                    prevPB = currentPB;
                }
            }
        }
    }
}
