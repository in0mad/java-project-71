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

                    return switch (keyStatusValue) {
                        case "unchanged" -> formatUnchanged(key, oldValue);
                        case "updated" -> formatUpdated(key, oldValue, newValue);
                        case "removed" -> formatRemoved(key, oldValue);
                        default -> formatAdded(key, newValue);
                    };
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    public static String formatUnchanged(String key, Object oldValue) {
        return String.format("  %s: %s", key, oldValue);
    }

    public static String formatUpdated(String key, Object oldValue, Object newValue) {
            return String.format("- %s: %s\n"
                    + "  + %s: %s", key, oldValue, key, newValue);
    }

    public static String formatRemoved(String key, Object oldValue) {
        return String.format("- %s: %s", key, oldValue);
    }

    public static String formatAdded(String key, Object newValue) {
        return String.format("+ %s: %s", key, newValue);
    }
}
