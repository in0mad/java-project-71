package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;


public class Parser {
    public static Map<String, Object> parse(String fileData, String extension) throws Exception {
        ObjectMapper objectMapper = switch (extension) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new RuntimeException("unknown format of the file");
        };

        return objectMapper.
                readValue(fileData, new TypeReference<>() {
                });
    }
}
