package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class KeystrokesScreen extends DraggableComponent {

    private KeyScreen mode = KeyScreen.DefaultSettings;

    public void setMode(KeyScreen mode) {
        this.mode = mode;
    }

    public enum KeyScreen {
        DefaultSettings(Key.W, Key.A, Key.S, Key.D, Key.SPACE, Key.SPRINT, Key.SNEAK);

        private final Key[] keys;
        private int width;
        private int height;

        KeyScreen(Key... keysIn) {
            this.keys = keysIn;

            for (Key key : keys) {
                this.width = Math.max(this.width, key.getX() + key.getWidth());
                this.height = Math.max(this.height, key.getY() + key.getHeight());
            }
        }

        public int getWidth() {return width;}

        public int getHeight() {return height;}

        public Key[] getKeys() {return keys;}
    }

    private static class Key {

        private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
        private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
        private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
        private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
        private static final Key SPACE = new Key("----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 12);
        private static final Key SPRINT = new Key("LC", Minecraft.getMinecraft().gameSettings.keyBindSprint, 41, 1, 18, 18);
        private static final Key SNEAK = new Key("LS", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 1, 18, 18);

        private final String name;
        private final KeyBinding key;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Key(String name, KeyBinding key, int x, int y, int width, int height) {
            this.name = name;
            this.key = key;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean isDown() {
            return key.isKeyDown();
        }

        public String getName() {return name;}

        public KeyBinding getKey() {return key;}

        public int getX() {return x;}

        public int getY() {return y;}

        public int getWidth() {return width;}

        public int getHeight() {return height;}
    }

    @Override
    public int getWidth() {
        return mode.getWidth();
    }

    @Override
    public int getHeight() {
        return mode.getHeight();
    }

    @Override
    public void render(ScreenPosition pos) {
        for (Key key : mode.getKeys()) {
            int tWidth = font.getStringWidth(key.getName());

            Gui.drawRect(pos.getAbsoluteX() + key.getX(), pos.getAbsoluteY() + key.getY(), pos.getAbsoluteX() + key.getX() + key.getWidth(), pos.getAbsoluteY() + key.getY() + key.getHeight(), key.isDown() ? new Color(255,255,255,100).getRGB() : new Color(0,0,0,100).getRGB());

            font.drawStringWithShadow(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - tWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, Color.WHITE.getRGB());
        }
    }
}
