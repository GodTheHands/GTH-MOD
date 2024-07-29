package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

import java.awt.*;

public class UnsyncScreen extends DraggableComponent {
    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("Unsync Calc> " + (ConfigLoader.auto_judgement ? "ON" : "OFF"));
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("Unsync Calc> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);

        if (ConfigLoader.auto_judgement) {
            font.drawStringWithShadow("ON", pos.getAbsoluteX() + font.getStringWidth("Unsync Calc> "), pos.getAbsoluteY(), new Color(0, 255, 0, 255).getRGB());
        }
        else {
            font.drawStringWithShadow("OFF", pos.getAbsoluteX() + font.getStringWidth("Unsync Calc> "), pos.getAbsoluteY(), new Color(255, 0, 0, 255).getRGB());
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Unsync Calc> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);

        if (ConfigLoader.auto_judgement) {
            font.drawStringWithShadow("ON", pos.getAbsoluteX() + font.getStringWidth("Unsync Calc> "), pos.getAbsoluteY(), new Color(0, 255, 0, 255).getRGB());
        }
        else {
            font.drawStringWithShadow("OFF", pos.getAbsoluteX() + font.getStringWidth("Unsync Calc> "), pos.getAbsoluteY(), new Color(255, 0, 0, 255).getRGB());
        }
    }
}
