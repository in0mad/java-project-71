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
                    Object valueFileOld;
                    Object valueFileNew;
                    String returned;
                    if (map.get("KEY STATUS").equals("updated")) {
                        valueFileOld = map.get("OLD VALUE");
                        valueFileNew = map.get("NEW VALUE");
                        if (valueFileOld == null || valueFileNew == null) {
                            returned = nullHandler(valueFileOld, valueFileNew, key);
                        } else {
                            returned = String.format("Property '%s' was updated. From %s to %s", key,
                                    complexCheck(valueFileOld), complexCheck(valueFileNew));
                        }
                    } else if (map.get("KEY STATUS").equals("removed")) {
                        returned = String.format("Property '%s' was removed", key);
                    } else {
                        valueFileNew = map.get("NEW VALUE");
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
