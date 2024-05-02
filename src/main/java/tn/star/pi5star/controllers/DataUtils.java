package tn.star.pi5star.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    public static List<String> loadData(String filename) throws IOException {
        List<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            // Ajouter la ligne de données à la liste
            data.add(line);
        }
        reader.close();
        return data;
    }
}