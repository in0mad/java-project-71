package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class JsonTest {
    private final String resourceDirectory = Paths.get("src", "test", "resources").toString();
    private String toJson1;
    private String toJson2;

    @BeforeEach
    public void beforeEach() {
        assertTrue(resourceDirectory.endsWith("src/test/resources"));
        toJson1 = Paths.get(resourceDirectory, "json1.json").toString();
        toJson2 = Paths.get(resourceDirectory, "json2.json").toString();
    }

    @Test
    public void stylishTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "stylish");
        String pathStylishTest = Paths.get(resourceDirectory, "result-stylish.txt").toString();
        String expected = Files.readString(Paths.get(pathStylishTest));
        assertEquals(expected, actual);
    }
    @Test
    public void plainTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "plain");
        String pathPlainTest = Paths.get(resourceDirectory, "result-plain.txt").toString();
        String expected = Files.readString(Paths.get(pathPlainTest));
        assertEquals(expected, actual);
    }
    @Test
    public void jsonTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "json");
        String pathJsonTest = Paths.get(resourceDirectory, "result-json.txt").toString();
        String expected = Files.readString(Paths.get(pathJsonTest));
        assertEquals(expected, actual);
    }

    @Test
    public void defaultTest() throws Exception {
        String actual = Differ.generate(toJson1, toJson2, "default");
        String pathDefaultTest = Paths.get(resourceDirectory, "result-stylish.txt").toString();
        String expected = Files.readString(Paths.get(pathDefaultTest));
        assertEquals(expected, actual);
    }

    @Test
    public void defaultTest2() throws Exception {
        String actual = Differ.generate(toJson1, toJson2);
        String pathDefaultTest = Paths.get(resourceDirectory, "result-stylish.txt").toString();
        String expected = Files.readString(Paths.get(pathDefaultTest));
        assertEquals(expected, actual);
    }

}
