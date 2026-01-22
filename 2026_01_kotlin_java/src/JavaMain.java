import kotlin.sequences.Sequence;
import java.util.Iterator;

public class JavaMain {
    public static void main(String[] args) {
        int type = 1;
        if (args.length > 0) {
            type = Integer.parseInt(args[0]);
        }

        Animal animal = new Animal();
        String sound = animal.makeSound(type);

        System.out.println("Java calling Kotlin: Animal type " + type + " says '" + sound + "'");

        System.out.println("\nLocations from Kotlin Sequence:");
        Sequence<String> locations = animal.getLocations();
        Iterator<String> iterator = locations.iterator();
        while (iterator.hasNext()) {
            System.out.println("  - " + iterator.next());
        }
    }
}
