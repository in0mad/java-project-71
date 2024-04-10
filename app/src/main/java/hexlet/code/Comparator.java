package hexlet.code;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.LinkedList;

public class Comparator {
    public static Map<String, Map<String, Object>> collectKeyStatus(Map<String, Object> dataFile1,
                                                                    Map<String, Object> dataFile2) {
        Set<String> unionKeySet = new TreeSet<>(dataFile1.keySet());
        unionKeySet.addAll(dataFile2.keySet());

        TreeMap<String, Map<String, Object>> keyStatusMap = new TreeMap<>();

        unionKeySet.forEach(key -> keyStatusMap.compute(key, (k, v) -> {
            LinkedList<String> statuses = new LinkedList<>(
                    List.of("Key value", "Key status", "Old value", "New value"));
            Map<String, Object> temp = new LinkedHashMap<>();
            for (String status : statuses) {
                switch (status) {
                    case "Key value" -> temp.put("Key value", keyVal(key, dataFile1, dataFile2));
                    case "Key status" -> temp.put("Key status", takeKeyStatus(key, dataFile1, dataFile2));
                    case "Old value" -> temp.put("Old value", takeOldVal(key, dataFile1, dataFile2));
                    case "New value" -> temp.put("New value", takeNewVal(key, dataFile2));
                }
            }
            return temp;
        }));
        return keyStatusMap;
    }

    public static Object keyVal(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        return dataFile1.getOrDefault(key, dataFile2.get(key));
    }

    public static Object takeKeyStatus(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        Object valueFile1;
        Object valueFile2;
        Object returned;
        returned = dataFile1.containsKey(key) && !dataFile2.containsKey(key) ? "removed" : "added";
        if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
            valueFile1 = dataFile1.get(key);
            valueFile2 = dataFile2.get(key);
            returned = valueFile1 == null || valueFile2 == null
                    ? valueFile1 == valueFile2 ? "unchanged" : "updated"
                    : valueFile1.equals(valueFile2) ? "unchanged" : "updated";
        }

        return returned;
    }

    public static Object takeOldVal(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        return dataFile1.getOrDefault(key, dataFile2.get(key));
    }

    public static Object takeNewVal(String key, Map<String, Object> dataFile2) {
        return dataFile2.getOrDefault(key, "");
    }
}
