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
}
