
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.stream.*;

class DataSource {
    void go() throws Exception {
        Path file = Paths.get("data1000.csv"); 
        try (Stream<String> lines = Files.lines(file, Charset.defaultCharset())) {
            lines.forEachOrdered(System.out::println);
        }
    }

    Stream<String> getStream() throws Exception {
        Path file = Paths.get("data1000.csv"); 
        Stream<String> lines = Files.lines(file, Charset.defaultCharset()); 
        return lines;
    }
}

class Worker {
    void process(String s) {
        System.out.println(s);
    }
}

public class Quick {
    void go() throws Exception {
        new DataSource().go();
    }

    void doWork() throws Exception {
        DataSource dataSource = new DataSource();
        Stream<String> lines = dataSource.getStream();
        Worker worker = new Worker();

        lines.forEach( line -> worker.process(line) );

        lines.close();
    }

    public static void main(String... args) throws Exception {
        Quick quick = new Quick();
        quick.doWork();
    }
}
