package com.GodTheHands.crazyparkour.hud;

public interface IRender extends IRenderConfig {
    int getWidth();
    int getHeight();

    void render(ScreenPosition pos);

    default void renderDummy(ScreenPosition pos) {
        render(pos);
    }

    default boolean isEnabled() {return true;}

}
