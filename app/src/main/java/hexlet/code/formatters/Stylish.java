package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Stylish {
    public static String stylish(List<Map<String, Object>> keyStatus) {
        return keyStatus.stream()
                .map(map -> {
                    String key = map.get("KEY").toString();
                    Object valueFileOld;
                    Object valueFileNew;
                    String returned;
                    if (map.get("KEY STATUS").equals("unchanged") || map.get("KEY STATUS").equals("updated")) {
                        valueFileOld = map.get("OLD VALUE");
                        valueFileNew = map.get("NEW VALUE");
                        if (valueFileOld == null || valueFileNew == null) {
                            returned = nullHandler(valueFileOld, valueFileNew, key);
                        } else {
                            returned = valueFileOld.equals(valueFileNew)
                                    ? String.format("  %s: %s", key, valueFileOld)
                                    : String.format("- %s: %s\n"
                                    + "  + %s: %s", key, valueFileOld, key, valueFileNew);
                        }
                    } else if (map.get("KEY STATUS").equals("removed")) {
                        valueFileOld = map.get("OLD VALUE");
                        returned = String.format("- %s: %s", key, valueFileOld);
                    } else {
                        valueFileNew = map.get("NEW VALUE");
                        returned = String.format("+ %s: %s", key, valueFileNew);
                    }
                    return returned;
                })
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }

    public static String nullHandler(Object valueFileOld, Object valueFileNew, String key) {
        if (valueFileOld == null && valueFileNew != null) {
            return String.format("- %s: %s\n"
                    + "  + %s: %s", key, null, key, valueFileNew.toString());
        } else if (valueFileOld != null && valueFileNew == null) {
            return String.format("- %s: %s\n"
                    + "  + %s: %s", key, valueFileOld.toString(), key, null);
        } else {
            return String.format("  %s: %s", key, null);
        }
    }
}
