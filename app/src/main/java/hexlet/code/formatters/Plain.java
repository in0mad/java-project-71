package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {
    public static String plain(List<Map<String, Object>> keyStatus) {
        return keyStatus.entrySet().stream()
                .filter(entry -> {
                    Map<String, Object> temp = entry.getValue();
                    return !temp.get("Key status").equals("unchanged");
                })
                .map(entry -> {
                    String key = entry.getKey();
                    Map<String, Object> temp = entry.getValue();
                    Object valueFileOld;
                    Object valueFileNew;
                    String returned;
                    if (temp.get("Key status").equals("updated")) {
                        valueFileOld = temp.get("Old value");
                        valueFileNew = temp.get("New value");
                        if (valueFileOld == null || valueFileNew == null) {
                            returned = nullHandler(valueFileOld, valueFileNew, key);
                        } else {
                            returned = String.format("Property '%s' was updated. From %s to %s", key,
                                    complexCheck(valueFileOld), complexCheck(valueFileNew));
                        }
                    } else if (temp.get("Key status").equals("removed")) {
                        returned = String.format("Property '%s' was removed", key);
                    } else {
                        valueFileNew = temp.get("New value");
                        returned = String.format("Property '%s' was added with value: %s",
                                key, complexCheck(valueFileNew));
                    }
                    return returned;
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
}
