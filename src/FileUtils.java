import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    private static Gson gson = new Gson();

    public static void saveStringToFile(String filePath, String data) {
        try {
            Files.write(Paths.get(filePath), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileToStringFromResources(String filePath) {
        try {
            return IOUtils.toString(FileUtils.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readFileToIntegerListFromResources(String filePath) {
        return gson.fromJson(readFileToStringFromResources(filePath), new TypeToken<List<Integer>>(){}.getType());
    }

    public static Gson getGson()
    {
        return gson;
    }
}
