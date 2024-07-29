package com.GodTheHands.crazyparkour.mixins;

import com.GodTheHands.crazyparkour.PBsystem.OffsetShower;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class ShowPBMixin extends Entity {
    public ShowPBMixin(World worldIn) { super(worldIn); }

    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    protected void onShowingPB(CallbackInfo c) {
        OffsetShower.show();
    }
}
