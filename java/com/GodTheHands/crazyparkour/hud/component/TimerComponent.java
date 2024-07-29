package com.GodTheHands.crazyparkour.hud.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class TimerComponent{
    public static int timing; //Faster
    public static int groundTiming;
    public static int groundTimingBW;
    public static int groundSneakingTiming;
    public static int groundSneakingTimingBW;

    public static boolean prevOnGround; //Slower
    public static boolean onGround;

    public static boolean prevPrevMoved; //Faster
    public static boolean prevMoved;
    public static boolean moved;
    public static boolean movingF;
    public static boolean movingB;
    public static boolean prevMovingF;
    public static boolean prevMovingB;
    public static boolean prevPrevMovingF;

    public static boolean isSneaking; //Slower
    public static boolean prevIsSneaking;
    public static boolean prevPrevIsSneaking;

    public static boolean isSprinting; //Slower
    public static boolean prevIsSprinting;
    
    public static double posX;
    public static double prevPosX;

    public static double posZ;
    public static double prevPosZ;

    public static KeyBinding forward = Minecraft.getMinecraft().gameSettings.keyBindForward;
    public static KeyBinding backward = Minecraft.getMinecraft().gameSettings.keyBindBack;

    public static boolean movingForward() {
        return forward.isKeyDown() && !backward.isKeyDown();
    }

    public static boolean movingBackward() {
        return !forward.isKeyDown() && backward.isKeyDown();
    }

    public static boolean movementJudgement() {
        GameSettings gs = Minecraft.getMinecraft().gameSettings;
        return gs.keyBindForward.isKeyDown() || gs.keyBindLeft.isKeyDown() || gs.keyBindBack.isKeyDown() || gs.keyBindRight.isKeyDown();
    }
}
