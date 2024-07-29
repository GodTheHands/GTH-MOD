package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

public class LastLandingScreen extends DraggableComponent {
    private static double lastLandingX;
    private static double lastLandingZ;

    @Override
    public int getWidth() {
        return font.getStringWidth("Last Landing X> " + roundByScale(lastLandingX));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT * 2;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (mc.thePlayer.onGround) {
            lastLandingX = mc.thePlayer.lastTickPosX;
            lastLandingZ = mc.thePlayer.lastTickPosZ;
        }

        font.drawStringWithShadow("Last Landing X> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(lastLandingX), pos.getAbsoluteX() + font.getStringWidth("Last Landing X> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Last Landing Z> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(lastLandingZ), pos.getAbsoluteX() + font.getStringWidth("Last Landing Y> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Last Landing X> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(lastLandingX), pos.getAbsoluteX() + font.getStringWidth("Last Landing X> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Last Landing Z> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(lastLandingZ), pos.getAbsoluteX() + font.getStringWidth("Last Landing Y> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
    }
}
