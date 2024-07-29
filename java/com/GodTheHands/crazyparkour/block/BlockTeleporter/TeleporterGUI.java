package com.GodTheHands.crazyparkour.block.BlockTeleporter;

import com.GodTheHands.crazyparkour.capability.CapabilityLoader;
import com.GodTheHands.crazyparkour.capability.IPositionStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TeleporterGUI extends GuiScreen {
    private final World worldIn;
    private final BlockPos pos;
    private final IBlockState state;

    private ArrayList<String> informations = new ArrayList<>(5);
    private ArrayList<GuiTextField> guiTextFields = new ArrayList<>();

    private FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
    private static int mouseIn = 1;
    private static int cursorIn = 0;

    public TeleporterGUI(World worldIn, BlockPos pos, IBlockState state) {
        this.worldIn = worldIn;
        this.pos = pos;
        this.state = state;

        for (int i = 0; i < 5; i++) {
            informations.add(i, "0");
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        this.drawDefaultBackground();
        //Init background
        this.drawGradientRect(sr.getScaledWidth() * 3 / 10, sr.getScaledHeight() / 3, sr.getScaledWidth() * 7 / 10, sr.getScaledHeight() * 2 / 3, Color.BLACK.getRGB(), Color.BLACK.getRGB());
        this.drawVerticalLine(sr.getScaledWidth() * 7 / 10, sr.getScaledHeight() / 3, sr.getScaledHeight() * 2 / 3, -1);
        this.drawVerticalLine(sr.getScaledWidth() * 3 / 10, sr.getScaledHeight() / 3, sr.getScaledHeight() * 2 / 3, -1);
        this.drawHorizontalLine(sr.getScaledWidth() * 3 / 10, sr.getScaledWidth() * 7 / 10, sr.getScaledHeight() / 3, -1);
        this.drawHorizontalLine(sr.getScaledWidth() * 3 / 10, sr.getScaledWidth() * 7 / 10, sr.getScaledHeight() * 2 / 3, -1);
        //Custom
        for (int i = 0; i < 5; i++) {
            guiTextFields.get(i).drawTextBox();
        }

        font.drawStringWithShadow("X", sr.getScaledWidth() * 2 / 5 - font.getStringWidth("X") / 2, sr.getScaledHeight() / 3 + font.FONT_HEIGHT / 2, -1);
        font.drawStringWithShadow("Y", sr.getScaledWidth() * 2 / 5 - font.getStringWidth("Y") / 2, sr.getScaledHeight() / 3 + sr.getScaledHeight() / 15 + font.FONT_HEIGHT / 2, -1);
        font.drawStringWithShadow("Z", sr.getScaledWidth() * 2 / 5 - font.getStringWidth("Z") / 2, sr.getScaledHeight() / 3 + sr.getScaledHeight() * 2 / 15 + font.FONT_HEIGHT / 2, -1);
        font.drawStringWithShadow("Yaw", sr.getScaledWidth() * 2 / 5 - font.getStringWidth("Yaw") / 2, sr.getScaledHeight() / 3 + sr.getScaledHeight() / 5 + font.FONT_HEIGHT / 2, -1);
        font.drawStringWithShadow("Pitch", sr.getScaledWidth() * 2 / 5 - font.getStringWidth("Pitch") / 2, sr.getScaledHeight() / 3 + sr.getScaledHeight() * 4 / 15 + font.FONT_HEIGHT / 2, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        load();

        initTextField();

        super.initGui();
    }

    private void load() {
        if (worldIn.getTileEntity(pos) == null) {
            return;
        }

        TileEntityBlockTeleporter tebt = (TileEntityBlockTeleporter)worldIn.getTileEntity(pos);

        if (tebt.hasCapability(CapabilityLoader.positionStorage, null)) {
            IPositionStorage tebte = tebt.getCapability(CapabilityLoader.positionStorage, null);
            informations.add(0, tebte.getX());
            informations.add(1, tebte.getY());
            informations.add(2, tebte.getZ());
            informations.add(3, tebte.getYaw());
            informations.add(4, tebte.getPitch());
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        GuiTextField gtf = guiTextFields.get(mouseIn - 1);

        if (gtf.textboxKeyTyped(typedChar, keyCode)) {
            int offset = informations.get(mouseIn - 1).length();
            this.textFieldActionPerformer(gtf);

            offset -= informations.get(mouseIn - 1).length();
            cursorIn -= offset;
        }

        if (keyCode == Keyboard.KEY_LEFT) {
            leftMoveCursor(gtf);
        }
        else if (keyCode == Keyboard.KEY_RIGHT) {
            rightMoveCursor(gtf);
        }
        else if (keyCode == Keyboard.KEY_DOWN) {
           if (mouseIn == 5) {
               mouseIn = 1;
           }
           else {
               mouseIn++;
           }

           if (cursorIn > informations.get(mouseIn - 1).length()) {
               cursorIn = informations.get(mouseIn - 1).length();
           }
        }
        else if (keyCode == Keyboard.KEY_UP) {
           if (mouseIn == 1) {
               mouseIn = 5;
           }
           else {
               mouseIn--;
           }

            if (cursorIn > informations.get(mouseIn - 1).length()) {
                cursorIn = informations.get(mouseIn - 1).length();
            }
        }

        mc.displayGuiScreen(new TeleporterGUI(worldIn, pos, state));

        super.keyTyped(typedChar, keyCode);
    }

    private void initTextField() {
        ScaledResolution sr = new ScaledResolution(mc);

        for (int i = 0; i < 5; i++) {
            GuiTextField tempTextField = new GuiTextField(i, font, sr.getScaledWidth() / 2, sr.getScaledHeight() / 3 + sr.getScaledHeight() * i / 15, sr.getScaledWidth() / 5, sr.getScaledHeight() / 15);

            if (i == mouseIn - 1) {
                tempTextField.setTextColor(Color.RED.getRGB());
                tempTextField.setFocused(true);
            }
            else {
                tempTextField.setTextColor(-1);
                tempTextField.setFocused(false);
            }

            tempTextField.setText(informations.get(i));

            tempTextField.setCursorPosition(cursorIn);

            guiTextFields.add(i, tempTextField);
        }
    }

    private void textFieldActionPerformer(GuiTextField gtf) {
        String s = gtf.getText();

        try {
            if (mouseIn >= 1 && mouseIn <= 3) {
                Double.parseDouble(s);
            }
            else {
                Float.parseFloat(s);
            }

            if (s.matches("[+-]?\\d+.?(\\d+)?")) {
                informations.set(mouseIn - 1, s);
            }
        } catch(java.lang.NumberFormatException e) {
            // Nothing should be done
        }
    }

    @Override
    public void onGuiClosed() {
        save();
    }

    private void save() {
        if (worldIn.getTileEntity(pos) == null) {
            return;
        }

        TileEntityBlockTeleporter tebt = (TileEntityBlockTeleporter)worldIn.getTileEntity(pos);
        if (tebt.hasCapability(CapabilityLoader.positionStorage, null)) {
            IPositionStorage tebte = tebt.getCapability(CapabilityLoader.positionStorage, null);
            tebte.setX(informations.get(0));
            tebte.setY(informations.get(1));
            tebte.setZ(informations.get(2));
            tebte.setYaw(informations.get(3));
            tebte.setPitch(informations.get(4));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        ScaledResolution SR = new ScaledResolution(Minecraft.getMinecraft());

        if (mouseX >= SR.getScaledWidth() / 2 && mouseX <= SR.getScaledWidth() * 7 / 10
        && mouseY >= SR.getScaledHeight() / 3 && mouseY <= SR.getScaledHeight() * 2 / 5) {
            mouseIn = 1;
        }
        else if (mouseX >= SR.getScaledWidth() / 2 && mouseX <= SR.getScaledWidth() * 7 / 10
                && mouseY >= SR.getScaledHeight() * 2 / 5 && mouseY <= SR.getScaledHeight() * 7 / 15) {
            mouseIn = 2;
        }
        else if (mouseX >= SR.getScaledWidth() / 2 && mouseX <= SR.getScaledWidth() * 7 / 10
                && mouseY >= SR.getScaledHeight() * 7 / 15 && mouseY <= SR.getScaledHeight() * 8 / 15) {
            mouseIn = 3;
        }
        else if (mouseX >= SR.getScaledWidth() / 2 && mouseX <= SR.getScaledWidth() * 7 / 10
                && mouseY >= SR.getScaledHeight() * 8 / 15 && mouseY <= SR.getScaledHeight() * 3 / 5) {
            mouseIn = 4;
        }
        else if (mouseX >= SR.getScaledWidth() / 2 && mouseX <= SR.getScaledWidth() * 7 / 10
                && mouseY >= SR.getScaledHeight() * 3 / 5 && mouseY <= SR.getScaledHeight() * 2 / 3) {
            mouseIn = 5;
        }

        if (cursorIn > informations.get(mouseIn - 1).length()) {
            cursorIn = informations.get(mouseIn - 1).length();
        }

        initTextField();
    }

    public void rightMoveCursor(GuiTextField gtf) {
        if (cursorIn <= gtf.getText().length()) {
            cursorIn++;
        }
        else {
            cursorIn = 0;
        }
    }

    public void leftMoveCursor(GuiTextField gtf) {
        if (cursorIn > 0) {
            cursorIn--;
        }
        else {
            cursorIn = gtf.getText().length();
        }
    }
}
