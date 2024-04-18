package hexlet.code;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Comparator {
    public static List<Map<String, Object>> collectKeyStatus(Map<String, Object> dataFile1,
                                                             Map<String, Object> dataFile2) {
        Set<String> unionKeySet = new TreeSet<>(dataFile1.keySet());
        unionKeySet.addAll(dataFile2.keySet());

        List<Map<String, Object>> keyStatusMap = new ArrayList<>();

        unionKeySet.forEach(key -> {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("KEY", key);
            temp.putAll(takeDifference(key, dataFile1, dataFile2));
            keyStatusMap.add(temp);
        });
        return keyStatusMap;
    }


    public static Map<String, Object> takeDifference(String key, Map<String, Object> dataFile1,
                                                     Map<String, Object> dataFile2) {
        Object valueFile1;
        Object valueFile2;
        Map<String, Object> returnedMap = new LinkedHashMap<>();
        if (!dataFile2.containsKey(key)) {
            valueFile1 = dataFile1.get(key);
            returnedMap.put("KEY STATUS", "removed");
            returnedMap.put("OLD VALUE", valueFile1);
        } else if (!dataFile1.containsKey(key)) {
            valueFile2 = dataFile2.get(key);
            returnedMap.put("KEY STATUS", "added");
            returnedMap.put("NEW VALUE", valueFile2);
        } else {
            valueFile1 = dataFile1.get(key);
            valueFile2 = dataFile2.get(key);
            if (Objects.equals(valueFile1, valueFile2)) {
                returnedMap.put("KEY STATUS", "unchanged");
                returnedMap.put("OLD VALUE", valueFile1);
            } else {
                returnedMap.put("KEY STATUS", "updated");
                returnedMap.put("OLD VALUE", valueFile1);
                returnedMap.put("NEW VALUE", valueFile2);
            }
        }
        return returnedMap;
    }
}
