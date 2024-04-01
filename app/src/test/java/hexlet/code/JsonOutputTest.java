package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonOutputTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
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
        String actual = Differ.generate(toJson1, toJson2, "json");
        String expected = """
                "{ \\"chars2\\" : \\"updated\\", \\"checked\\" : \\"updated\\", \\"default\\" : \\"updated\\", \\"id\\" : \\"updated\\", \\"key1\\" : \\"removed\\", \\"key2\\" : \\"added\\", \\"numbers2\\" : \\"updated\\", \\"numbers3\\" : \\"removed\\", \\"numbers4\\" : \\"added\\", \\"obj1\\" : \\"added\\", \\"setting1\\" : \\"updated\\", \\"setting2\\" : \\"updated\\", \\"setting3\\" : \\"updated\\" }\"""";
        assertEquals(expected, actual);
    }

    @Test
    public void cleanHumanTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "hjs");
        String expected = """
                {\"chars2\":\"updated\",\"checked\":\"updated\",\"default\":\"updated\",\"id\":\"updated\",\"key1\":\"removed\",\"key2\":\"added\",\"numbers2\":\"updated\",\"numbers3\":\"removed\",\"numbers4\":\"added\",\"obj1\":\"added\",\"setting1\":\"updated\",\"setting2\":\"updated\",\"setting3\":\"updated\"}""";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyJson, toJson2, "json");
        String expected = """
                {
                  "chars1": "added",
                  "chars2": "added",
                  "checked": "added",
                  "default": "added",
                  "id": "added",
                  "key2": "added",
                  "numbers1": "added",
                  "numbers2": "added",
                  "numbers4": "added",
                  "obj1": "added",
                  "setting1": "added",
                  "setting2": "added",
                  "setting3": "added"
                }""";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyJson, toEmptyJson, "json");
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
