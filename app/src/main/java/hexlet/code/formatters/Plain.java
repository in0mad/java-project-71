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

                    return switch (keyStatusValue) {
                        case "updated" -> formatUpdated(key, oldValue, newValue);
                        case "removed" -> formatRemoved(key);
                        default -> formatAdded(key, newValue);
                    };
                })
                .collect(Collectors.joining("\n"));
    }

    public static String nullHandler(Object valueFileOld, Object valueFileNew, String key) {
        return valueFileOld == null && valueFileNew != null
                ? String.format("Property '%s' was updated. From %s to %s", key, null, complexCheck(valueFileNew))
                : String.format("Property '%s' was updated. From %s to %s", key, complexCheck(valueFileOld), null);
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

    public static String formatUpdated(String key, Object oldValue, Object newValue) {
        if (oldValue == null || newValue == null) {
            return nullHandler(oldValue, newValue, key);
        } else {
            return String.format("Property '%s' was updated. From %s to %s", key,
                    complexCheck(oldValue), complexCheck(newValue));
        }
    }

    public static String formatRemoved(String key) {
        return String.format("Property '%s' was removed", key);
    }

    public static String formatAdded(String key, Object newValue) {
        return String.format("Property '%s' was added with value: %s",
                key, complexCheck(newValue));
    }

}
