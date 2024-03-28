package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;

public class Formatter {
    public static String plain(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                       Set<String> unionKeySet) {
        return Plain.plain(dataFile1, dataFile2, unionKeySet);
    }

    public static String stylish(Map<String, Object> dataFile1, Map<String, Object> dataFile2,
                                       Set<String> unionKeySet) {
        return Stylish.stylish(dataFile1, dataFile2, unionKeySet);
    }
}
