package com.GodTheHands.crazyparkour.macro;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class FakeKeyStroke {
    public static FontRenderer FR = Minecraft.getMinecraft().fontRendererObj;

    public static final Key W = new Key("W", 21, 1, 18, 18, 0);
    public static final Key A = new Key("A", 1, 21, 18, 18, 1);
    public static final Key S = new Key("S", 21, 21, 18, 18, 2);
    public static final Key D = new Key("D", 41, 21, 18, 18, 3);
    public static final Key SPACE = new Key("----", 1, 41, 58, 12, 4);
    public static final Key SNEAK = new Key("LS", 1, 1, 18, 18, 5);
    public static final Key SPRINT = new Key("LC", 41, 1, 18, 18, 6);
    public static final Key[] keys = {W, A, S, D, SPACE, SPRINT, SNEAK};
    
    public static class Key {
        public String name;
        public int minX;
        public int minY;
        public int width;
        public int height;
        public int id;

        public Key(String name, int minX, int minY, int width, int height, int id) {
            this.name = name;
            this.minX = minX;
            this.minY = minY;
            this.width = width;
            this.height = height;
            this.id = id;
        }
    }

    public static void render(TickStateSaver tss) {
        if (tss == null) { return;}

        ScaledResolution SR = new ScaledResolution(Minecraft.getMinecraft());

        int positionX = SR.getScaledWidth() * 15 / 20;
        int positionY = SR.getScaledHeight() * 15 / 20;

        for (Key key : keys) {
            int tWidth = FR.getStringWidth(key.name);

            Gui.drawRect(positionX + key.minX, positionY + key.minY , positionX + (key.minX + key.width), positionY + (key.minY + key.height), tss.getState(key.id) ? new Color(255,255,255,100).getRGB() : new Color(0,0,0,100).getRGB());

            FR.drawStringWithShadow(key.name, positionX + (key.minX + key.width / 2) - tWidth / 2, positionY + (key.minY + key.height / 2 - 4), Color.WHITE.getRGB());
        }
    }
}
