import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Vehicle {
    public String getFuel(int type) {
        return switch (type) {
            case 1 -> "Electric";
            case 2 -> "Gasoline";
            case 3 -> "Diesel";
            case 4 -> "Hydrogen";
            default -> "Unknown fuel";
        };
    }

    public ArrayList<String> getLocations() throws IOException {
        return new ArrayList<>(Files.readAllLines(Paths.get("src/locations.txt")));
    }
}
