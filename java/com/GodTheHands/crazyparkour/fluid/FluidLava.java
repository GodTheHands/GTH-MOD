package com.GodTheHands.crazyparkour.fluid;

import com.GodTheHands.crazyparkour.CrazyParkour;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLava extends Fluid {
    public static final ResourceLocation still = new ResourceLocation(CrazyParkour.MODID + ":" + "fluid/lava_still");
    public static final ResourceLocation flowing = new ResourceLocation(CrazyParkour.MODID + ":" + "fluid/lava_flow");

    public FluidLava() {
        super("lava", FluidWater.still, FluidWater.flowing);
        this.setUnlocalizedName("fluidLava");
    }
}