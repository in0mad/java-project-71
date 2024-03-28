package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;


public class Stylish {
    public static String stylish(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyStatus) {
        return keyStatus.keySet().stream()
                .map(key -> {
                    Object valueFile1;
                    Object valueFile2;
                    String returned;
                    if (keyStatus.get(key).equals("unchanged") || keyStatus.get(key).equals("updated")) {
                        valueFile1 = dataFile1.get(key);
                        valueFile2 = dataFile2.get(key);
                        if (valueFile1 == null || valueFile2 == null) {
                            returned = nullHandler(valueFile1, valueFile2, key);
                        } else {
                            returned = valueFile1.equals(valueFile2)
                                    ? String.format("  %s: %s", key, valueFile1.toString())
                                    : String.format("- %s: %s\n"
                                    + "  + %s: %s", key, valueFile1, key, valueFile2.toString());
                        }
                    } else if (keyStatus.get(key).equals("removed")) {
                        returned = dataFile1.get(key) == null
                                ? String.format("- %s: %s", key, null)
                                : String.format("- %s: %s", key, dataFile1.get(key).toString());
                    } else {
                        returned = dataFile2.get(key) == null
                                ? String.format("+ %s: %s", key, null)
                                : String.format("+ %s: %s", key, dataFile2.get(key).toString());
                    }
                    return returned;
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    public static String nullHandler(Object valueFile1, Object valueFile2, String key) {
        if (valueFile1 == null && valueFile2 != null) {
            return String.format("- %s: %s\n"
                    + "  + %s: %s", key, null, key, valueFile2.toString());
        } else if (valueFile1 != null && valueFile2 == null) {
            return String.format("- %s: %s\n"
                    + "  + %s: %s", key, valueFile1.toString(), key, null);
        } else {
            return String.format("  %s: %s", key, null);
        }
    }
}
