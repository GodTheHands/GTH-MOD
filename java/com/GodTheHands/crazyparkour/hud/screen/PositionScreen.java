package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

public class PositionScreen extends DraggableComponent {

    @Override
    public int getWidth() {
        return font.getStringWidth("X> " + roundByScale(mc.thePlayer.posX));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT * 3;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("X> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.posX), pos.getAbsoluteX() + font.getStringWidth("X> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Y> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.posY), pos.getAbsoluteX() + font.getStringWidth("Y> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Z> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT * 2, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.posZ), pos.getAbsoluteX() + font.getStringWidth("Z> "), pos.getAbsoluteY() + font.FONT_HEIGHT * 2, ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("X> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.posX), pos.getAbsoluteX() + font.getStringWidth("X> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Y> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.posY), pos.getAbsoluteX() + font.getStringWidth("Y> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Z> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT * 2, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.posZ), pos.getAbsoluteX() + font.getStringWidth("Z> "), pos.getAbsoluteY() + font.FONT_HEIGHT * 2, ConfigLoader.RGBFontColor);
    }
}
