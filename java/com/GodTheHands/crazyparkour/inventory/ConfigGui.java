package com.GodTheHands.crazyparkour.inventory;

import com.GodTheHands.crazyparkour.CrazyParkour;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ConfigGui implements IModGuiFactory {
    @Override
    public void initialize(Minecraft mc) { }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return Collections.emptySet();
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return TopLevelConfigGui.class;
    }

    public static class TopLevelConfigGui extends GuiConfig
    {
        public TopLevelConfigGui(GuiScreen parentScreen) {
            super(parentScreen, getConfigElements(), CrazyParkour.MODID, false, false, I18n.format("config.sample.title"));
        }

        private static List<IConfigElement> getConfigElements() {
            List<IConfigElement> list = new ArrayList<>();

            list.add(new DummyConfigElement.DummyCategoryElement("displaySettings", "config.ctgy.displaySettings", displaySettingsEntry.class));
            list.add(new DummyConfigElement.DummyCategoryElement("gameSettings", "config.ctgy.gameSettings", gameSettingsEntry.class));

            return list;
        }

        public static class displaySettingsEntry extends GuiConfigEntries.CategoryEntry {
            public displaySettingsEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
                super(owningScreen, owningEntryList, prop);
            }

            @Override
            protected GuiScreen buildChildScreen()
            {
                return new GuiConfig(this.owningScreen,
                        (new ConfigElement(ConfigLoader.config.getCategory("displaysettings"))).getChildElements(),
                        this.owningScreen.modID, "displaysettings", this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                        this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                        GuiConfig.getAbridgedConfigPath(ConfigLoader.config.toString()));
            }
        }

        public static class gameSettingsEntry extends GuiConfigEntries.CategoryEntry {
            public gameSettingsEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
                super(owningScreen, owningEntryList, prop);
            }

            @Override
            protected GuiScreen buildChildScreen()
            {
                return new GuiConfig(this.owningScreen,
                        (new ConfigElement(ConfigLoader.config.getCategory("gamesettings"))).getChildElements(),
                        this.owningScreen.modID, "gamesettings", this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                        this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                        GuiConfig.getAbridgedConfigPath(ConfigLoader.config.toString()));
            }
        }
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }
}
