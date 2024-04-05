package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyInfo, String format) throws Exception {
        return switch (format) {
            case "stylish" -> stylish(dataFile1, dataFile2, keyInfo);
            case "plain" -> plain(dataFile1, dataFile2, keyInfo);
            case "json" -> json(keyInfo);
            case "default" -> defaultJson(dataFile1, dataFile2, keyInfo);
            case "human-json" -> humanJson(keyInfo);
            default -> "unsupported format";
        };
    }
    public static String plain(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                               Map<String, String> keyStatus) {
        return Plain.plain(dataFile1, dataFile2, keyStatus);
    }

    public static String stylish(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyStatus) {
        return Stylish.stylish(dataFile1, dataFile2, keyStatus);
    }

    public static String json(Map<String, String> keyStatus) throws Exception {
        return Json.json(keyStatus);
    }

    public static String defaultJson(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                     Map<String, String> keyStatus) {
        return stylish(dataFile1, dataFile2, keyStatus);
    }

    public static String humanJson(Map<String, String> keyStatus) throws Exception {
        return Json.humanJson(keyStatus);
    }
}
