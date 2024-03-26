package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Formatters {
    public static String defaultFormat(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                       Set<String> unionKeySet) {
        return unionKeySet.stream()
                .map(key -> {
                    Object valueFile1 = dataFile1.get(key);
                    Object valueFile2 = dataFile2.get(key);
                    String returned;
                    if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) { //if value is changed
                        returned = valueFile1.equals(valueFile2)
                                ? String.format("  %s: %s", key, valueFile1)
                                : String.format("- %s: %s\n"
                                + "  + %s: %s", key, valueFile1, key, valueFile2);
                    } else if (dataFile1.containsKey(key) && !dataFile2.containsKey(key)) {
                        returned = String.format("- %s: %s", key, valueFile1);
                    } else if (!dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
                        returned = String.format("+ %s: %s", key, valueFile2);
                    } else {
                        returned = String.format("- %s: %s", key, valueFile1);
                    }
                    return returned;
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    public static String stylish(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                       Set<String> unionKeySet) {
        return unionKeySet.stream()
                .map(key -> {
                    Object valueFile1 = dataFile1.get(key);
                    Object valueFile2 = dataFile2.get(key);
                    String returned;
                    if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) { //if value is changed
                        returned = valueFile1.equals(valueFile2)
                                ? String.format("  %s: %s", key, valueFile1)
                                : String.format("- %s: %s\n"
                                + "  + %s: %s", key, valueFile1, key, valueFile2);
                    } else if (dataFile1.containsKey(key) && !dataFile2.containsKey(key)) {
                        returned = String.format("- %s: %s", key, valueFile1);
                    } else if (!dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
                        returned = String.format("+ %s: %s", key, valueFile2);
                    } else {
                        returned = String.format("- %s: %s", key, valueFile1);
                    }
                    return returned;
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }
}
