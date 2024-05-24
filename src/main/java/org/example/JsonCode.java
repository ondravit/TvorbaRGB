package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonCode {
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveJSON(List<Barva> barvy) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save JSON File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".json")) {
                filePath += ".json";
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                GSON.toJson(barvy, writer);
            } catch (IOException e) {
                e.printStackTrace(); // Handle error
            }
        }
    }
}
