package hexlet.code.formatters;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Json {
    public static String json(Map<String, Map<String, Object>> keyStatus) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(keyStatus);
    }
}
