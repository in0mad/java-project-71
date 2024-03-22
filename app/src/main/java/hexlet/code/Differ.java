package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        if (dataFile1.isEmpty() && dataFile2.isEmpty()) {
            return "{\n}";
        }

        var keySet1 = dataFile1.keySet();
        var keySet2 = dataFile2.keySet();
        Set<String> unionKeySet = new TreeSet<>();
        unionKeySet.addAll(keySet1);
        unionKeySet.addAll(keySet2);

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
