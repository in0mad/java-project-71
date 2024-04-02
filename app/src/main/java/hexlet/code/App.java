package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private static String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private static String filepath2;

    @Override
    public Integer call() throws Exception {
        Path absolutePath1 = Paths.get(filepath1).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath1)) {
            throw new Exception(String.format("%s: No such file", filepath1));
        }
        Path absolutePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath2)) {
            throw new Exception(String.format("%s: No such file", filepath2));
        }
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }

    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
