package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeScreen extends DraggableComponent {
    private static SimpleDateFormat sdf;
    private static Date d;

    static {
        d = new Date();
        sdf = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("Time> " + sdf.format(d.getTime()));
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        d = new Date();
        font.drawStringWithShadow("Time> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(sdf.format(d.getTime()), pos.getAbsoluteX() + font.getStringWidth("Time> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Time> ", pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(sdf.format(d.getTime()), pos.getAbsoluteX() + font.getStringWidth("Time> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
