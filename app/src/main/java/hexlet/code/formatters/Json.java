package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;


public class Json {
    public static String json(Map<String, String> keyStatus) {
        return keyStatus.keySet().stream()
                .filter(key -> !keyStatus.get(key).equals("unchanged"))
                .map(key -> {
                    String returned;
                    if (keyStatus.get(key).equals("updated")) {
                        returned = String.format("  \"%s\": \"updated\"", key);
                    } else if (keyStatus.get(key).equals("removed")) {
                        returned = String.format("  \"%s\": \"removed\"", key);
                    } else {
                        returned = String.format("  \"%s\": \"added\"", key);
                    }
                    return returned;
                })
                .collect(Collectors.joining(",\n", "{\n", "\n}"));
    }
}
