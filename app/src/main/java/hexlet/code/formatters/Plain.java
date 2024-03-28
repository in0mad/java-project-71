package hexlet.code.formatters;

import org.apache.commons.lang3.builder.EqualsExclude;

import javax.lang.model.type.PrimitiveType;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Plain {
    public static String plain(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                 Map<String, String> keyStatus) {
//    return unionKeySet.stream()
//            .map(key -> {
//                Object valueFile1;
//                Object valueFile2;
//                String returned;
//                if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
//                    valueFile1 = dataFile1.get(key);
//                    valueFile2 = dataFile2.get(key);
//                    if (valueFile1 == null || valueFile2 == null) {
//                        returned = nullHandler(valueFile1, valueFile2, key);
//                    } else {
//                        returned = valueFile1.equals(valueFile2)
//                            ? String.format("  %s: %s", key, valueFile1.toString())
//                            : String.format("- %s: %s\n"
//                            + "  + %s: %s", key, valueFile1, key, valueFile2.toString());
//                    }
//                } else if (dataFile1.containsKey(key) && !dataFile2.containsKey(key)) {
//                    returned = dataFile1.get(key) == null
//                            ? String.format("- %s: %s", key, null)
//                            : String.format("- %s: %s", key, dataFile1.get(key).toString());
//                } else {
//                    returned = dataFile2.get(key) == null
//                            ? String.format("+ %s: %s", key, null)
//                            : String.format("+ %s: %s", key, dataFile2.get(key).toString());
//                }
//                return returned;
//            })
//            .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
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
                        if (valueFile2 == null) {
                            returned = String.format("Property '%s' was added. With value: %s",
                                    key, null);
                        } else {
                        returned = String.format("Property '%s' was added. With value: %s",
                                key, complexCheck(valueFile2));
                        }
                    }
                    return returned;
                })
                .collect(Collectors.joining("\n"));
    }

    public static String nullHandler(Object valueFile1, Object valueFile2, String key) {
        if (valueFile1 == null && valueFile2 != null) {
            return String.format("Property '%s' was added. With value %s", key, complexCheck(valueFile2));
        } else if (valueFile1 != null && valueFile2 == null) {
            return String.format("Property '%s' was removed", key);
        } else {
            return "";
        }
    }

    public static String complexCheck(Object obj) {
        return !obj.getClass().isPrimitive() ? "[complex value]" : obj.toString();
    }
}
