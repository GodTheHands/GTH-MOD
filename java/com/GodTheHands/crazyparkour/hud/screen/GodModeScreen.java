package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

import java.awt.*;

public class GodModeScreen extends DraggableComponent {
    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("God Mode> " + (ConfigLoader.god_mode ? "ON" : "OFF"));
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("God Mode> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);

        if (ConfigLoader.god_mode) {
            font.drawStringWithShadow("ON", pos.getAbsoluteX() + font.getStringWidth("God Mode> "), pos.getAbsoluteY(), new Color(0, 255, 0, 255).getRGB());
        }
        else {
            font.drawStringWithShadow("OFF", pos.getAbsoluteX() + font.getStringWidth("God Mode> "), pos.getAbsoluteY(), new Color(255, 0, 0, 255).getRGB());
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("God Mode> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);

        if (ConfigLoader.god_mode) {
            font.drawStringWithShadow("ON", pos.getAbsoluteX() + font.getStringWidth("God Mode> "), pos.getAbsoluteY(), new Color(0, 255, 0, 255).getRGB());
        }
        else {
            font.drawStringWithShadow("OFF", pos.getAbsoluteX() + font.getStringWidth("God Mode> "), pos.getAbsoluteY(), new Color(255, 0, 0, 255).getRGB());
        }
    }
}
