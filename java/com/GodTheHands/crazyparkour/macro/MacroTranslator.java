package com.GodTheHands.crazyparkour.macro;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import java.util.ArrayList;

public class MacroTranslator {
    private static ArrayList<TickStateSaver> macroTable;
    public static ArrayList<String> behaviors = new ArrayList<>();

    public static void load(ArrayList<TickStateSaver> tsss) {
        macroTable = tsss;
    }

    public static ArrayList<TickStateSaver> transformArray(ArrayList<ArrayList<Integer>> runTable, ArrayList<ArrayList<String>> facingTable) {
        ArrayList<TickStateSaver> tsss = new ArrayList<>();

        for (int i = 0; i < runTable.size(); i++) {
            for (int j = 0; j < runTable.get(i).size(); j++) {
                if (i >= 1 && runTable.get(i - 1).size() < 15) {
                    break;
                }
                tsss.add(new TickStateSaver(runTable.get(i).get(j), facingTable.get(i).get(j)));
            }
        }

        return tsss;
    }

    public static void translateMacro() {
        for (int i = 0; i < macroTable.size(); i++) {
            String result = "";
            TickStateSaver tss = macroTable.get(i);

            System.out.println(tss.formPassword());

            if (i == 0) {
                if (tss.isWDown)
                    result += "down('W');\n";
                if (tss.isADown)
                    result += "down('A');\n";
                if (tss.isSDown)
                    result += "down('S');\n";
                if (tss.isDDown)
                    result += "down('D');\n";
                if (tss.isSprintDown)
                    result += "down('Sprint');\n";
                if (tss.isShiftDown)
                    result += "down('Shift');\n";
                if (tss.isSpaceDown)
                    result += "down('Space');\n";
                if (Double.compare(tss.rotationDX, 0.0D) != 0)
                    result += "mouseMove(" + tss.rotationDX + ");\n";
            }
            else {
                TickStateSaver previousTss = macroTable.get(i - 1);

                if (tss.isWDown && !previousTss.isWDown)
                    result += "down('W');\n";
                else if (!tss.isWDown && previousTss.isWDown)
                    result += "up('W');\n";

                if (tss.isADown && !previousTss.isADown)
                    result += "down('A');\n";
                else if (!tss.isADown && previousTss.isADown)
                    result += "up('A');\n";

                if (tss.isSDown && !previousTss.isSDown)
                    result += "down('S');\n";
                else if (!tss.isSDown && previousTss.isSDown)
                    result += "up('S');\n";

                if (tss.isDDown && !previousTss.isDDown)
                    result += "down('D');\n";
                else if (!tss.isDDown && previousTss.isDDown)
                    result += "up('D');\n";

                if (tss.isSprintDown && !previousTss.isSprintDown)
                    result += "down('Sprint');\n";
                else if (!tss.isSprintDown && previousTss.isSprintDown)
                    result += "up('Sprint');\n";

                if (tss.isShiftDown && !previousTss.isShiftDown)
                    result += "down('Shift');\n";
                else if (!tss.isShiftDown && previousTss.isShiftDown)
                    result += "up('Shift');\n";

                if (tss.isSpaceDown && !previousTss.isSpaceDown)
                    result += "down('Space');\n";
                else if (!tss.isSpaceDown && previousTss.isSpaceDown)
                    result += "up('Space');\n";

                if (Double.compare(tss.rotationDX, 0.0D) != 0)
                    result += "mouseMove(" + tss.rotationDX + ");\n";
            };

            if (result.equals(""))
                continue;

            result = "//Tick " + (i + 1) + "\n" + result;
            behaviors.add(result);
        };
    };

    public static void betterTranslate(float sens) {
        float factor = 1.2f * (sens * 0.6f + 0.2f) * (sens * 0.6f + 0.2f) * (sens * 0.6f + 0.2f);

        float total = 0.0f;
        float assumption = 0.0f;

        for (int i = 0; i < macroTable.size(); i++) {
            String result = "";
            TickStateSaver tss = macroTable.get(i);

            System.out.println(tss.formPassword());

            if (i == 0) {
                if (tss.isWDown)
                    result += "down('W');\n";
                if (tss.isADown)
                    result += "down('A');\n";
                if (tss.isSDown)
                    result += "down('S');\n";
                if (tss.isDDown)
                    result += "down('D');\n";
                if (tss.isSprintDown)
                    result += "down('Sprint');\n";
                if (tss.isShiftDown)
                    result += "down('Shift');\n";
                if (tss.isSpaceDown)
                    result += "down('Space');\n";
            }
            else {
                TickStateSaver previousTss = macroTable.get(i - 1);

                if (tss.isWDown && !previousTss.isWDown)
                    result += "down('W');\n";
                else if (!tss.isWDown && previousTss.isWDown)
                    result += "up('W');\n";

                if (tss.isADown && !previousTss.isADown)
                    result += "down('A');\n";
                else if (!tss.isADown && previousTss.isADown)
                    result += "up('A');\n";

                if (tss.isSDown && !previousTss.isSDown)
                    result += "down('S');\n";
                else if (!tss.isSDown && previousTss.isSDown)
                    result += "up('S');\n";

                if (tss.isDDown && !previousTss.isDDown)
                    result += "down('D');\n";
                else if (!tss.isDDown && previousTss.isDDown)
                    result += "up('D');\n";

                if (tss.isSprintDown && !previousTss.isSprintDown)
                    result += "down('Sprint');\n";
                else if (!tss.isSprintDown && previousTss.isSprintDown)
                    result += "up('Sprint');\n";

                if (tss.isShiftDown && !previousTss.isShiftDown)
                    result += "down('Shift');\n";
                else if (!tss.isShiftDown && previousTss.isShiftDown)
                    result += "up('Shift');\n";

                if (tss.isSpaceDown && !previousTss.isSpaceDown)
                    result += "down('Space');\n";
                else if (!tss.isSpaceDown && previousTss.isSpaceDown)
                    result += "up('Space');\n";
            };

            if (Double.compare(tss.rotationDX, 0.0D) != 0) {
                total += tss.rotationDX;

                int diff = Math.round((total - assumption) / factor);
                assumption += (diff * factor);

                result += "mouseMove(" + diff + ", 0); // (Offset by " + (assumption - total) + ")\n";
            };
            if (result.equals(""))
                continue;

            result = "delayTo(" + (i * 50) + ");\n" + result;
            behaviors.add(result);
        };
    };
}
