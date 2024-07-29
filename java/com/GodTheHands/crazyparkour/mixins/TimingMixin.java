package com.GodTheHands.crazyparkour.mixins;


import com.GodTheHands.crazyparkour.PBsystem.OffsetShower;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.component.StrafeComponent;
import com.GodTheHands.crazyparkour.hud.component.TimerComponent;
import com.GodTheHands.crazyparkour.macro.MacroRunner;
import com.GodTheHands.crazyparkour.macro.TickStateSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class TimingMixin extends Entity {
    @Shadow public abstract boolean isSneaking();

    public TimingMixin(World worldIn) { super(worldIn); }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    protected void onTimingUpdate(CallbackInfo c) {
        if (MacroRunner.macroTable != null && MacroRunner.macroTable.size() != 0 ) {
            TickStateSaver tss = MacroRunner.macroTable.get(0);
            MacroRunner.macroTable.remove(0);
            tss.actionPerform();
        }

        StrafeComponent.prevPrevMovingStrafe = StrafeComponent.prevMovingStrafe;
        StrafeComponent.prevMovingStrafe = StrafeComponent.movingStrafe;
        StrafeComponent.movingStrafe = StrafeComponent.isMovingStrafe();

        StrafeComponent.prevPrevMovingStraight = StrafeComponent.prevMovingStraight;
        StrafeComponent.prevMovingStraight = StrafeComponent.movingStraight;
        StrafeComponent.movingStraight = StrafeComponent.isMovingStraight();

        StrafeComponent.prevPrevRotationYaw = StrafeComponent.prevRotationYaw;
        StrafeComponent.prevRotationYaw = StrafeComponent.rotationYaw;
        StrafeComponent.rotationYaw = rotationYaw;

        TimerComponent.prevOnGround = TimerComponent.onGround;
        TimerComponent.onGround = onGround;

        TimerComponent.prevPrevMoved = TimerComponent.prevMoved;
        TimerComponent.prevMoved = TimerComponent.moved;
        TimerComponent.moved = TimerComponent.movementJudgement();

        TimerComponent.prevPrevMovingF = TimerComponent.prevMovingF;
        TimerComponent.prevMovingF = TimerComponent.movingF;
        TimerComponent.movingF = TimerComponent.movingForward();

        TimerComponent.prevMovingB = TimerComponent.movingB;
        TimerComponent.movingB = TimerComponent.movingBackward();

        TimerComponent.prevPrevIsSneaking = TimerComponent.prevIsSneaking;
        TimerComponent.prevIsSneaking = TimerComponent.isSneaking;
        TimerComponent.isSneaking = isSneaking();

        TimerComponent.prevIsSprinting = TimerComponent.isSprinting;
        TimerComponent.isSprinting = isSprinting();

        TimerComponent.prevPosX = TimerComponent.posX;
        TimerComponent.posX = posX;

        TimerComponent.prevPosZ = TimerComponent.posZ;
        TimerComponent.posZ = posZ;

        if (!TimerComponent.onGround && TimerComponent.prevOnGround) {
            TimerComponent.timing = 10;
        }
        else if (!TimerComponent.prevOnGround && !TimerComponent.onGround && !Minecraft.getMinecraft().thePlayer.capabilities.isFlying) {
            TimerComponent.timing--;
            TimerComponent.groundTiming = TimerComponent.groundTimingBW = -1;
        }
        else if (!TimerComponent.prevOnGround && !TimerComponent.onGround) {
            TimerComponent.groundTiming = TimerComponent.groundTimingBW = -1;
        }
        else if (!TimerComponent.moved) {
            TimerComponent.groundTiming = TimerComponent.groundTimingBW = -1;
            TimerComponent.groundSneakingTiming = TimerComponent.groundSneakingTimingBW = -2;
        }
        else {
            if (TimerComponent.movingF) {
                TimerComponent.groundTiming++;
                TimerComponent.groundTimingBW = -1;
                TimerComponent.groundSneakingTiming++;
                TimerComponent.groundSneakingTimingBW = -2;
                if (!TimerComponent.prevPrevIsSneaking) {
                    TimerComponent.groundSneakingTiming = -2;
                }
            }
            else if (TimerComponent.movingB) {
                TimerComponent.groundTiming = -1;
                TimerComponent.groundTimingBW++;
                TimerComponent.groundSneakingTiming = -2;
                TimerComponent.groundSneakingTimingBW++;
                if (!TimerComponent.prevPrevIsSneaking) {
                    TimerComponent.groundSneakingTimingBW = -2;
                }
            }
        }
    }
}
