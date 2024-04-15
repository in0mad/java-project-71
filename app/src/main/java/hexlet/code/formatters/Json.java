package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Json {
    public static String json(List<Map<String, Object>> keyStatus) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(keyStatus);
    }
}
