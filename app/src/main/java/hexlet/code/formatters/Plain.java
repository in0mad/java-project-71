package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {
    public static String plain(List<Map<String, Object>> keyStatus) {
        return keyStatus.stream()
                .filter(map -> !map.get("KEY STATUS").equals("unchanged"))
                .map(map -> {
                    String key = map.get("KEY").toString();
                    String keyStatusValue = map.get("KEY STATUS").toString();
                    Object oldValue = map.get("OLD VALUE");
                    Object newValue = map.get("NEW VALUE");

                    return formatted(key, oldValue, newValue, keyStatusValue);
                })
                .collect(Collectors.joining("\n"));
    }

    public static String complexCheck(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof String) {
            return String.format("'%s'", obj);
        } else if (obj instanceof Map || obj instanceof List) {
            return "[complex value]";
        } else {
            return obj.toString();
        }
    }

    public static String formatted(String key, Object oldValue, Object newValue, String keyStatus) {
        return switch (keyStatus) {
            case "updated" -> String.format("Property '%s' was updated. From %s to %s", key,
                    complexCheck(oldValue), complexCheck(newValue));
            case "removed" -> String.format("Property '%s' was removed", key);
            default -> String.format("Property '%s' was added with value: %s",
                    key, complexCheck(newValue));
        };
    }

}
