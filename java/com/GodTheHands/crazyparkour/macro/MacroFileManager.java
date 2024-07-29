package com.GodTheHands.crazyparkour.macro;

import com.google.gson.Gson;

import java.io.*;

public class MacroFileManager {
    private static Gson gson = new Gson();

    private static File ROOT_DIR = new File("CrazyParkour");
    private static File SCREENS_DIR = new File(ROOT_DIR, "Macro");

    public static void init() {
        if (!ROOT_DIR.exists()) {ROOT_DIR.mkdirs();}
        if (!SCREENS_DIR.exists()) {SCREENS_DIR.mkdirs();}
    }

    public static File getScreensDir() {return SCREENS_DIR;}

    public static boolean writeJsonToFile(File file, Object object) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream output = new FileOutputStream(file);
            output.write(gson.toJson(object).getBytes());
            output.flush();
            output.close();

            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T> T readFromJson(File file, Class<T> c) {
        try {
            FileInputStream input = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader breader = new BufferedReader(reader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = breader.readLine()) != null) {
                sb.append(line);
            }

            breader.close();
            reader.close();
            input.close();

            return gson.fromJson(sb.toString(), c);
        }
        catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
