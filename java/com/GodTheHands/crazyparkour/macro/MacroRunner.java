package com.GodTheHands.crazyparkour.macro;

import java.util.ArrayList;

public class MacroRunner {
    public static ArrayList<TickStateSaver> macroTable;

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
}
