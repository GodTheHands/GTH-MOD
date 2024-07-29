package com.GodTheHands.crazyparkour.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ConfigLoader {

    public static Configuration config;

    private static Logger logger;

    public static int colorForHardenedClay;
    public static int colorForGlassPane;
    public static int precisionDigit;
    public static int prefixColor;
    public static int RGBFontColor;

    public static boolean god_mode;
    public static boolean quick_leave;
    public static boolean toggle_sprint;
    public static boolean auto_judgement;
    public static boolean ld_killer;

    public static String macro_name;

    public ConfigLoader(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    public static void load()
    {
        logger.info("Started loading config. ");

        List<String> propOrder = new ArrayList<>();
        Property prop;

        prop = config.get("displaysettings", "colorForHardenedClay", 0,
                "Color meta for hardened clay. ", 0, 15).setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        prop.setLanguageKey("config.sample.hardenedClay");
        colorForHardenedClay = prop.getInt(0);
        propOrder.add(prop.getName());

        prop = config.get("displaysettings", "colorForGlassPane", 0,
                "Color meta for glass pane. ", 0, 15).setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        prop.setLanguageKey("config.sample.glassPane");
        colorForGlassPane = prop.getInt(0);
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "god_mode", false,
                "Determine whether the player will hurt. ");
        prop.setLanguageKey("config.sample.god_mode");
        god_mode = prop.getBoolean(false);
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "quick_leave", false,
                "To avoid the window to be lagged. ");
        prop.setLanguageKey("config.sample.quick_leave");
        quick_leave = prop.getBoolean(false);
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "toggle_sprint", false,
                "Determine whether to use toggle sprint. ");
        prop.setLanguageKey("config.sample.toggle_sprint");
        toggle_sprint = prop.getBoolean(false);
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "precisionDigit", 5,
                "Determine how many digit you want to display. ", 3, 15).setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        prop.setLanguageKey("config.sample.precisionDigit");
        precisionDigit = prop.getInt(5);
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "prefixColor", Color.WHITE.getRGB(),
                "Determine the RGB for prefix. ");
        prop.setLanguageKey("config.sample.prefixColor");
        prefixColor = prop.getInt(Color.WHITE.getRGB());
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "RGBFontColor", Color.WHITE.getRGB(),
                "Determine the RGB for font. ");
        prop.setLanguageKey("config.sample.RGBFontColor");
        RGBFontColor = prop.getInt(Color.WHITE.getRGB());
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "macro_name", "",
                "The macro you want to use. ");
        prop.setLanguageKey("config.sample.macro_name");
        macro_name = prop.getString();
        propOrder.add(prop.getName());

        prop = config.get("gamesettings", "auto_judgement", false,
                "Enable/Disable auto judgement. ");
        prop.setLanguageKey("config.sample.auto_judgement");
        auto_judgement = prop.getBoolean(false);
        propOrder.add(prop.getName());

        config.save();

        logger.info("Finished loading config. ");
    }

    public static Logger logger()
    {
        return logger;
    }
}
