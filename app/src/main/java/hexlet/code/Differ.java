package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String absolutePath1 = normalizePath(filepath1);
        String absolutePath2 = normalizePath(filepath2);

        String dataFile1 = readFile(absolutePath1);
        String dataFile2 = readFile(absolutePath2);

        Map<String, Object> parseFile1 = Parser.parse(dataFile1, defineFormat(absolutePath1));
        Map<String, Object> parseFile2 = Parser.parse(dataFile2, defineFormat(absolutePath2));

        Map<String, Map<String, Object>> keyInfo = Comparator.collectKeyStatus(parseFile1, parseFile2);
        return Formatter.format(keyInfo, format);
    }

    public static String readFile(String filepath) throws Exception {
        return Files.readString(Paths.get(filepath));
    }

    public static String defineFormat(String filepath) {
        String[] splitPath = filepath.split(Pattern.quote("."));
        return splitPath[splitPath.length - 1];
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String normalizePath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize().toString();
    }
}
