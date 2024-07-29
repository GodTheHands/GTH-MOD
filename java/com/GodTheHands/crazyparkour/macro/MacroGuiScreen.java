package com.GodTheHands.crazyparkour.macro;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MacroGuiScreen extends GuiScreen {
    static class tickPair {
        public static int lowerBound = -1; //Invalid argument
        public static int upperBound = -1; //Invalid argument
    }

    private static ArrayList<ArrayList<Integer>> passwords;
    private static ArrayList<ArrayList<String>> dxs;
    private static ArrayList<GuiTextField> guiTextFields = new ArrayList<>();
    private static FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
    private static int page;
    private static int ticks;
    private static int mouseIn;
    public static MacroConfigSaver macroConfigSaver;
    private static int clickMouseX, clickMouseY;
    private static int releaseMouseX, releaseMouseY;
    private static ArrayList<Integer> bufferPwds = new ArrayList<>(15);
    private static ArrayList<String> bufferDxs = new ArrayList<>(15);

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        this.drawDefaultBackground();
        //Init background
        this.drawGradientRect(sr.getScaledWidth() / 10, 0, sr.getScaledWidth() * 9 / 10, sr.getScaledHeight(), Color.BLACK.getRGB(), Color.BLACK.getRGB());
        this.drawVerticalLine(sr.getScaledWidth() / 10, 0, sr.getScaledHeight(), -1);
        this.drawVerticalLine(sr.getScaledWidth() * 9 / 10, 0, sr.getScaledHeight(), -1);
        this.drawVerticalLine(sr.getScaledWidth() * 7 / 10, 0, sr.getScaledHeight(), -1);
        this.drawVerticalLine(sr.getScaledWidth() / 2, 0, sr.getScaledHeight(), -1);
        //Custom
        updatePair();
        drawWithRecord();
        drawKeyState();

        FakeKeyStroke.render(new TickStateSaver(macroConfigSaver.passwords.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1), macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1)));

        font.drawStringWithShadow("Page " + macroConfigSaver.currentPage, (float)sr.getScaledWidth() * 7 / 10 + 5, 5, -1);
        font.drawStringWithShadow("Tick " + (macroConfigSaver.currentIn - 15 + 15 * macroConfigSaver.currentPage), (float)sr.getScaledWidth() * 7 / 10 + 5, 25, -1);

        for (int i = 0; i < ticks; i++) {
            guiTextFields.get(i).drawTextBox();
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        macroConfigSaver = MacroConfigSaver.loadMCSFromFile();

        page = macroConfigSaver.currentPage;
        ticks = Math.min(macroConfigSaver.currentTicks + 15 - 15 * macroConfigSaver.currentPage, 15);
        mouseIn = macroConfigSaver.currentIn;
        passwords = macroConfigSaver.passwords;
        dxs = macroConfigSaver.dxs;

        initTextField();

        super.initGui();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        TickStateSaver tss = new TickStateSaver(passwords.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1), dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1));
        GuiTextField gtf = guiTextFields.get(macroConfigSaver.currentIn - 1);

        if (gtf.textboxKeyTyped(typedChar, keyCode)) {
            int offset = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
            this.textFieldActionPerformer(gtf);

            offset -= macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
            macroConfigSaver.cursorIn -= offset;
        }

        if (keyCode == mc.gameSettings.keyBindForward.getKeyCode()) {
            tss.isWDown = tss.toggle(tss.isWDown);
            stateSaver(tss);
        }
        else if (keyCode == mc.gameSettings.keyBindLeft.getKeyCode()) {
            tss.isADown = tss.toggle(tss.isADown);
            stateSaver(tss);
        }
        else if (keyCode == mc.gameSettings.keyBindBack.getKeyCode()) {
            tss.isSDown = tss.toggle(tss.isSDown);
            stateSaver(tss);
        }
        else if (keyCode == mc.gameSettings.keyBindRight.getKeyCode()) {
            tss.isDDown = tss.toggle(tss.isDDown);
            stateSaver(tss);
        }
        else if (keyCode == mc.gameSettings.keyBindJump.getKeyCode()) {
            tss.isSpaceDown = tss.toggle(tss.isSpaceDown);
            stateSaver(tss);
        }
        else if (keyCode == mc.gameSettings.keyBindSneak.getKeyCode()) {
            tss.isShiftDown = tss.toggle(tss.isShiftDown);
            stateSaver(tss);
        }
        else if (keyCode == mc.gameSettings.keyBindSprint.getKeyCode()) {
            tss.isSprintDown = tss.toggle(tss.isSprintDown);
            stateSaver(tss);
        }
        else if (keyCode == Keyboard.KEY_RETURN) {
            macroConfigSaver.addCurrentIn();
            macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
        }
        else if (keyCode == Keyboard.KEY_DOWN) {
            macroConfigSaver.addCurrentInWithoutModify();
            macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
        }
        else if (keyCode == Keyboard.KEY_UP) {
            macroConfigSaver.prevCurrentIn();
            macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
        }
        else if (keyCode == Keyboard.KEY_DELETE) {
            macroConfigSaver.deleteElement(macroConfigSaver.currentIn - 16 + 15 * macroConfigSaver.currentPage);
            macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
        }
        else if (keyCode == Keyboard.KEY_INSERT) {
            macroConfigSaver.insertElement(macroConfigSaver.currentIn - 16 + 15 * macroConfigSaver.currentPage);
            macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
        }
        else if (keyCode == Keyboard.KEY_LEFT) {
            macroConfigSaver.leftMoveCursor(gtf);
        }
        else if (keyCode == Keyboard.KEY_RIGHT) {
            macroConfigSaver.rightMoveCursor(gtf);
        }
        else if (keyCode == Keyboard.KEY_PRIOR) {
            macroConfigSaver.newPageUp();
        }
        else if (keyCode == Keyboard.KEY_NEXT) {
            macroConfigSaver.newPageDown();
        }
        else if (keyCode == Keyboard.KEY_RSHIFT) {
            macroConfigSaver.duplicateTable(macroConfigSaver.currentIn - 15 + 15 * macroConfigSaver.currentPage);
        }
        else if (Keyboard.isKeyDown(46) && isCtrlKeyDown() && tickPair.lowerBound != -1) {
            copyTicks();
        }
        else if (Keyboard.isKeyDown(47) && isCtrlKeyDown() && tickPair.lowerBound != -1) {
            pasteTicks();
        }

        mc.displayGuiScreen(new MacroGuiScreen());

        super.keyTyped(typedChar, keyCode);
    }

    private void copyTicks() {
        //Refresh
        bufferPwds = new ArrayList<>(15);
        bufferDxs = new ArrayList<>(15);
        //Load
        for (int i = tickPair.lowerBound - 1; i < tickPair.upperBound; i++) {
            bufferPwds.add(passwords.get(macroConfigSaver.currentPage - 1).get(i));
            bufferDxs.add(dxs.get(macroConfigSaver.currentPage - 1).get(i));
        }
    }

    private void pasteTicks() {
        for (int i = 0; i < bufferPwds.size(); i++) {
            macroConfigSaver.insertValue(macroConfigSaver.currentIn - 15 + 15 * macroConfigSaver.currentPage, bufferPwds.get(i), bufferDxs.get(i));
            macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
        }
    }

    private void stateSaver(TickStateSaver tss) {
        ArrayList<Integer> temp = macroConfigSaver.passwords.get(page - 1);
        ArrayList<String> temp2 = macroConfigSaver.dxs.get(page - 1);
        temp.set(mouseIn - 1, tss.formPassword());
        temp2.set(mouseIn - 1, tss.originalDX);
        macroConfigSaver.passwords.set(page - 1, temp);
        macroConfigSaver.dxs.set(page - 1, temp2);
    }

    private void drawWithRecord() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int tickIndex = tickGetter();
        int currentMax = macroConfigSaver.currentTicks - (macroConfigSaver.currentPage - 1) * 15;
        int currentIn = macroConfigSaver.currentIn;

        //White Drawer
        for (int i = 0; i <= ticks; i++) {
            this.drawHorizontalLine(i, -1);
        }
        //Blue Drawer
        for (int i = 0; i <= ticks; i++) {
            if ((i == tickIndex || i == tickIndex - 1) && tickIndex <= currentMax) {
                this.drawHorizontalLine(i, Color.BLUE.getRGB());
                this.drawVerticalLine(sr.getScaledWidth() / 10, sr.getScaledHeight() * (tickIndex - 1) / 15, sr.getScaledHeight() * tickIndex / 15, Color.BLUE.getRGB());
                this.drawVerticalLine(sr.getScaledWidth() / 2, sr.getScaledHeight() * (tickIndex - 1) / 15, sr.getScaledHeight() * tickIndex / 15, Color.BLUE.getRGB());
            }
        }
        //Green Drawer
        this.drawHorizontalLine(tickPair.lowerBound - 1, Color.GREEN.getRGB());
        for (int i = tickPair.lowerBound; i <= tickPair.upperBound; i++) {
            this.drawHorizontalLine(i, Color.GREEN.getRGB());
            this.drawVerticalLine(sr.getScaledWidth() / 10, sr.getScaledHeight() * (i - 1) / 15, sr.getScaledHeight() * i / 15, Color.GREEN.getRGB());
            this.drawVerticalLine(sr.getScaledWidth() / 2, sr.getScaledHeight() * (i - 1) / 15, sr.getScaledHeight() * i / 15, Color.GREEN.getRGB());
        }
        //Red Drawer
        for (int i = 0; i <= ticks; i++) {
            if (i == currentIn || i == currentIn - 1) {
                this.drawHorizontalLine(i, Color.RED.getRGB());
                this.drawVerticalLine(sr.getScaledWidth() / 10, sr.getScaledHeight() * (currentIn - 1) / 15, sr.getScaledHeight() * currentIn / 15, Color.RED.getRGB());
                this.drawVerticalLine(sr.getScaledWidth() / 2, sr.getScaledHeight() * (currentIn - 1) / 15, sr.getScaledHeight() * currentIn / 15, Color.RED.getRGB());
            }
        }
    }

    private void drawHorizontalLine(int position, int color) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        this.drawHorizontalLine(sr.getScaledWidth() / 10, sr.getScaledWidth() / 2, sr.getScaledHeight() * position / 15, color);
    }

    private void drawKeyState() {
        if (macroConfigSaver.currentIn <= ticks) {
            for (int i = 1; i <= ticks; i++) {
                if (i != macroConfigSaver.currentIn) {
                    drawFont(new TickStateSaver(macroConfigSaver.passwords.get(macroConfigSaver.currentPage - 1).get(i - 1), macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(i - 1)),i - 1, -1);
                }
                else {
                    drawFont(new TickStateSaver(macroConfigSaver.passwords.get(macroConfigSaver.currentPage - 1).get(i - 1), macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(i - 1)), i - 1, Color.RED.getRGB());
                }
            }
        }
    }

    private void drawFont(TickStateSaver tss, int YPos, int color) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        font.drawStringWithShadow(tss.drawFont(), sr.getScaledWidth() / 10 + 5, sr.getScaledHeight() * YPos / 15 + 5, color);
    }

    private void initTextField() {
        ScaledResolution sr = new ScaledResolution(mc);

        for (int i = 0; i < ticks; i++) {
            GuiTextField tempTextField = new GuiTextField(i, font, sr.getScaledWidth() / 2, sr.getScaledHeight() * i / 15, sr.getScaledWidth() / 5, sr.getScaledHeight() / 15);

            if (i == macroConfigSaver.currentIn - 1) {
                tempTextField.setTextColor(Color.RED.getRGB());
                tempTextField.setFocused(true);
            }
            else {
                tempTextField.setTextColor(-1);
                tempTextField.setFocused(false);
            }

            tempTextField.setText(macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(i));
            tempTextField.setCursorPosition(macroConfigSaver.cursorIn);

            guiTextFields.add(i, tempTextField);
        }
    }

    private void textFieldActionPerformer(GuiTextField gtf) {
        String s = gtf.getText();

        ArrayList<String> temp = macroConfigSaver.dxs.get(page - 1);

        try {
            Double.parseDouble(s);

            if (s.matches("[+-]?\\d+.?(\\d+)?")) {
                temp.set(mouseIn - 1, s);
            }
        } catch(java.lang.NumberFormatException e) {
            // Nothing should be done here
        }

        dxs.set(page - 1, temp);
    }

    @Override
    public void onGuiClosed() {
        MacroConfigSaver.saveMCSToFile();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        TickStateSaver tss = new TickStateSaver(passwords.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1), dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1));
        ScaledResolution SR = new ScaledResolution(Minecraft.getMinecraft());
        int positionX = SR.getScaledWidth() * 15 / 20;
        int positionY = SR.getScaledHeight() * 15 / 20;
        int tickIndex = tickGetter();
        //LS
        if (mouseX >= positionX + 1 && mouseX < positionX + 19
        && mouseY >= positionY + 1 && mouseY < positionY + 19) {
            tss.isShiftDown = tss.toggle(tss.isShiftDown);
            stateSaver(tss);
        }
        //LC
        if (mouseX >= positionX + 41 && mouseX < positionX + 59
        && mouseY >= positionY + 1 && mouseY < positionY + 19) {
            tss.isSprintDown = tss.toggle(tss.isSprintDown);
            stateSaver(tss);
        }
        //Space
        if (mouseX >= positionX + 1 && mouseX < positionX + 59
        && mouseY >= positionY + 41 && mouseY < positionY + 53) {
            tss.isSpaceDown = tss.toggle(tss.isSpaceDown);
            stateSaver(tss);
        }
        //W
        if (mouseX >= positionX + 21 && mouseX < positionX + 39
        && mouseY >= positionY + 1 && mouseY < positionY + 19) {
            tss.isWDown = tss.toggle(tss.isWDown);
            stateSaver(tss);
        }
        //A
        if (mouseX >= positionX + 1 && mouseX < positionX + 19
        && mouseY >= positionY + 21 && mouseY < positionY + 39) {
            tss.isADown = tss.toggle(tss.isADown);
            stateSaver(tss);
        }
        //S
        if (mouseX >= positionX + 21 && mouseX < positionX + 39
        && mouseY >= positionY + 21 && mouseY < positionY + 39) {
            tss.isSDown = tss.toggle(tss.isSDown);
            stateSaver(tss);
        }
        //D
        if (mouseX >= positionX + 41 && mouseX < positionX + 59
        && mouseY >= positionY + 21 && mouseY < positionY + 39) {
            tss.isDDown = tss.toggle(tss.isDDown);
            stateSaver(tss);
        }
        //Position
        if (tickIndex != -1) {
            if (macroConfigSaver.currentTicks + 15 >= macroConfigSaver.currentPage * 15 + tickIndex) {
                macroConfigSaver.currentIn = tickIndex;
                macroConfigSaver.cursorIn = macroConfigSaver.dxs.get(macroConfigSaver.currentPage - 1).get(macroConfigSaver.currentIn - 1).length();
                initTextField();
            }
        };
        releaseMouseX = clickMouseX = mouseX;
        releaseMouseY = clickMouseY = mouseY;
    }

    private int tickGetter() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        float x = Mouse.getX() / sr.getScaleFactor();
        float y = Mouse.getY() / sr.getScaleFactor();

        int tickIndex = -1;
        if (x >= sr.getScaledWidth() / 10 && x <= sr.getScaledWidth() * 7 / 10) {
            for (int i = 0; i <= 14; i++) {
                if (y >= sr.getScaledHeight() * i / 15 && y <= sr.getScaledHeight() * (i + 1) / 15) {
                    tickIndex = 15 - i;
                    break;
                }
            }
        }

        return tickIndex;
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        releaseMouseX = mouseX;
        releaseMouseY = mouseY;
    }

    private void updatePair() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int currentMax = macroConfigSaver.currentTicks - (macroConfigSaver.currentPage - 1) * 15;

        if (clickMouseX > sr.getScaledWidth() * 7 / 10 && releaseMouseX > sr.getScaledWidth() * 7 / 10) {
            return;
        }

        if (clickMouseX < sr.getScaledWidth() / 10 && releaseMouseX < sr.getScaledWidth() / 10) {
            return;
        }

        int min = (clickMouseY <= releaseMouseY ? clickMouseY : releaseMouseY);
        int max = (clickMouseY >= releaseMouseY ? clickMouseY : releaseMouseY);

        for (int i = 0; i <= 14; i++) {
            if (min >= sr.getScaledHeight() * i / 15 && min <= sr.getScaledHeight() * (i + 1) / 15) {
                tickPair.lowerBound = i + 1;
            }
        }

        for (int i = 0; i <= 14; i++) {
            if (max >= sr.getScaledHeight() * i / 15 && max <= sr.getScaledHeight() * (i + 1) / 15) {
                tickPair.upperBound = i + 1;
            }
        }

        if (tickPair.lowerBound > currentMax) {
            tickPair.lowerBound = tickPair.upperBound = -1;
        }
        else if (tickPair.upperBound > currentMax) {
            tickPair.upperBound = currentMax;
        }
    }
}
