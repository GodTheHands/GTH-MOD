package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

import java.awt.*;

public class ToggleSprintScreen extends DraggableComponent {
    @Override
    public int getWidth() {
        return font.getStringWidth("Toggle Sprint> " + (ConfigLoader.toggle_sprint ? "ON" : "OFF"));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("Toggle Sprint> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);

        if (ConfigLoader.toggle_sprint) {
            font.drawStringWithShadow("ON", pos.getAbsoluteX() + font.getStringWidth("Toggle Sprint> "), pos.getAbsoluteY(), new Color(0,255,0,255).getRGB());
        }
        else {
            font.drawStringWithShadow("OFF", pos.getAbsoluteX() + font.getStringWidth("Toggle Sprint> "), pos.getAbsoluteY(), new Color(255,0,0,255).getRGB());
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Toggle Sprint> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);

        if (ConfigLoader.toggle_sprint) {
            font.drawStringWithShadow("ON", pos.getAbsoluteX() + font.getStringWidth("Toggle Sprint> "), pos.getAbsoluteY(), new Color(0,255,0,255).getRGB());
        }
        else {
            font.drawStringWithShadow("OFF", pos.getAbsoluteX() + font.getStringWidth("Toggle Sprint> "), pos.getAbsoluteY(), new Color(255,0,0,255).getRGB());
        }
    }
}
