package com.GodTheHands.crazyparkour.hud;

import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class HUDManager {
    private Minecraft minecraft = Minecraft.getMinecraft();

    private HUDManager() {}

    private static HUDManager instance = null;

    public static HUDManager getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new HUDManager();

        return instance;
    }

    private Set<IRender> registerRenderers = Sets.newHashSet();

    public void register(IRender... renderers) {
        this.registerRenderers.addAll(Arrays.asList(renderers));
    }

    public void unregister(IRender... renderers) {
        for (IRender render : renderers) {
            this.registerRenderers.remove(render);
        }
    }

    public Collection<IRender> getRegisteredRender() {
        return Sets.newHashSet(registerRenderers);
    }

    public void openConfigScreen() {
        minecraft.displayGuiScreen(new HUDConfigScreen(this));
    }

    public void onRender() {
        if (minecraft.currentScreen == null) {
            for (IRender renders : registerRenderers) {
                callRenderer(renders);
            }
        }
    }

    private void callRenderer(IRender render) {
        if (!render.bload()) {return;}

        ScreenPosition pos = render.load();

        if (pos == null) {pos = ScreenPosition.fromRelativePosition(0.5, 0.5);}

        render.render(pos);
    }
}
