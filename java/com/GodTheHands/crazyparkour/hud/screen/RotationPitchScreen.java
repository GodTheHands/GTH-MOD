package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

public class RotationPitchScreen extends DraggableComponent {
    @Override
    public int getWidth() { return font.getStringWidth("Pitch> " + roundByScale(mc.thePlayer.rotationPitch));}

    @Override
    public int getHeight() { return font.FONT_HEIGHT;}

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("Pitch> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.rotationPitch), pos.getAbsoluteX() + font.getStringWidth("Pitch> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Pitch> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(mc.thePlayer.rotationPitch), pos.getAbsoluteX() + font.getStringWidth("Pitch> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
