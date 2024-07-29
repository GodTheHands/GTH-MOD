package com.GodTheHands.crazyparkour.item;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.fluid.FluidLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ItemLavaBucket extends ItemBucket {
    public ItemLavaBucket() {
        super(BlockLoader.fluidLava);
        this.setContainerItem(Items.bucket);
        this.setUnlocalizedName("bucketLava");

        FluidContainerRegistry.registerFluidContainer(FluidLoader.fluidLava, new ItemStack(this), FluidContainerRegistry.EMPTY_BUCKET);
    };
}
