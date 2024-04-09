package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Json {
    public static String json(Map<String, Map<String, Object>> keyStatus) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = keyStatus.entrySet().stream()
                .filter(entry -> {
                    Map<String, Object> temp = entry.getValue();
                    return !temp.get("Key status").equals("unchanged");
                })
                .map(entry -> {
                    String key = entry.getKey();
                    Map<String, Object> temp = entry.getValue();
                    String returned;
                    if (temp.get("Key status").equals("updated")) {
                        returned = String.format("\"%s\" : \"updated\"", key);
                    } else if (temp.get("Key status").equals("removed")) {
                        returned = String.format("\"%s\" : \"removed\"", key);
                    } else {
                        returned = String.format("\"%s\" : \"added\"", key);
                    }
                    return returned;
                })
                .collect(Collectors.joining(", ", "{ ", " }"));
        return objectMapper.writeValueAsString(result);
    }
}
