package com.GodTheHands.crazyparkour.macro;

import com.GodTheHands.crazyparkour.common.ConfigLoader;
import net.minecraft.client.gui.GuiTextField;

import java.io.File;
import java.util.ArrayList;

public class MacroConfigSaver {
    public int currentPage = 1;
    public int currentTicks = 1;
    public int currentIn = 1;

    public int cursorIn;
    public ArrayList<ArrayList<Integer>> passwords = new ArrayList<>();
    public ArrayList<ArrayList<String>> dxs = new ArrayList<>();

    public MacroConfigSaver() {
        passwords.add(new ArrayList<Integer>(15){});
        passwords.get(0).add(0);

        dxs.add(new ArrayList<String>(15){});
        dxs.get(0).add("0");

        cursorIn = dxs.get(0).get(0).length();
    }

    public static MacroConfigSaver loadMCSFromFile() {
        MacroConfigSaver loaded = MacroFileManager.readFromJson(new File(MacroFileManager.getScreensDir(), ConfigLoader.macro_name + ".json"), MacroConfigSaver.class);

        if (loaded == null) {
            loaded = new MacroConfigSaver();
            saveMCSToFile();
        }

        return loaded;
    }

    public static void saveMCSToFile() {
        MacroFileManager.writeJsonToFile(new File(MacroFileManager.getScreensDir(), ConfigLoader.macro_name + ".json"), MacroGuiScreen.macroConfigSaver);
    }

    public void addCurrentIn() {
        if (currentIn < 15) {
            currentIn++;

            if (currentIn > currentTicks + 15 - 15 * currentPage) {
                passwords.get(currentPage - 1).add(0);
                dxs.get(currentPage - 1).add("0");
                currentTicks++;
            }
        }
        else {
            pageUp();
            currentIn = 1;

            if (currentIn > currentTicks + 15 - 15 * currentPage) {
                passwords.add(new ArrayList<Integer>(15){});
                passwords.get(currentPage - 1).add(0);

                dxs.add(new ArrayList<String>(15){});
                dxs.get(currentPage - 1).add("0");
                currentTicks++;
            }
        }
    }

    public void addCurrentInWithoutModify() {
        if (currentIn + (currentPage - 1) * 15 < currentTicks) {
            addCurrentIn();
        }
    }

    public void prevCurrentIn() {
        if (currentIn > 1) {
            currentIn--;
        }
        else {
            if (pageDown()) {
                currentIn = 15;
            }
        }
    }

    public void pageUp() {
        currentPage++;
    }

    public boolean pageDown() {
        if (currentPage == 1) {
            return false;
        }
        else {
            currentPage--;
            return true;
        }
    }

    public void newPageDown() {
        if (currentPage == 1) {
            return;
        }

        currentPage--;
        cursorIn = dxs.get(currentPage - 1).get(currentIn - 1).length();
    }

    public void newPageUp() {
        int maxPage = (currentTicks - 1) / 15 + 1;

        if (currentPage == maxPage) { return;}

        if (currentPage != maxPage - 1) {
            currentPage++;
            cursorIn = dxs.get(currentPage - 1).get(currentIn - 1).length();
        }
        else {
            currentPage++;

            if (currentIn > (currentTicks - 1) % 15 + 1) {
                currentIn = (currentTicks - 1) % 15 + 1;
            }
            cursorIn = dxs.get(currentPage - 1).get(currentIn - 1).length();
        }
    }

    public void deleteElement(int index) { //index = realIndex - 1
        if (currentTicks == 1) {return;}

        ArrayList<ArrayList<Integer>> temp = passwords;
        ArrayList<ArrayList<String>> temp2 = dxs;

        int deleteIndex = index % 15;
        int pageIndex = index / 15;
        int maxPageIndex = (currentTicks - 1) / 15;

        for (int i = pageIndex; i < maxPageIndex; i++) {
            ArrayList<Integer> arr = temp.get(i);
            ArrayList<Integer> nextArr = temp.get(i + 1);
            ArrayList<String> arr2 = temp2.get(i);
            ArrayList<String> nextArr2 = temp2.get(i + 1);

            if (i == pageIndex) {
                arr.remove(deleteIndex);
                arr2.remove(deleteIndex);
            }
            else {
                arr.remove(0);
                arr2.remove(0);
            }

            arr.add(nextArr.get(0));
            arr2.add(nextArr2.get(0));
            temp.set(i, arr);
            temp2.set(i, arr2);
        }

        ArrayList<Integer> lastArr = temp.get(maxPageIndex);
        ArrayList<String> lastArr2 = temp2.get(maxPageIndex);

        if (pageIndex == maxPageIndex) {
            if (lastArr.size() == 1) {
                temp.remove(maxPageIndex);
                temp2.remove(maxPageIndex);
            }
            else {
                lastArr.remove(deleteIndex);
                lastArr2.remove(deleteIndex);
                temp.set(maxPageIndex, lastArr);
                temp2.set(maxPageIndex, lastArr2);
            }
        }
        else {
            if (lastArr.size() == 1) {
                temp.remove(maxPageIndex);
                temp2.remove(maxPageIndex);
            }
            else {
                lastArr.remove(0);
                lastArr2.remove(0);
                temp.set(maxPageIndex, lastArr);
                temp2.set(maxPageIndex, lastArr2);
            }
        }

        passwords = temp;
        currentTicks--;
        if (currentIn + currentPage * 15 == currentTicks + 16) {
            prevCurrentIn();
        }
    }

