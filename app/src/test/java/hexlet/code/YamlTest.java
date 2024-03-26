package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YamlTest {
    private final String resourceDirectory = Paths.get("src","test", "resources").toString();
    Path toYaml1;
    Path toYaml2;
    Path toEmptyYaml;
    @BeforeEach
    public void beforeEach() {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        toYaml1 = Paths.get(resourceDirectory, "yaml1.yml").toAbsolutePath();
        toYaml2 = Paths.get(resourceDirectory, "yaml2.yml").toAbsolutePath();
        toEmptyYaml = Paths.get(resourceDirectory, "yaml-empty.yml").toAbsolutePath();
    }

    @Test
    public void cleanTest() throws Exception {
        String actual = Differ.generate(toYaml1, toYaml2);
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
        String actual = Differ.generate(toYaml2, toYaml1);
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
        String actual = Differ.generate(toEmptyYaml, toYaml2);
        String expected = "{\n"
                + "  + host: hexlet.io\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest2() throws Exception {
        String actual = Differ.generate(toYaml2, toEmptyYaml);
        String expected = "{\n"
                + "  - host: hexlet.io\n"
                + "  - timeout: 20\n"
                + "  - verbose: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyYaml, toEmptyYaml);
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
