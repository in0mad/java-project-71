package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String plain(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                               Map<String, String> keyStatus) {
        return Plain.plain(dataFile1, dataFile2, keyStatus);
    }

    public static String stylish(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyStatus) {
        return Stylish.stylish(dataFile1, dataFile2, keyStatus);
    }

    public static String json(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyStatus) {
        return Json.json(dataFile1, dataFile2, keyStatus);
    }
}
