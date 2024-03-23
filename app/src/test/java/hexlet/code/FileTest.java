//package hexlet.code;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Map;
//import java.util.TreeMap;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class FileTest {
//    Map<String, Object> testFile1;
//    Map<String, Object> testFile2;
//    @BeforeEach
//    public void beforeEach() {
//        testFile1 = new TreeMap<>();
//        testFile2 = new TreeMap<>();
//    }
//
//    @Test
//    public void cleanTest() {
//        String actual = Differ.generate(testFile1, testFile2);
//        String expected = "{\n"
//                + "  - follow: false\n"
//                + "    host: hexlet.io\n"
//                + "  - proxy: 123.234.53.22\n"
//                + "  - timeout: 50\n"
//                + "  + timeout: 20\n"
//                + "  + verbose: true\n"
//                + "}";
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void cleanTest2() {
//        String actual = Differ.generate(testFile1, testFile2);
//        String expected = "{\n"
//                + "  + follow: false\n"
//                + "    host: hexlet.io\n"
//                + "  + proxy: 123.234.53.22\n"
//                + "  - timeout: 20\n"
//                + "  + timeout: 50\n"
//                + "  - verbose: true\n"
//                + "}";
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void emptyTest1() {
//
//        String actual = Differ.generate(testFile1, testFile2);
//        String expected = "{\n"
//                + "  + host: hexlet.io\n"
//                + "  + timeout: 20\n"
//                + "  + verbose: true\n"
//                + "}";
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void emptyTest2() {
//        String actual = Differ.generate(testFile1, testFile2);
//        String expected = "{\n"
//                + "  - host: hexlet.io\n"
//                + "  - timeout: 20\n"
//                + "  - verbose: true\n"
//                + "}";
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void emptyTest3() {
//        String actual = Differ.generate(testFile1, testFile2);
//        String expected = "{\n}";
//        assertEquals(expected, actual);
//    }
//}
