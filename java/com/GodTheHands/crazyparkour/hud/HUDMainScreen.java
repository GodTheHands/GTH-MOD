package com.GodTheHands.crazyparkour.hud;

import com.GodTheHands.crazyparkour.CrazyParkour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class HUDMainScreen {
    private boolean isEnabled = true;

    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final CrazyParkour crazyParkour;

    public HUDMainScreen() {
        this.mc = Minecraft.getMinecraft();
        this.font = mc.fontRendererObj;
        this.crazyParkour = CrazyParkour.instance;

        setEnabled(isEnabled);
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
