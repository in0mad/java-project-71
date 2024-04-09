package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> keyInfo, String format) throws Exception {
        return switch (format) {
            case "stylish" -> stylish(keyInfo);
            case "plain" -> plain(keyInfo);
            case "json" -> json(keyInfo);
            case "default" -> defaultJson(keyInfo);
            default -> throw new RuntimeException("unsupported format");
        };
    }
    public static String plain(Map<String, Map<String, Object>> keyStatus) {
        return Plain.plain(keyStatus);
    }

    public static String stylish(Map<String, Map<String, Object>> keyStatus) {
        return Stylish.stylish(keyStatus);
    }

    public static String json(Map<String, Map<String, Object>> keyStatus) throws Exception {
        return Json.json(keyStatus);
    }

    public static String defaultJson(Map<String, Map<String, Object>> keyStatus) {
        return stylish(keyStatus);
    }

}
