package utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static Gson gson = new Gson();

    public static List<String> loadFile(String fileName) {
        try {
            List<String> fileContent = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveFile(String fileName, List<String> fileContent) {
        try {
            File file = new File(fileName);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int counter = 0; counter < fileContent.size(); counter++) {
                String line = fileContent.get(counter);
                writer.write(line);
                if (counter < fileContent.size() - 1) {
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Gson getGson() {
        return gson;
    }

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
