package com.GodTheHands.crazyparkour.capability;

import net.minecraft.util.Vec3;

public interface IPositionHistory {
    Vec3 getPosition();
    float getRotationYaw();
    float getRotationPitch();

    void setPosition(Vec3 position);
    void setRotationYaw(float yaw);
    void setRotationPitch(float pitch);
}
