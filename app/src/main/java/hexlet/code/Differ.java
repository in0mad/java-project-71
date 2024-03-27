package hexlet.code;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Path absolutePath1, Path absolutePath2, String format) throws Exception {
        Map<String, Object> dataFile1 = Parser.parse(absolutePath1);
        Map<String, Object> dataFile2 = Parser.parse(absolutePath2);
        if (dataFile1.isEmpty() && dataFile2.isEmpty()) {
            return "{\n}";
        }
        var keySet1 = dataFile1.keySet();
        var keySet2 = dataFile2.keySet();
        Set<String> unionKeySet = new TreeSet<>();
        unionKeySet.addAll(keySet1);
        unionKeySet.addAll(keySet2);
        return switch (format) {
            case "stylish" -> Formatters.stylish(dataFile1, dataFile2, unionKeySet);
            case "default" -> Formatters.defaultFormat(dataFile1, dataFile2, unionKeySet);
            default -> "unsupported format";
        };
    }

    public static String generate(Path absolutePath1, Path absolutePath2) throws Exception {
        return generate(absolutePath1, absolutePath2, "stylish");
    }
}
