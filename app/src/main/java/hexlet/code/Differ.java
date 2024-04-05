package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize().toString();
        String absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize().toString();

        String dataFile1 = readFile(absolutePath1);
        String dataFile2 = readFile(absolutePath2);

        Map<String, Object> parseFile1 = Parser.parse(dataFile1, defineFormat(absolutePath1));
        Map<String, Object> parseFile2 = Parser.parse(dataFile2, defineFormat(absolutePath2));

        Map<String, String> keyInfo = Comparator.collectKeyStatus(parseFile1, parseFile2);

        return Formatter.format(parseFile1, parseFile2, keyInfo, format);
    }

    public static String readFile(String filepath) throws Exception {
        return Files.readString(Paths.get(filepath));
    }

    public static String defineFormat(String filepath) {
        if (filepath.endsWith(".yaml") || filepath.endsWith(".yml")) {
            return  "yaml";
        } else if (filepath.endsWith(".json")) {
            return  "json";
        } else {
            return "unknown";
        }
    }
}
