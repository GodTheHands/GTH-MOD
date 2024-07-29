package com.GodTheHands.crazyparkour.hud.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class StrafeComponent {
    public static boolean movingStraight; //Faster
    public static boolean prevMovingStraight;
    public static boolean prevPrevMovingStraight;

    public static boolean movingStrafe; //Faster
    public static boolean prevMovingStrafe;
    public static boolean prevPrevMovingStrafe;

    public static double rotationYaw; //Faster
    public static double prevRotationYaw;
    public static double prevPrevRotationYaw;

    public static KeyBinding left = Minecraft.getMinecraft().gameSettings.keyBindLeft;
    public static KeyBinding right = Minecraft.getMinecraft().gameSettings.keyBindRight;

    private static boolean isStrafeValid() {
        return (left.isKeyDown() && !right.isKeyDown()) || (!left.isKeyDown() && right.isKeyDown());
    }

    public static boolean isMovingStraight() {
        return (TimerComponent.movingForward() || TimerComponent.movingBackward()) && !isStrafeValid();
    }

    public static boolean isMovingStrafe() {
        return (TimerComponent.movingForward() || TimerComponent.movingBackward()) && isStrafeValid();
    }
}
