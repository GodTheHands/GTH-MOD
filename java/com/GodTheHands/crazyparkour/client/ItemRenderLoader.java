package com.GodTheHands.crazyparkour.client;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.fluid.FluidLoader;
import com.GodTheHands.crazyparkour.item.ItemLoader;

public class ItemRenderLoader {
    public ItemRenderLoader()
    {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
        FluidLoader.registerRenders();
    }
}
