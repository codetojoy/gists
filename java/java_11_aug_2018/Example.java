
import java.io.*;
import java.util.*; 

// simply accepts a filename as a command-line argument
// and prints out the lines in reverse order

public class Example {
    private List<String> lines = new ArrayList<String>();

    // this is a "high-ceremony" way to read a simple text file
    public void read(String filename) throws IOException {
        File file = new File(filename);
    
        BufferedReader input =  new BufferedReader(new FileReader(file));
        
        try {         
            String line = null;

            while (( line = input.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            // clean-up!
            input.close();
        }
    }

    private void display() {
        int max = lines.size();
        
        for (int i = max - 1; i >= 0; i--) {
            System.out.println(lines.get(i));
        }
    }
    
    public static void main(String... args) throws IOException {
        // TODO: guard against case with no arguments
        String filename = args[0];
        Example example = new Example();
        example.read(filename);
        example.display();
    }
}














