package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Stylish {
    public static String stylish(List<Map<String, Object>> keyStatus) {
        return keyStatus.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    Map<String, Object> temp = entry.getValue();
                    Object valueFileOld;
                    Object valueFileNew;
                    String returned;
                    if (temp.get("Key status").equals("unchanged") || temp.get("Key status").equals("updated")) {
                        valueFileOld = temp.get("Old value");
                        valueFileNew = temp.get("New value");
                        if (valueFileOld == null || valueFileNew == null) {
                            returned = nullHandler(valueFileOld, valueFileNew, key);
                        } else {
                            returned = valueFileOld.equals(valueFileNew)
                                    ? String.format("  %s: %s", key, valueFileOld.toString())
                                    : String.format("- %s: %s\n"
                                    + "  + %s: %s", key, valueFileOld, key, valueFileNew.toString());
                        }
                    } else if (temp.get("Key status").equals("removed")) {
                        valueFileOld = temp.get("Old value");
                        returned = valueFileOld == null
                                ? String.format("- %s: %s", key, null)
                                : String.format("- %s: %s", key, valueFileOld.toString());
                    } else {
                        valueFileNew = temp.get("New value");
                        returned = valueFileNew == null
                                ? String.format("+ %s: %s", key, null)
                                : String.format("+ %s: %s", key, valueFileNew.toString());
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
