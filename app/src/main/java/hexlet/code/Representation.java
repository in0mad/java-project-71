package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Formatters.nullHandler;

public class Representation {
    public static Map<String, String> collectKeyStatus(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        var keySet1 = dataFile1.keySet();
        var keySet2 = dataFile2.keySet();
        Set<String> unionKeySet = new TreeSet<>();
        unionKeySet.addAll(keySet1);
        unionKeySet.addAll(keySet2);

        TreeMap<String, String> keyMap = new TreeMap<>();
        unionKeySet.forEach(key -> keyMap.compute(key, (k, v) -> {
            Object valueFile1;
            Object valueFile2;
            String returned;
            if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
                valueFile1 = dataFile1.get(key);
                valueFile2 = dataFile2.get(key);
                if (valueFile1 == null || valueFile2 == null) {
                    returned = nullHandler(valueFile1, valueFile2, key);
                } else {
                    returned = valueFile1.equals(valueFile2) ? "unchanged" : "updated";
                }
            } else if (dataFile1.containsKey(key) && !dataFile2.containsKey(key)) {
                returned = "removed";
            } else {
                returned = "added";
            }
            return returned;
        }));
        return keyMap;
    }
}
