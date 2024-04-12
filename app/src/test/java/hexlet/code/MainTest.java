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
    private String toJson1;
    private String toJson2;
    private String toYaml1;
    private String toYaml2;
    private String stylishResult;
    private String plainResult;
    private String jsonResult;
    private String defaultResult;


    @BeforeEach
    public void beforeEach() throws Exception {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        toJson1 = Paths.get(resourceDirectory, "json1.json").toString();
        toJson2 = Paths.get(resourceDirectory, "json2.json").toString();
        toYaml1 = Paths.get(resourceDirectory, "yaml1.yml").toString();
        toYaml2 = Paths.get(resourceDirectory, "yaml2.yml").toString();
        stylishResult = Files.readString(Paths.get(resourceDirectory, "result-stylish.txt"));
        plainResult = Files.readString(Paths.get(resourceDirectory, "result-plain.txt"));
        jsonResult = Files.readString(Paths.get(resourceDirectory, "result-json.txt"));
        defaultResult = stylishResult;
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void stylishTest(String extension) throws Exception {
        String actual;
        if (extension.equals("json")) {
            actual = Differ.generate(toJson1, toJson2, "stylish");
        } else {
            actual = Differ.generate(toYaml1, toYaml2, "stylish");
        }
        String expected = stylishResult;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void plainTest(String extension) throws Exception {
        String actual;
        if (extension.equals("json")) {
            actual = Differ.generate(toJson1, toJson2, "plain");
        } else {
            actual = Differ.generate(toYaml1, toYaml2, "plain");
        }
        String expected = plainResult;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void jsonTest(String extension) throws Exception {
        String actual;
        if (extension.equals("json")) {
            actual = Differ.generate(toJson1, toJson2, "json");
        } else {
            actual = Differ.generate(toYaml1, toYaml2, "json");
        }
        String expected = jsonResult;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void defaultTest2(String extension) throws Exception {
        String actual;
        if (extension.equals("json")) {
            actual = Differ.generate(toJson1, toJson2);
        } else {
            actual = Differ.generate(toYaml1, toYaml2);
        }
        String expected = defaultResult;
        assertEquals(expected, actual);
    }
}
