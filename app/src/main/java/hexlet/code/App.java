package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filepath2;

    @Override
    public Integer call() throws Exception{
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath1)) {
            throw new Exception(String.format("%s: No such file", filepath1));
        }
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath2)) {
            throw new Exception(String.format("%s: No such file", filepath2));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> dataFile1 = objectMapper.
                readValue(absolutePath1.toUri().toURL(), new TypeReference<>() {});
        Map<String, Object> dataFile2 = objectMapper.
                readValue(absolutePath2.toUri().toURL(), new TypeReference<>() {});
        System.out.println(Differ.generate(dataFile1, dataFile2));
        return 0;
    }

    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
