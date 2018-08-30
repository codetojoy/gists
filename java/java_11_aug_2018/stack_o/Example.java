import java.nio.file.*;
import java.util.stream.Stream;

// simply accepts a filename as a command-line argument
// and prints out the lines 

public class ExampleJDK11 {
    public static void main(String[] args) throws Exception {
        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
            stream.forEach(System.out::println);
        }
    }
}
