package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlainTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    String toJson1;
    String toJson2;
    String toEmptyJson;
    @BeforeEach
    public void beforeEach() {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        toJson1 = Paths.get(resourceDirectory, "json1.json").toString();
        toJson2 = Paths.get(resourceDirectory, "json2.json").toString();
        toEmptyJson = Paths.get(resourceDirectory, "json-empty.json").toString();
    }

    @Test
    public void cleanTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "plain");
        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        assertEquals(expected, actual);
    }

    @Test
    public void cleanTest2() throws Exception {
        String actual = Differ.generate(toJson2, toJson1, "plain");
        String expected = "Property 'chars2' was updated. From false to [complex value]\n"
            + "Property 'checked' was updated. From true to false\n"
            + "Property 'default' was updated. From [complex value] to null\n"
            + "Property 'id' was updated. From null to 45\n"
            + "Property 'key1' was added with value: 'value1'\n"
            + "Property 'key2' was removed\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was added with value: [complex value]\n"
            + "Property 'numbers4' was removed\n"
            + "Property 'obj1' was removed\n"
            + "Property 'setting1' was updated. From 'Another value' to 'Some value'\n"
            + "Property 'setting2' was updated. From 300 to 200\n"
            + "Property 'setting3' was updated. From 'none' to true";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyJson, toJson2, "plain");
        String expected = "Property 'chars1' was added with value: [complex value]\n"
                + "Property 'chars2' was added with value: false\n"
                + "Property 'checked' was added with value: true\n"
                + "Property 'default' was added with value: [complex value]\n"
                + "Property 'id' was added with value: null\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers1' was added with value: [complex value]\n"
                + "Property 'numbers2' was added with value: [complex value]\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was added with value: 'Another value'\n"
                + "Property 'setting2' was added with value: 300\n"
                + "Property 'setting3' was added with value: 'none'";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest2() throws Exception {
        String actual = Differ.generate(toJson1, toEmptyJson, "plain");
        String expected = "Property 'chars1' was removed\n"
                + "Property 'chars2' was removed\n"
                + "Property 'checked' was removed\n"
                + "Property 'default' was removed\n"
                + "Property 'id' was removed\n"
                + "Property 'key1' was removed\n"
                + "Property 'numbers1' was removed\n"
                + "Property 'numbers2' was removed\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'setting1' was removed\n"
                + "Property 'setting2' was removed\n"
                + "Property 'setting3' was removed";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyJson, toEmptyJson, "plain");
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
