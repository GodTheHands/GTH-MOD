package com.GodTheHands.crazyparkour.item;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.fluid.FluidLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ItemWaterBucket extends ItemBucket {
    public ItemWaterBucket() {
        super(BlockLoader.fluidWater);
        this.setContainerItem(Items.bucket);
        this.setUnlocalizedName("bucketWater");

        FluidContainerRegistry.registerFluidContainer(FluidLoader.fluidWater, new ItemStack(this), FluidContainerRegistry.EMPTY_BUCKET);
    };
}
