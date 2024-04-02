package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonOutputTest {
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
        String actual = Differ.generate(toJson1, toJson2, "json");
        String expected = "result-json-clean";
        assertEquals(expected, actual);
    }

    @Test
    public void cleanHumanTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "hjs");
        String expected = "result-json-human";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyJson, toJson2, "json");
        String expected = "result-json.empty";
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyJson, toEmptyJson, "json");
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
