package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Comparator {
    public static Map<String, String> collectKeyStatus(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        Set<String> unionKeySet = new TreeSet<>(dataFile1.keySet());
        unionKeySet.addAll(dataFile2.keySet());

        TreeMap<String, String> keyStatusMap = new TreeMap<>();
        unionKeySet.forEach(key -> keyStatusMap.compute(key, (k, v) -> {
            Object valueFile1;
            Object valueFile2;
            String returned;
            if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
                valueFile1 = dataFile1.get(key);
                valueFile2 = dataFile2.get(key);
                if (valueFile1 == null || valueFile2 == null) {
                    returned = valueFile1 == valueFile2 ? "unchanged" : "updated";
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
        return keyStatusMap;
    }
}
