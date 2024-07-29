package com.GodTheHands.crazyparkour.fluid;

import com.GodTheHands.crazyparkour.CrazyParkour;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidWater extends Fluid {
    public static final ResourceLocation still = new ResourceLocation(CrazyParkour.MODID + ":" + "fluid/water_still");
    public static final ResourceLocation flowing = new ResourceLocation(CrazyParkour.MODID + ":" + "fluid/water_flow");

    public FluidWater()
    {
        super("water", FluidWater.still, FluidWater.flowing);
        this.setUnlocalizedName("fluidWater");
    }


}
