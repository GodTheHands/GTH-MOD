package com.GodTheHands.crazyparkour.ifmltweaker;

import com.GodTheHands.crazyparkour.CrazyParkour;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion(value = "1.8.9")
public class FMLTweaker implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public FMLTweaker() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins." + CrazyParkour.MODID + ".json");
    }
}