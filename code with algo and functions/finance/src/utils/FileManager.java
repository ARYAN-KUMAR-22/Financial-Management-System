package utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Base64;

public class FileManager {

    public static void saveData(Map<Category, List<Transaction>> data, String filename) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        String encoded = Base64.getEncoder().encodeToString(json.getBytes());

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(encoded);
        }
    }

    public static Map<Category, List<Transaction>> loadData(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String encoded = reader.readLine();
            String json = new String(Base64.getDecoder().decode(encoded));

            Gson gson = new Gson();
            Type type = new TypeToken<Map<Category, List<Transaction>>>() {}.getType();
            return gson.fromJson(json, type);
        }
    }
}

