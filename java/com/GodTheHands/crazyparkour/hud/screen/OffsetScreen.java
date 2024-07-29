package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.PBsystem.OffsetShower;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

public class OffsetScreen extends DraggableComponent {
    private static double offsetX;
    private static double offsetZ;

    @Override
    public int getWidth() {
        return font.getStringWidth("OffsetX> " + roundByScale(offsetX));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT * 2;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (OffsetShower.calculatePB() >= -1.5D) {
            offsetX = OffsetShower.offsetX;
            offsetZ = OffsetShower.offsetZ;
        }

        font.drawStringWithShadow("OffsetX> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(offsetX), pos.getAbsoluteX() + font.getStringWidth("OffsetX> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("OffsetZ> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(offsetZ), pos.getAbsoluteX() + font.getStringWidth("OffsetZ> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("OffsetX> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(offsetX), pos.getAbsoluteX() + font.getStringWidth("OffsetX> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("OffsetZ> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(offsetZ), pos.getAbsoluteX() + font.getStringWidth("OffsetZ> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
    }
}
