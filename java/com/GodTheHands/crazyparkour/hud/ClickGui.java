package com.GodTheHands.crazyparkour.hud;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class ClickGui extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(1, 5, 5, 150, 20, "Position: " + (ScreenInstances.getPositionScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(2, 5, 35, 150, 20, "Keystrokes: " + (ScreenInstances.getKeystrokesScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, 5, 65, 150, 20, "Modes: " + (ScreenInstances.getToggleSprintScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(4, 5, 95, 150, 20, "Facing: " + (ScreenInstances.getRotationYawScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(5, 5, 125, 150, 20, "Speed: " + (ScreenInstances.getMotionScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(6, 5, 155, 150, 20, "Last Landing: " + (ScreenInstances.getLastLandingScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(7, 5, 185, 150, 20, "Tier: " + (ScreenInstances.getTierScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(8, 5, 215, 150, 20, "Strat: " + (ScreenInstances.getStratScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(9, 165, 5, 150, 20, "Time: " + (ScreenInstances.getTimeScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(10, 165, 35, 150, 20, "Strafe Angle: " + (ScreenInstances.getStrafeAngleScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(11, 165, 65, 150, 20, "Offset: " + (ScreenInstances.getOffsetScreen().isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(12, 165, 95, 150, 20, "Unsync Calc: " + (ScreenInstances.getUnsyncScreen().isEnabled() ? "ON" : "OFF")));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            ScreenInstances.getPositionScreen().setEnabled(!ScreenInstances.getPositionScreen().isEnabled());
            ScreenInstances.getPositionScreen().save(ScreenInstances.getPositionScreen().isEnabled());
        }
        else if (button.id == 2) {
            ScreenInstances.getKeystrokesScreen().setEnabled(!ScreenInstances.getKeystrokesScreen().isEnabled());
            ScreenInstances.getKeystrokesScreen().save(ScreenInstances.getKeystrokesScreen().isEnabled());
        }
        else if (button.id == 3) {
            ScreenInstances.getToggleSprintScreen().setEnabled(!ScreenInstances.getToggleSprintScreen().isEnabled());
            ScreenInstances.getGodModeScreen().setEnabled(!ScreenInstances.getGodModeScreen().isEnabled());
            ScreenInstances.getToggleSprintScreen().save(ScreenInstances.getToggleSprintScreen().isEnabled());
            ScreenInstances.getGodModeScreen().save(ScreenInstances.getGodModeScreen().isEnabled());
        }
        else if (button.id == 4) {
            ScreenInstances.getRotationYawScreen().setEnabled(!ScreenInstances.getRotationYawScreen().isEnabled());
            ScreenInstances.getRotationPitchScreen().setEnabled(!ScreenInstances.getRotationPitchScreen().isEnabled());
            ScreenInstances.getRotationYawScreen().save(ScreenInstances.getRotationYawScreen().isEnabled());
            ScreenInstances.getRotationPitchScreen().save(ScreenInstances.getRotationPitchScreen().isEnabled());
        }
        else if (button.id == 5) {
            ScreenInstances.getMotionScreen().setEnabled(!ScreenInstances.getMotionScreen().isEnabled());
            ScreenInstances.getMotionScreen().save(ScreenInstances.getMotionScreen().isEnabled());
        }
        else if (button.id == 6) {
            ScreenInstances.getLastLandingScreen().setEnabled(!ScreenInstances.getLastLandingScreen().isEnabled());
            ScreenInstances.getJumpAngleScreen().setEnabled(!ScreenInstances.getJumpAngleScreen().isEnabled());
            ScreenInstances.getLastLandingScreen().save(ScreenInstances.getLastLandingScreen().isEnabled());
            ScreenInstances.getJumpAngleScreen().save(ScreenInstances.getJumpAngleScreen().isEnabled());
        }
        else if (button.id == 7) {
            ScreenInstances.getTierScreen().setEnabled(!ScreenInstances.getTierScreen().isEnabled());
            ScreenInstances.getTierScreen().save(ScreenInstances.getTierScreen().isEnabled());
        }
        else if (button.id == 8) {
            ScreenInstances.getStratScreen().setEnabled(!ScreenInstances.getStratScreen().isEnabled());
            ScreenInstances.getStratScreen().save(ScreenInstances.getStratScreen().isEnabled());
        }
        else if (button.id == 9) {
            ScreenInstances.getTimeScreen().setEnabled(!ScreenInstances.getTimeScreen().isEnabled());
            ScreenInstances.getTimeScreen().save(ScreenInstances.getTimeScreen().isEnabled());
        }
        else if (button.id == 10) {
            ScreenInstances.getStrafeAngleScreen().setEnabled(!ScreenInstances.getStrafeAngleScreen().isEnabled());
            ScreenInstances.getStrafeAngleScreen().save(ScreenInstances.getStrafeAngleScreen().isEnabled());
        }
        else if (button.id == 11) {
            ScreenInstances.getOffsetScreen().setEnabled(!ScreenInstances.getOffsetScreen().isEnabled());
            ScreenInstances.getOffsetScreen().save(ScreenInstances.getOffsetScreen().isEnabled());
        }
        else if (button.id == 12) {
            ScreenInstances.getUnsyncScreen().setEnabled(!ScreenInstances.getUnsyncScreen().isEnabled());
            ScreenInstances.getUnsyncScreen().save(ScreenInstances.getUnsyncScreen().isEnabled());
        }

        mc.displayGuiScreen(new ClickGui());
        super.actionPerformed(button);
    }
}
