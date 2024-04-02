package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

import java.nio.file.Path;

public class Parser {
    public static Map<String, Object> parse(Path filepath) throws Exception {
        String path = filepath.toString();
        ObjectMapper objectMapper;
        if (path.endsWith(".yaml") || path.endsWith(".yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            objectMapper = new ObjectMapper();
        }
        return objectMapper.
                readValue(filepath.toFile(), new TypeReference<>() {
                });
    }
}
