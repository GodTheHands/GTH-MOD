package com.GodTheHands.crazyparkour.hud.component;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.hud.FileManager;
import com.GodTheHands.crazyparkour.hud.HUDMainScreen;
import com.GodTheHands.crazyparkour.hud.IRender;
import com.GodTheHands.crazyparkour.hud.ScreenPosition;

import java.io.File;
import java.text.DecimalFormat;

public abstract class DraggableComponent extends HUDMainScreen implements IRender {

    protected ScreenPosition pos;

    public DraggableComponent() {
        pos = loadPositionFromFile();
        setEnabled(loadEnabledFromFile());
    }

    private File getFolder() {
        File folder = new File(FileManager.getScreensDir(), this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    private ScreenPosition loadPositionFromFile() {
        ScreenPosition loaded = FileManager.readFromJson(new File(getFolder(), "position.json"), ScreenPosition.class);

        if (loaded == null) {
            loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
            this.pos = loaded;
            savePositionToFile();
        }

        return loaded;
    }

    private boolean loadEnabledFromFile() {
        Boolean isEnabled = FileManager.readFromJson(new File(getFolder(), "enable.json"), Boolean.class);

        if (isEnabled == null) {
            isEnabled = true;
            setEnabled(isEnabled);
            saveEnabledToFile();
        }

        return isEnabled;
    }

    private void savePositionToFile() {
        FileManager.writeJsonToFile(new File(getFolder(),"position.json"), pos);
    }

    private void saveEnabledToFile() {
        FileManager.writeJsonToFile(new File(getFolder(), "enable.json"), isEnabled());
    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
        savePositionToFile();
    }

    @Override
    public ScreenPosition load() {
        return pos;
    }

    @Override
    public void save(boolean enabled) {
        this.setEnabled(enabled);
        saveEnabledToFile();
    }

    @Override
    public boolean bload() {
        return isEnabled();
    }

    public String roundByScale(double floatNumber) {
        int scale = ConfigLoader.precisionDigit;

        if (scale == 0) {
            return new DecimalFormat("0").format(floatNumber);
        }

        StringBuilder formatStr = new StringBuilder("0.");

        for (int i = 0; i < scale; i++) {
            formatStr.append("0");
        }
        return new DecimalFormat(formatStr.toString()).format(floatNumber);
    }
}
