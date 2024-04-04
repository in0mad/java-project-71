package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();

        Map<String, Object> dataFile1 = readFile(absolutePath1);
        Map<String, Object> dataFile2 = readFile(absolutePath2);

        // тут должно что то возвращаться от парсера ...

        Map<String, String> keyInfo = Comparator.collectKeyStatus(dataFile1, dataFile2);

        return Formatter.format(dataFile1, dataFile2, keyInfo, format);
    }

    public static Map<String, Object> readFile(Path filepath) throws Exception {
        String path = filepath.toString();
        ObjectMapper objectMapper;
        if (path.endsWith(".yaml") || path.endsWith(".yml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            objectMapper = new ObjectMapper();
        }
        return objectMapper.readValue(filepath.toFile(), new TypeReference<>() { });
    }
}
