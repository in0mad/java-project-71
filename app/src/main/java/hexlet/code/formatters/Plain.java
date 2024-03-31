package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {
    public static String plain(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyStatus) {
        return keyStatus.keySet().stream()
                .filter(key -> !keyStatus.get(key).equals("unchanged"))
                .map(key -> {
                    Object valueFile1;
                    Object valueFile2;
                    String returned;
                    if (keyStatus.get(key).equals("updated")) {
                        valueFile1 = dataFile1.get(key);
                        valueFile2 = dataFile2.get(key);
                        if (valueFile1 == null || valueFile2 == null) {
                            returned = nullHandler(valueFile1, valueFile2, key);
                        } else {
                            returned = String.format("Property '%s' was updated. From %s to %s", key,
                                    complexCheck(valueFile1), complexCheck(valueFile2));
                        }
                    } else if (keyStatus.get(key).equals("removed")) {
                        returned = String.format("Property '%s' was removed", key);
                    } else {
                        valueFile2 = dataFile2.get(key);
                        returned = String.format("Property '%s' was added with value: %s",
                                key, complexCheck(valueFile2));
                    }
                    return returned;
                })
                .collect(Collectors.joining("\n"));
    }

    public static String nullHandler(Object valueFile1, Object valueFile2, String key) {
        return valueFile1 == null && valueFile2 != null
                ? String.format("Property '%s' was updated. From %s to %s", key, null, complexCheck(valueFile2))
                : String.format("Property '%s' was updated. From %s to %s", key, complexCheck(valueFile1), null);
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
