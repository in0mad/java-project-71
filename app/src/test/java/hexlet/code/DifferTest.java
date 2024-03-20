package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.TreeMap;

public class DifferTest {
    @Test
    public void cleanTest() {
        Map<String, Object> cleanFile1 = new TreeMap<>(Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false));
        Map<String, Object> cleanFile2 = new TreeMap<>(Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io"));

        String actual = Differ.generate(cleanFile1, cleanFile2);
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        assertEquals(expected, actual);
    }
}
