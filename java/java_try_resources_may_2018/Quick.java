
import java.io.*;

public class Quick {
    private static int BUFFER_SIZE_IN_BYTES = 1024 * 16;

    public void copy(String src, String dest) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dest) ) {
            byte[] buffer = new byte[BUFFER_SIZE_IN_BYTES];
            int num = in.read(buffer);
            while (num >= 0) {
                out.write(buffer, 0, num);
                num = in.read(buffer);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String input = "input.txt";
        String output = "output.txt";
        new Quick().copy(input, output);
    }
}
