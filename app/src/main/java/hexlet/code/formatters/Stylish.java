package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {
    public static String stylish(List<Map<String, Object>> keyStatus) {
        return keyStatus.stream()
                .map(map -> {
                    String key = map.get("KEY").toString();
                    String keyStatusValue = map.get("KEY STATUS").toString();
                    Object oldValue = map.get("OLD VALUE");
                    Object newValue = map.get("NEW VALUE");

                    return formatted(key, oldValue, newValue, keyStatusValue);
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    public static String formatted(String key, Object oldValue, Object newValue, String keyStatus) {
        return switch (keyStatus) {
            case "unchanged" -> String.format("  %s: %s", key, oldValue);
            case "updated" -> String.format("- %s: %s\n"
                    + "  + %s: %s", key, oldValue, key, newValue);
            case "removed" -> String.format("- %s: %s", key, oldValue);
            default -> String.format("+ %s: %s", key, newValue);
        };
    }
}
