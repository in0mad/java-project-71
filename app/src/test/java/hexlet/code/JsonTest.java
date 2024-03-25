package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonTest {
    private final String resourceDirectory = Paths.get("src","test", "resources").toString();
    Path toJson1;
    Path toJson2;
    Path toEmptyJson;
    @BeforeEach
    public void beforeEach() {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        toJson1 = Paths.get(resourceDirectory, "json1.json").toAbsolutePath();
        toJson2 = Paths.get(resourceDirectory, "json2.json").toAbsolutePath();
        toEmptyJson = Paths.get(resourceDirectory, "json-empty.json").toAbsolutePath();
    }

    @Test
    public void cleanTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2);
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
    public void cleanTest2() throws Exception {
        String actual = Differ.generate(toJson2, toJson1);
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
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyJson, toJson2);
        String expected = "{\n"
                + "  + host: hexlet.io\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest2() throws Exception {
        String actual = Differ.generate(toJson2, toEmptyJson);
        String expected = "{\n"
                + "  - host: hexlet.io\n"
                + "  - timeout: 20\n"
                + "  - verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyJson, toEmptyJson);
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
