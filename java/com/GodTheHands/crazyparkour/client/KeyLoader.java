package com.GodTheHands.crazyparkour.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyLoader {
    public static KeyBinding enableGodMode;
    public static KeyBinding openHUD;
    public static KeyBinding clickGui;
    public static KeyBinding toggleSprint;
    public static KeyBinding macroGui;
    public static KeyBinding macroRunner;
    public static KeyBinding autoJudgement;
    public static KeyBinding target;

    public KeyLoader() {
        KeyLoader.enableGodMode = new KeyBinding("key.crazyparkour.enableGodMode", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.openHUD = new KeyBinding("key.crazyparkour.openHUD", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.clickGui = new KeyBinding("key.crazyparkour.clickGui", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.toggleSprint = new KeyBinding("key.crazyparkour.toggleSprint", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.macroGui = new KeyBinding("key.crazyparkour.macroGui", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.macroRunner = new KeyBinding("key.crazyparkour.macroRunner", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.autoJudgement = new KeyBinding("key.crazyparkour.autoJudgement", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");
        KeyLoader.target = new KeyBinding("key.crazyparkour.target", Keyboard.KEY_NUMPAD0, "key.ctgy.crazyparkour");

        ClientRegistry.registerKeyBinding(KeyLoader.enableGodMode);
        ClientRegistry.registerKeyBinding(KeyLoader.openHUD);
        ClientRegistry.registerKeyBinding(KeyLoader.clickGui);
        ClientRegistry.registerKeyBinding(KeyLoader.toggleSprint);
        ClientRegistry.registerKeyBinding(KeyLoader.macroGui);
        ClientRegistry.registerKeyBinding(KeyLoader.macroRunner);
        ClientRegistry.registerKeyBinding(KeyLoader.autoJudgement);
        ClientRegistry.registerKeyBinding(KeyLoader.target);
    }
}
