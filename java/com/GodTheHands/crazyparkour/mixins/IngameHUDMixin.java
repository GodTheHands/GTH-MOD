package com.GodTheHands.crazyparkour.mixins;

import com.GodTheHands.crazyparkour.CrazyParkour;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameForge.class)
public class IngameHUDMixin {
    @Inject(method = "renderGameOverlay", at = @At(value = "RETURN"))
    protected void onRenderGameOverlay(CallbackInfo c) {
        CrazyParkour.instance.hudManager.onRender();
    };
}
