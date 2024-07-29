package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import net.minecraft.util.MathHelper;

public class MotionScreen extends DraggableComponent {
    @Override
    public int getWidth() { return font.getStringWidth("Motion Value> " + roundByScale(Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ)));}

    @Override
    public int getHeight() { return font.FONT_HEIGHT * 2;}

    @Override
    public void render(ScreenPosition pos) {
        double dx = mc.thePlayer.posX - mc.thePlayer.prevPosX;
        double dz = mc.thePlayer.posZ - mc.thePlayer.prevPosZ;
        font.drawStringWithShadow("Motion Value> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(MathHelper.sqrt_double(dx * dx + dz * dz)), pos.getAbsoluteX() + font.getStringWidth("Motion Value> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Motion Deg> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(Math.toDegrees(-MathHelper.atan2(dx, dz))), pos.getAbsoluteX() + font.getStringWidth("Motion Deg> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        double dx = mc.thePlayer.posX - mc.thePlayer.prevPosX;
        double dz = mc.thePlayer.posZ - mc.thePlayer.prevPosZ;
        font.drawStringWithShadow("Motion Value> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(MathHelper.sqrt_double(dx * dx + dz * dz)), pos.getAbsoluteX() + font.getStringWidth("Motion Value> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
        font.drawStringWithShadow("Motion Deg> ", pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.prefixColor);
        font.drawStringWithShadow(roundByScale(Math.toDegrees(-MathHelper.atan2(dx, dz))), pos.getAbsoluteX() + font.getStringWidth("Motion Deg> "), pos.getAbsoluteY() + font.FONT_HEIGHT, ConfigLoader.RGBFontColor);
    }
}
