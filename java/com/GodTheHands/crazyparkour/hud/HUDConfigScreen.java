package com.GodTheHands.crazyparkour.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HUDConfigScreen extends GuiScreen {
    private final HashMap<IRender, ScreenPosition> renderers = new HashMap<>();
    private HUDManager api;

    private Optional<IRender> selectedRenderer = Optional.empty();
    private int prevX, prevY;

    public HUDConfigScreen(HUDManager api) {
        this.api = api;
        Collection<IRender> registeredRenderers = api.getRegisteredRender();

        for (IRender r : registeredRenderers) {
            if (!r.isEnabled()) {
                continue;
            }

            ScreenPosition pos = r.load();

            if (pos == null) {pos = ScreenPosition.fromRelativePosition(0.5, 0.5);}

            adjustBounds(r, pos);
            this.renderers.put(r, pos);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        final float zBackup = this.zLevel;
        this.zLevel = 200;

        this.drawHollowRect(0, 0, this.width - 1, this.height - 1, 0xFFFF0000);

        for (IRender renderer : renderers.keySet()) {
            ScreenPosition pos = renderers.get(renderer);

            renderer.renderDummy(pos);

            this.drawHollowRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, renderer.getWidth() + 2, renderer.getHeight() + 1, 0xFF00FFFF);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);

        this.zLevel = zBackup;
    }

    private void drawHollowRect(int absX, int absY, int width, int height, int color) {
        this.drawHorizontalLine(absX, absX + width, absY, color);
        this.drawHorizontalLine(absX, absX + width, absY + height, color);

        this.drawVerticalLine(absX, absY, absY + height, color);
        this.drawVerticalLine(absX + width, absY, absY + height, color);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            renderers.entrySet().forEach((entry)-> entry.getKey().save(entry.getValue()));
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (selectedRenderer.isPresent()) {
            moveSelectedRenderBy(mouseX - prevX, mouseY - prevY);
        }

        this.prevX = mouseX;
        this.prevY = mouseY;
    }

    private void moveSelectedRenderBy(int dx, int dy) {
        IRender render = selectedRenderer.get();
        ScreenPosition pos = renderers.get(render);

        pos.setAbsolute(pos.getAbsoluteX() + dx, pos.getAbsoluteY() + dy);
        adjustBounds(render, pos);
    }

    @Override
    public void onGuiClosed() {
        for (IRender render : renderers.keySet()) {
            render.save(renderers.get(render));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void adjustBounds(IRender render, ScreenPosition pos) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();

        int absX = Math.max(3, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - render.getWidth() - 2, 3)));
        // Collide Cascade
        List<IRender> lista = api.getRegisteredRender().stream().filter(irender -> (absX <= irender.load().getAbsoluteX() && absX + render.getWidth() >= irender.load().getAbsoluteX()) || (absX <= irender.load().getAbsoluteX() + irender.getWidth() && absX +render.getWidth() >= irender.load().getAbsoluteX() + irender.getWidth())).filter(irender -> irender != render).sorted(Comparator.comparingInt(p -> p.load().getAbsoluteY())).collect(Collectors.toList());
        int down = render.load().getAbsoluteY();
        int up = render.load().getAbsoluteY();
        for (IRender r : lista) {
            if ((down > r.load().getAbsoluteY() && down < r.load().getAbsoluteY() + r.getHeight()) || (down + render.getHeight() > r.load().getAbsoluteY() && down + render.getHeight() < r.load().getAbsoluteY() + r.getHeight())) {
                down = r.load().getAbsoluteY() - render.getHeight();
            };
        }
        lista = lista.stream().sorted(Comparator.comparingInt(p -> -p.load().getAbsoluteY())).collect(Collectors.toList());
        for (IRender r : lista) {
            if ((up > r.load().getAbsoluteY() && up < r.load().getAbsoluteY() + r.getHeight()) || (up + render.getHeight() > r.load().getAbsoluteY() && up + render.getHeight() < r.load().getAbsoluteY() + r.getHeight())) {
                up = r.load().getAbsoluteY() + r.getHeight();
            };
        };

        int absY = (up + down > 2 * render.load().getAbsoluteY() ? down : up);
        absY = Math.max(3, absY);
        absY = Math.min(absY, screenHeight - render.getHeight() - 1);

        //int absY = Math.max(3, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - render.getHeight() - 1, 3)));

        pos.setAbsolute(absX, absY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.prevX = mouseX;
        this.prevY = mouseY;

        loadMouseOver(mouseX, mouseY);
    }

    private void loadMouseOver(int mouseX, int mouseY) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(mouseX, mouseY)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRender> {
        private int mouseX, mouseY;

        public MouseOverFinder(int mouseX, int mouseY) {
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }

        @Override
        public boolean test(IRender iRender) {
            ScreenPosition sp = renderers.get(iRender);

            int absX = sp.getAbsoluteX();
            int absY = sp.getAbsoluteY();
            // Mouse Overbound Checker
            if (mouseX >= absX && mouseX <= absX + iRender.getWidth()) {
                return mouseY >= absY && mouseY <= absY + iRender.getHeight();
            }
            return false;
        }
    }
}
