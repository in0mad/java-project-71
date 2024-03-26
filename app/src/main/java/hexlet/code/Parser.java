package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import java.nio.file.Path;

public class Parser {
    public static Map<String, Object> parse(Path absolutePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.
                readValue(absolutePath.toFile(), new TypeReference<>() {
                });
    }
}
