package com.GodTheHands.crazyparkour.hud;

public interface IRenderConfig {
    void save(ScreenPosition pos);

    ScreenPosition load();

    void save(boolean enabled);

    boolean bload();
}
