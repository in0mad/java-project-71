package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Map<String, Object>> keyInfo, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.stylish(keyInfo);
            case "plain" -> Plain.plain(keyInfo);
            case "json" -> Json.json(keyInfo);
            default -> throw new RuntimeException("unsupported format");
        };
    }
}