    public void insertElement(int index) { //index = realIndex - 1
        ArrayList<ArrayList<Integer>> temp = passwords;
        ArrayList<ArrayList<String>> temp2 = dxs;

        int addIndex = index % 15;
        int currentPageIndex = index / 15;
        int maxPageIndex = (currentTicks - 1) / 15;

        ArrayList<Integer> realLastArr = temp.get(maxPageIndex);
        ArrayList<String> realLastArr2 = temp2.get(maxPageIndex);

        if (currentPageIndex == maxPageIndex) {
            if (realLastArr.size() == 15) {
                ArrayList<Integer> newArr = new ArrayList<Integer>(15);
                ArrayList<String> newArr2 = new ArrayList<>(15);
                newArr.add(0, realLastArr.get(14));
                newArr2.add(0, realLastArr2.get(14));
                realLastArr.remove(14);
                realLastArr2.remove(14);
                realLastArr.add(addIndex, 0);
                realLastArr2.add(addIndex, "0");
                temp.set(maxPageIndex, realLastArr);
                temp2.set(maxPageIndex, realLastArr2);
                temp.add(newArr);
                temp2.add(newArr2);
            }
            else {
                realLastArr.add(addIndex, 0);
                realLastArr2.add(addIndex, "0");
                temp.set(maxPageIndex, realLastArr);
                temp2.set(maxPageIndex, realLastArr2);
            }
        }
        else {
            if (realLastArr.size() == 15) {
                ArrayList<Integer> newArr = new ArrayList<Integer>(15);
                ArrayList<String> newArr2 = new ArrayList<String>(15);
                newArr.add(0, realLastArr.get(14));
                newArr2.add(0, realLastArr2.get(14));
                realLastArr.remove(14);
                realLastArr2.remove(14);
                realLastArr.add(0, temp.get(maxPageIndex - 1).get(14));
                realLastArr2.add(0, temp2.get(maxPageIndex - 1).get(14));
                temp.set(maxPageIndex, realLastArr);
                temp2.set(maxPageIndex, realLastArr2);
                temp.add(newArr);
                temp2.add(newArr2);
            }
            else {
                realLastArr.add(0, temp.get(maxPageIndex - 1).get(14));
                realLastArr2.add(0, temp2.get(maxPageIndex - 1).get(14));
                temp.set(maxPageIndex, realLastArr);
                temp2.set(maxPageIndex, realLastArr2);
            }
        }

        for (int i = maxPageIndex - 1; i >= currentPageIndex; i--) {
            ArrayList<Integer> arr = temp.get(i);
            ArrayList<String> arr2 = temp2.get(i);

            arr.remove(14);
            arr2.remove(14);

            if (i == currentPageIndex) {
                arr.add(addIndex, 0);
                arr2.add(addIndex, "0");
            } else {
                ArrayList<Integer> lastArr = temp.get(i - 1);
                ArrayList<String> lastArr2 = temp2.get(i - 1);
                arr.add(0, lastArr.get(14));
                arr2.add(0, lastArr2.get(14));
            }

            temp.set(i, arr);
            temp2.set(i, arr2);
        }

        passwords = temp;
        dxs = temp2;
        currentTicks++;
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

    public void duplicateTable(int realIndex) {
        int index = realIndex - 1;
        int addIndex = index % 15;
        int currentPageIndex = index / 15;

        Integer currentPassword = passwords.get(currentPageIndex).get(addIndex);
        String currentDx = dxs.get(currentPageIndex).get(addIndex);

        insertElement(index);

        ArrayList<Integer> temp1 = passwords.get(currentPageIndex);
        temp1.set(addIndex, currentPassword);
        passwords.set(currentPageIndex, temp1);

        ArrayList<String> temp2 = dxs.get(currentPageIndex);
        temp2.set(addIndex, currentDx);
        dxs.set(currentPageIndex, temp2);

        addCurrentIn();
    }

    public void insertValue(int realIndex, Integer intValue, String stringValue) {
        int index = realIndex - 1;
        int addIndex = index % 15;
        int currentPageIndex = index / 15;

        insertElement(index);

        ArrayList<Integer> temp1 = passwords.get(currentPageIndex);
        temp1.set(addIndex, intValue);
        passwords.set(currentPageIndex, temp1);

        ArrayList<String> temp2 = dxs.get(currentPageIndex);
        temp2.set(addIndex, stringValue);
        dxs.set(currentPageIndex, temp2);

        addCurrentIn();
    };
}
