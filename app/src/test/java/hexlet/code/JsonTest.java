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
        toJson1 = Paths.get(resourceDirectory, "json-nested1.json").toAbsolutePath();
        toJson2 = Paths.get(resourceDirectory, "json-nested2.json").toAbsolutePath();
        toEmptyJson = Paths.get(resourceDirectory, "json-empty.json").toAbsolutePath();
    }

    @Test
    public void cleanTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2);
        String expected = "{\n" +
                "    chars1: [a, b, c]\n" +
                "  - chars2: [d, e, f]\n" +
                "  + chars2: false\n" +
                "  - checked: false\n" +
                "  + checked: true\n" +
                "  - default: null\n" +
                "  + default: [value1, value2]\n" +
                "  - id: 45\n" +
                "  + id: null\n" +
                "  - key1: value1\n" +
                "  + key2: value2\n" +
                "    numbers1: [1, 2, 3, 4]\n" +
                "  - numbers2: [2, 3, 4, 5]\n" +
                "  + numbers2: [22, 33, 44, 55]\n" +
                "  - numbers3: [3, 4, 5]\n" +
                "  + numbers4: [4, 5, 6]\n" +
                "  + obj1: {nestedKey=value, isNested=true}\n" +
                "  - setting1: Some value\n" +
                "  + setting1: Another value\n" +
                "  - setting2: 200\n" +
                "  + setting2: 300\n" +
                "  - setting3: true\n" +
                "  + setting3: none\n" +
                "}";
        assertEquals(expected, actual);
    }

//    @Test
//    public void cleanTest2() throws Exception {
//        String actual = Differ.generate(toJson2, toJson1);
//        String expected = "{\n"
//                + "  + follow: false\n"
//                + "    host: hexlet.io\n"
//                + "  + proxy: 123.234.53.22\n"
//                + "  - timeout: 20\n"
//                + "  + timeout: 50\n"
//                + "  - verbose: true\n"
//                + "}";
//        assertEquals(expected, actual);
 //   }

    @Test
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyJson, toJson2);
        String expected = "{\n" +
                "    + chars1: [a, b, c]\n" +
                "    + chars2: false\n" +
                "    + checked: true\n" +
                "    + default: [value1, value2]\n" +
                "    + id: null\n" +
                "    + key2: value2\n" +
                "    + numbers1: [1, 2, 3, 4]\n" +
                "    + numbers2: [22, 33, 44, 55]\n" +
                "    + numbers4: [4, 5, 6]\n" +
                "    + obj1: {nestedKey=value, isNested=true}\n" +
                "    + setting1: Another value\n" +
                "    + setting2: 300\n" +
                "    + setting3: none\n" +
                "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest2() throws Exception {
        String actual = Differ.generate(toJson1, toEmptyJson);
        String expected = "{\n" +
                "    - chars1: [a, b, c]\n" +
                "    - chars2: [d, e, f]\n" +
                "    - checked: false\n" +
                "    - default: null\n" +
                "    - id: 45\n" +
                "    - key1: value1\n" +
                "    - numbers1: [1, 2, 3, 4]\n" +
                "    - numbers2: [2, 3, 4, 5]\n" +
                "    - numbers3: [3, 4, 5]\n" +
                "    - setting1: Some value\n" +
                "    - setting2: 200\n" +
                "    - setting3: true\n" +
                "}";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyJson, toEmptyJson);
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
