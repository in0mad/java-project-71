package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class JsonTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private String toJson1;
    private String toJson2;
    private String stylishResult;
    private String plainResult;
    private String jsonResult;
    private String defaultResult;
    private String extention;


    @BeforeEach
    public void beforeEach() throws Exception {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        toJson1 = Paths.get(resourceDirectory, "json1.json").toString();
        toJson2 = Paths.get(resourceDirectory, "json2.json").toString();
        stylishResult = Files.readString(Paths.get(resourceDirectory, "result-stylish.txt"));
        plainResult = Files.readString(Paths.get(resourceDirectory, "result-plain.txt"));
        jsonResult = Files.readString(Paths.get(resourceDirectory, "result-json.txt"));
        defaultResult = Files.readString(Paths.get(resourceDirectory, "result-default.txt"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void stylishTest(String extention) throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "stylish");
        String expected = stylishResult;
        assertEquals(expected, actual);
    }
    @Test
    public void plainTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "plain");
        String expected = plainResult;
        assertEquals(expected, actual);
    }
    @Test
    public void jsonTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "json");
        String expected = jsonResult;
        assertEquals(expected, actual);
    }

    @Test
    public void defaultTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "default");
        String expected = defaultResult;
        assertEquals(expected, actual);
    }

    @Test
    public void defaultTest2() throws Exception {
        String actual = Differ.generate(toJson1, toJson2);
        String expected = defaultResult;
        assertEquals(expected, actual);
    }
}
