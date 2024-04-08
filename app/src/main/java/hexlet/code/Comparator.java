package hexlet.code;

import java.util.*;

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

        unionKeySet.forEach(key -> keyStatusMap.compute(key, (k, v) -> {
            Object valueFile1;
            Object valueFile2;
            String returned;
            returned = dataFile1.containsKey(key) && !dataFile2.containsKey(key) ? "removed" : "added";
            if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
                valueFile1 = dataFile1.get(key);
                valueFile2 = dataFile2.get(key);
                returned = valueFile1 == null || valueFile2 == null
                        ? valueFile1 == valueFile2 ? "unchanged" : "updated"
                        : valueFile1.equals(valueFile2) ? "unchanged" : "updated";
            }
            return returned;
        }));
        return keyStatusMap;
    }


//    public static Map<String, Map<String, Object>> collectKeyStatus(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
//        Set<String> unionKeySet = new TreeSet<>(dataFile1.keySet());
//        unionKeySet.addAll(dataFile2.keySet());
//
//        TreeMap <String, Map<String, Object>> keyStatusMap = new TreeMap<>();
//
//        unionKeySet.forEach(key -> keyStatusMap.compute(key, (k, v) -> {
//            LinkedList<String> statuses = new LinkedList<>(List.of("Current value", "Key status", "Old value", "New value"));
//            for (String status : statuses) {
//                return switch (status) {
//                    case "Key status" -> takeKeyStatus(key, dataFile1, dataFile2);
//                    case "Old value" -> takeOldVal(key, dataFile1, dataFile2);
//                    case "New value" -> takeNewVal(key, dataFile2);
//                    default -> takeCurrent(key, dataFile1, dataFile2);
//                };
//            }
//            return v;
//        }));
//
//        return keyStatusMap;
//    }
//
//    public static Map<String, Object> takeCurrent(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
//        Map<String, Object> currentValMap = new HashMap<>();
//        currentValMap.put("Current value", dataFile1.getOrDefault(key, dataFile2.get(key)));
//        return currentValMap;
//    }
//
//    public static Map<String, Object> takeKeyStatus(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
//        Map<String, Object> statusMap = new HashMap<>();
//        Object valueFile1;
//        Object valueFile2;
//        Object returned;
//        returned = dataFile1.containsKey(key) && !dataFile2.containsKey(key) ? "removed" : "added";
//        if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
//        valueFile1 = dataFile1.get(key);
//        valueFile2 = dataFile2.get(key);
//        returned = valueFile1 == null || valueFile2 == null
//                ? valueFile1 == valueFile2 ? "unchanged" : "updated"
//                : valueFile1.equals(valueFile2) ? "unchanged" : "updated";
//        }
//
//        statusMap.put("Key status", returned);
//        return statusMap;
//    }
//
//    public static Map<String, Object> takeOldVal(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
//        Map<String, Object> oldValMap = new HashMap<>();
//        oldValMap.put("Old value", dataFile1.getOrDefault(key, dataFile2.get(key)));
//        return oldValMap;
//    }
//
//    public static Map<String, Object> takeNewVal(String key, Map<String, Object> dataFile2) {
//        Map<String, Object> newValMap = new HashMap<>();
//        newValMap.put("New value", dataFile2.getOrDefault(key, ""));
//        return newValMap;
//    }
}
