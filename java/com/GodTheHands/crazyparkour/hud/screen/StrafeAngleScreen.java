package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import com.GodTheHands.crazyparkour.hud.component.StrafeComponent;
import com.GodTheHands.crazyparkour.hud.component.TimerComponent;

public class StrafeAngleScreen extends DraggableComponent {
    private static double strafeAngle;

    @Override
    public int getWidth() {
        return font.getStringWidth("Strafe Angle> " + roundByScale(strafeAngle));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        if (!(TimerComponent.onGround) && StrafeComponent.prevPrevMovingStraight && StrafeComponent.prevMovingStrafe) {
            strafeAngle = StrafeComponent.prevRotationYaw - StrafeComponent.prevPrevRotationYaw;
        }

        font.drawStringWithShadow("Strafe Angle> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(strafeAngle), pos.getAbsoluteX() + font.getStringWidth("Strafe Angle> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Strafe Angle> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(strafeAngle), pos.getAbsoluteX() + font.getStringWidth("Strafe Angle> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
