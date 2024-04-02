package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class JsonOutputTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private String toJson1;
    private String toJson2;
    private String toEmptyJson;

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
        String pathForCleanTest = Paths.get(resourceDirectory, "result-json-clean.txt").toString();
        String expected = Files.readString(Paths.get(pathForCleanTest));
        assertEquals(expected, actual);
    }

    @Test
    public void cleanHumanTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "hjs");
        String pathForHumanTest = Paths.get(resourceDirectory, "result-json-human.txt").toString();
        String expected = Files.readString(Paths.get(pathForHumanTest));
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest1() throws Exception {
        String actual = Differ.generate(toEmptyJson, toJson2, "json");
        String pathForEmptyTest = Paths.get(resourceDirectory, "result-json-empty.txt").toString();
        String expected = Files.readString(Paths.get(pathForEmptyTest));
        assertEquals(expected, actual);
    }

    @Test
    public void emptyTest3() throws Exception {
        String actual = Differ.generate(toEmptyJson, toEmptyJson, "json");
        String expected = "{\n}";
        assertEquals(expected, actual);
    }
}
