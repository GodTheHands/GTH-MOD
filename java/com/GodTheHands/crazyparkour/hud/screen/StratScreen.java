package com.GodTheHands.crazyparkour.hud.screen;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;
import com.GodTheHands.crazyparkour.hud.component.DraggableComponent;
import com.GodTheHands.crazyparkour.hud.component.TimerComponent;

public class StratScreen extends DraggableComponent {
    private static String header = "No Input";

    public String finder() {

         if (!TimerComponent.prevIsSneaking && TimerComponent.prevPrevIsSneaking && TimerComponent.prevMoved && TimerComponent.prevOnGround && !TimerComponent.onGround) {
             if (TimerComponent.prevMovingF && TimerComponent.groundSneakingTiming > 0) {
                 header = "Burst " + TimerComponent.groundSneakingTiming + "t";
             }
             else if (TimerComponent.prevMovingB && TimerComponent.groundSneakingTimingBW > 0) {
                 header = "BW Burst " + TimerComponent.groundSneakingTimingBW + "t";
              }
         }
         else if (!TimerComponent.onGround && TimerComponent.prevOnGround && (TimerComponent.groundTiming > 0 || TimerComponent.groundTimingBW > 0) && TimerComponent.prevMoved) {
            if (TimerComponent.prevMovingF && TimerComponent.groundTiming > 0) {
                header = "HH " + TimerComponent.groundTiming + "t";
            }
            else if (TimerComponent.prevMovingB && TimerComponent.groundTimingBW > 0) {
                header = "BWHH " + TimerComponent.groundTimingBW + "t";
            }
        }
         else if (!TimerComponent.onGround && TimerComponent.prevOnGround && TimerComponent.prevMoved) {
            if (TimerComponent.prevMovingF) {
                header = "Jam";
            }
            else if (TimerComponent.prevMovingB) {
                header = "BWMM";
            }
        }
        else if (TimerComponent.prevMoved && !TimerComponent.prevPrevMoved && !TimerComponent.onGround) {
            header = "Pessi " + (TimerComponent.timing - 10) + "t";
        }
        else if (TimerComponent.prevMoved && !TimerComponent.prevPrevMoved && !TimerComponent.prevOnGround && TimerComponent.onGround) {
            header = "Pessi " + (TimerComponent.timing - 11) + "t";
         }
        else if (TimerComponent.prevPrevMovingF && TimerComponent.prevMovingF && TimerComponent.isSprinting && !TimerComponent.prevIsSprinting && !TimerComponent.onGround) {
            header = "FMM " + (10 - TimerComponent.timing) + "t";
         }
        else if (TimerComponent.prevPrevMovingF && TimerComponent.prevMovingF && TimerComponent.isSprinting && !TimerComponent.prevIsSprinting && !TimerComponent.prevOnGround && TimerComponent.onGround) {
            header = "FMM " + (11 - TimerComponent.timing) + "t";
         }

        return header;
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("Strat> " + finder());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow("Strat> " , pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(finder(), pos.getAbsoluteX() + font.getStringWidth("Strat> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow("Strat> " , pos.getAbsoluteX(), pos.getAbsoluteY(), ConfigLoader.prefixColor);
        font.drawStringWithShadow(finder(), pos.getAbsoluteX() + font.getStringWidth("Strat> "), pos.getAbsoluteY(), ConfigLoader.RGBFontColor);
    }
}
