package hexlet.code;

import java.nio.file.Path;
import java.util.Map;

public class Differ {
    public static String generate(Path absolutePath1, Path absolutePath2, String format) throws Exception {
        Map<String, Object> dataFile1 = Parser.parse(absolutePath1);
        Map<String, Object> dataFile2 = Parser.parse(absolutePath2);
        if (dataFile1.isEmpty() && dataFile2.isEmpty()) {
            return "{\n}";
        }
        Map<String, String> keyInfo = Representation.collectKeyStatus(dataFile1, dataFile2);

        return switch (format) {
            case "stylish" -> Formatter.stylish(dataFile1, dataFile2, keyInfo);
            case "plain" -> Formatter.plain(dataFile1, dataFile2, keyInfo);
            case "json" -> Formatter.json(keyInfo);
            default -> "unsupported format";
        };
    }

    public static String generate(Path absolutePath1, Path absolutePath2) throws Exception {
        return generate(absolutePath1, absolutePath2, "stylish");
    }
}
