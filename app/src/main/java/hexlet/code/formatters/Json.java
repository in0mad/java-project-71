package hexlet.code.formatters;

import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Json {
    public static String json(Map<String, String> keyStatus) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = keyStatus.keySet().stream()
                .filter(key -> !keyStatus.get(key).equals("unchanged"))
                .map(key -> {
                    String returned;
                    if (keyStatus.get(key).equals("updated")) {
                        returned = String.format("\"%s\" : \"updated\"", key);
                    } else if (keyStatus.get(key).equals("removed")) {
                        returned = String.format("\"%s\" : \"removed\"", key);
                    } else {
                        returned = String.format("\"%s\" : \"added\"", key);
                    }
                    return returned;
                })
                .collect(Collectors.joining(", ", "{ ", " }"));
        return objectMapper.writeValueAsString(result);
    }

    public static String humanJson(Map<String, String> keyStatus) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(keyStatus);
    }
}
