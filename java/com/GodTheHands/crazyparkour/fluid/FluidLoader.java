package com.GodTheHands.crazyparkour.fluid;

import com.GodTheHands.crazyparkour.CrazyParkour;
import com.GodTheHands.crazyparkour.block.BlockLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FluidLoader {
    public static Fluid fluidWater = new FluidWater();
    public static Fluid fluidLava = new FluidLava();

    public FluidLoader(FMLPreInitializationEvent event)
    {
        if (FluidRegistry.isFluidRegistered(fluidWater))
        {
            event.getModLog().info("Found fluid {}, the registration is canceled. ", fluidWater.getName());
            fluidWater = FluidRegistry.getFluid(fluidWater.getName());
            event.getModLog().info("Found fluid {}, the registration is canceled. ", fluidLava.getName());
            fluidLava = FluidRegistry.getFluid(fluidLava.getName());
        }
        else
        {
            FluidRegistry.registerFluid(fluidWater);
            FluidRegistry.registerFluid(fluidLava);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerFluidRender((BlockFluidBase) BlockLoader.fluidWater, "fluid_water");
        registerFluidRender((BlockFluidBase) BlockLoader.fluidLava, "fluid_lava");
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidRender(BlockFluidBase blockFluid, String blockStateName)
    {
        final String location = CrazyParkour.MODID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> new ModelResourceLocation(location, "fluid"));
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation(location, "fluid");
            }
        });
    }
}
