package com.GodTheHands.crazyparkour.hud;

import com.GodTheHands.crazyparkour.hud.screen.*;
import com.GodTheHands.crazyparkour.hud.screen.StratScreen;

public class ScreenInstances {
    private static PositionScreen positionScreen;

    private static KeystrokesScreen keystrokesScreen;

    private static ToggleSprintScreen toggleSprintScreen;

    private static GodModeScreen godModeScreen;

    private static RotationYawScreen rotationYawScreen;

    private static RotationPitchScreen rotationPitchScreen;

    private static MotionScreen motionScreen;

    private static LastLandingScreen lastLandingScreen;

    private static JumpAngleScreen jumpAngleScreen;

    private static TierScreen tierScreen;

    private static StratScreen stratScreen;

    private static TimeScreen timeScreen;

    private static StrafeAngleScreen strafeAngleScreen;

    private static OffsetScreen offsetScreen;

    private static UnsyncScreen unsyncScreen;

    public static void register(HUDManager api) {
        positionScreen = new PositionScreen();
        api.register(positionScreen);

        keystrokesScreen = new KeystrokesScreen();
        api.register(keystrokesScreen);

        toggleSprintScreen = new ToggleSprintScreen();
        api.register(toggleSprintScreen);

        godModeScreen = new GodModeScreen();
        api.register(godModeScreen);

        rotationYawScreen = new RotationYawScreen();
        api.register(rotationYawScreen);

        rotationPitchScreen = new RotationPitchScreen();
        api.register(rotationPitchScreen);

        motionScreen = new MotionScreen();
        api.register(motionScreen);

        lastLandingScreen = new LastLandingScreen();
        api.register(lastLandingScreen);

        jumpAngleScreen = new JumpAngleScreen();
        api.register(jumpAngleScreen);

        tierScreen = new TierScreen();
        api.register(tierScreen);

        stratScreen = new StratScreen();
        api.register(stratScreen);

        timeScreen = new TimeScreen();
        api.register(timeScreen);

        strafeAngleScreen = new StrafeAngleScreen();
        api.register(strafeAngleScreen);

        offsetScreen = new OffsetScreen();
        api.register(offsetScreen);

        unsyncScreen = new UnsyncScreen();
        api.register(unsyncScreen);
    }

    public static PositionScreen getPositionScreen() { return positionScreen; }

    public static KeystrokesScreen getKeystrokesScreen() { return keystrokesScreen;}

    public static ToggleSprintScreen getToggleSprintScreen() { return toggleSprintScreen;}

    public static GodModeScreen getGodModeScreen() { return godModeScreen;}

    public static RotationYawScreen getRotationYawScreen() { return rotationYawScreen;}

    public static RotationPitchScreen getRotationPitchScreen() { return rotationPitchScreen;}

    public static MotionScreen getMotionScreen() { return motionScreen;}

    public static LastLandingScreen getLastLandingScreen() { return lastLandingScreen;}

    public static JumpAngleScreen getJumpAngleScreen() { return jumpAngleScreen;}

    public static TierScreen getTierScreen() { return tierScreen;}

    public static StratScreen getStratScreen() { return stratScreen;}

    public static TimeScreen getTimeScreen() { return timeScreen;}

    public static StrafeAngleScreen getStrafeAngleScreen() { return strafeAngleScreen;}

    public static OffsetScreen getOffsetScreen() { return offsetScreen;}

    public static UnsyncScreen getUnsyncScreen() { return unsyncScreen;}
}
