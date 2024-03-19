package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    String format = "stylish";

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    String filepath1 = "path to file 1";

    @Parameters(index = "0", description = "path to second file", paramLabel = "filepath2")
    String filepath2 = "path to file 2";

    @Override
    public void run() {
        System.out.println("STDOUT");
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
