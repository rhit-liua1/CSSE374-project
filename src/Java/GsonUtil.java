package Java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtil {
    final static Gson GSON = new Gson();

    public static String serializeWithGson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        return GSON.fromJson(jsonData, type);
    }

    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        return GSON.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
    }
}