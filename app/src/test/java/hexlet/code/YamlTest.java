package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YamlTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
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
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void cleanTest2() throws Exception {
        String actual = Differ.generate(toYaml2, toYaml1);
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: false\n"
                + "  + chars2: [d, e, f]\n"
                + "  - checked: true\n"
                + "  + checked: false\n"
                + "  - default: [value1, value2]\n"
                + "  + default: null\n"
                + "  - id: null\n"
                + "  + id: 45\n"
                + "  + key1: value1\n"
                + "  - key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [22, 33, 44, 55]\n"
                + "  + numbers2: [2, 3, 4, 5]\n"
                + "  + numbers3: [3, 4, 5]\n"
                + "  - numbers4: [4, 5, 6]\n"
                + "  - obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Another value\n"
                + "  + setting1: Some value\n"
                + "  - setting2: 300\n"
                + "  + setting2: 200\n"
                + "  - setting3: none\n"
                + "  + setting3: true\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyYaml, toYaml2);
        String expected = "{\n"
                + "  + chars1: [a, b, c]\n"
                + "  + chars2: false\n"
                + "  + checked: true\n"
                + "  + default: [value1, value2]\n"
                + "  + id: null\n"
                + "  + key2: value2\n"
                + "  + numbers1: [1, 2, 3, 4]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  + setting1: Another value\n"
                + "  + setting2: 300\n"
                + "  + setting3: none\n"
                + "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest2() throws Exception {
        String actual = Differ.generate(toYaml1, toEmptyYaml);
        String expected = "{\n"
                + "  - chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  - checked: false\n"
                + "  - default: null\n"
                + "  - id: 45\n"
                + "  - key1: value1\n"
                + "  - numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  - setting1: Some value\n"
                + "  - setting2: 200\n"
                + "  - setting3: true\n"
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
