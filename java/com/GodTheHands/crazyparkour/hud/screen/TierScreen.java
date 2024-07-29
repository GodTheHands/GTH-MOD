package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import com.GodTheHands.crazyparkour.hud.component.TimerComponent;

public class TierScreen extends DraggableComponent {
    @Override
    public int getWidth() {
        return font.getStringWidth("Tier> " + TimerComponent.timing);
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("Tier> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(String.valueOf(TimerComponent.timing), pos.getAbsoluteX() + font.getStringWidth("Tier> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Tier> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(String.valueOf(TimerComponent.timing), pos.getAbsoluteX() + font.getStringWidth("Tier> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
