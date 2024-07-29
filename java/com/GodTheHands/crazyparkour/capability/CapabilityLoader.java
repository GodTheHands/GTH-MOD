package com.GodTheHands.crazyparkour.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {
    @CapabilityInject(IPositionHistory.class)
    public static Capability<IPositionHistory> positionHistory;

    @CapabilityInject(IPositionStorage.class)
    public static Capability<IPositionStorage> positionStorage;

    public CapabilityLoader(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IPositionHistory.class, new CapabilityPositionHistory.Storage(), CapabilityPositionHistory.Implementation.class);
        CapabilityManager.INSTANCE.register(IPositionStorage.class, new CapabilityPositionStorage.Storage(), CapabilityPositionStorage.Implementation.class);
    }
}
