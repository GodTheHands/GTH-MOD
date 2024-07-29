package com.GodTheHands.crazyparkour.macro;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class TickStateSaver {
    public boolean isWDown; //1
    public boolean isADown; //2
    public boolean isSDown; //4
    public boolean isDDown; //8

    public boolean isSpaceDown; //16
    public boolean isShiftDown; //32
    public boolean isSprintDown; //64

    public int statePassword;

    public double rotationDX;
    public String originalDX;

    public boolean toggle(boolean toggleKey) {
        return !toggleKey;
    }

    public TickStateSaver(int statePassword, String dx) {
        this.statePassword = statePassword;
        isWDown = statePassword % 2 == 1;
        statePassword /= 2;
        isADown = statePassword % 2 == 1;
        statePassword /= 2;
        isSDown = statePassword % 2 == 1;
        statePassword /= 2;
        isDDown = statePassword % 2 == 1;
        statePassword /= 2;
        isSpaceDown = statePassword % 2 == 1;
        statePassword /= 2;
        isShiftDown = statePassword % 2 == 1;
        statePassword /= 2;
        isSprintDown = statePassword % 2 == 1;

        this.originalDX = dx;
        this.rotationDX = Double.parseDouble(originalDX);
    }

    public int formPassword() {
        return (isWDown ? 1 : 0) + (isADown ? 2 : 0) + (isSDown ? 4 : 0) + (isDDown ? 8 : 0) + (isSpaceDown ? 16 : 0) + (isShiftDown ? 32 : 0) + (isSprintDown ? 64 : 0);
    }

    public boolean getState(int id) {
        int result = statePassword;

        for (int i = 0; i < id; i++) {
            result /= 2;
        }

        return (result % 2 == 1);
    }

    public String drawFont() {
        return (isWDown ? " W " : "") + (isADown ? " A " : "") + (isSDown ? " S " : "") + (isDDown ? " D " : "") + (isSpaceDown ? " Space " : "") + (isShiftDown ? " Shift " : "") + (isSprintDown ? " Sprint " : "");
    }

    public void actionPerform() {
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode(), isWDown);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode(), isADown);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode(), isSDown);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode(), isDDown);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode(), isSpaceDown);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), isShiftDown);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), isSprintDown);
        Minecraft.getMinecraft().thePlayer.rotationYaw += rotationDX;
    }
}
