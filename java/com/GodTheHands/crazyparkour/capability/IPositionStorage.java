package com.GodTheHands.crazyparkour.capability;

public interface IPositionStorage {
    String getX();
    String getY();
    String getZ();
    String getYaw();
    String getPitch();

    void setX(String x);
    void setY(String y);
    void setZ(String z);
    void setYaw(String yaw);
    void setPitch(String pitch);
}
