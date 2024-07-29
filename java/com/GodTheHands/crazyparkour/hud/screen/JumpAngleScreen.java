package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import net.minecraft.util.MathHelper;

public class JumpAngleScreen extends DraggableComponent {
    private static double jumpAngle;

    @Override
    public int getWidth() {
        return font.getStringWidth("Landing Angle> " + roundByScale(jumpAngle));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (mc.thePlayer.onGround) {
            jumpAngle = mc.thePlayer.rotationYaw;
        }

        font.drawStringWithShadow("Landing Angle> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(MathHelper.wrapAngleTo180_double(jumpAngle)), pos.getAbsoluteX() + font.getStringWidth("Landing Angle> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Landing Angle> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(MathHelper.wrapAngleTo180_double(jumpAngle)), pos.getAbsoluteX() + font.getStringWidth("Landing Angle> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
