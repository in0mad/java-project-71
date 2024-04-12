package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MainTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private final String fileTemplate1 = Paths.get("src", "test", "resources", "input1").toString();
    private final String fileTemplate2 = Paths.get("src", "test", "resources", "input2").toString();
    private String stylishResult;
    private String plainResult;
    private String jsonResult;
    private String defaultResult;



    @BeforeEach
    public void beforeEach() throws Exception {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        stylishResult = Files.readString(Paths.get(resourceDirectory, "result-stylish.txt"));
        plainResult = Files.readString(Paths.get(resourceDirectory, "result-plain.txt"));
        jsonResult = Files.readString(Paths.get(resourceDirectory, "result-json.txt"));
        defaultResult = stylishResult;
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void stylishTest(String extension) throws Exception {
        String actual = Differ.generate(fileTemplate1 + extension,
                fileTemplate2 + extension, "stylish");
        String expected = stylishResult;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void plainTest(String extension) throws Exception {
        String actual = Differ.generate(fileTemplate1 + extension,
                fileTemplate2 + extension, "plain");
        String expected = plainResult;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void jsonTest(String extension) throws Exception {
        String actual = Differ.generate(fileTemplate1 + extension,
                fileTemplate2 + extension, "json");
        String expected = jsonResult;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void defaultTest2(String extension) throws Exception {
        String actual = Differ.generate(fileTemplate1 + extension,
                fileTemplate2 + extension);
        String expected = defaultResult;
        assertEquals(expected, actual);
    }
}
