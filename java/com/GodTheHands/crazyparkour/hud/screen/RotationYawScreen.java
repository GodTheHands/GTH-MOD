package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import net.minecraft.util.MathHelper;

public class RotationYawScreen extends DraggableComponent {
    @Override
    public int getWidth() { return font.getStringWidth("F> " + roundByScale((mc.thePlayer.rotationYaw + 180) % 360 - 180));}

    @Override
    public int getHeight() { return font.FONT_HEIGHT;}

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("F> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(MathHelper.wrapAngleTo180_double(mc.thePlayer.rotationYaw)), pos.getAbsoluteX() + font.getStringWidth("F> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("F> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(MathHelper.wrapAngleTo180_double(mc.thePlayer.rotationYaw)), pos.getAbsoluteX() + font.getStringWidth("F> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
