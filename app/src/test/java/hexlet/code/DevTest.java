package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.TreeMap;

public class DevTest {
    Map<String, Object> testFile1;
    Map<String, Object> testFile2;
    @BeforeEach
    public void beforeEach() {
        testFile1 = new TreeMap<>();
        testFile2 = new TreeMap<>();
    }

    @Test
    public void cleanTest() {
        testFile1.putAll((Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false)));
        testFile2.putAll((Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io")));

        String actual = Differ.generate(testFile1, testFile2);
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void cleanTest2() {
        testFile1.putAll((Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io")));
        testFile2.putAll((Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false)));
        String actual = Differ.generate(testFile1, testFile2);
        String expected = "{\n"
                + "  + follow: false\n"
                + "    host: hexlet.io\n"
                + "  + proxy: 123.234.53.22\n"
                + "  - timeout: 20\n"
                + "  + timeout: 50\n"
                + "  - verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest1() {
        testFile1 = new TreeMap<>();
        testFile2.putAll(Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io"));

        String actual = Differ.generate(testFile1, testFile2);
        String expected = "{\n"
                + "  + host: hexlet.io\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest2() {
        testFile1.putAll(Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io"));
        testFile2 = new TreeMap<>();
        String actual = Differ.generate(testFile1, testFile2);
        String expected = "{\n"
                + "  - host: hexlet.io\n"
                + "  - timeout: 20\n"
                + "  - verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() {
        String actual = Differ.generate(testFile1, testFile2);
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
