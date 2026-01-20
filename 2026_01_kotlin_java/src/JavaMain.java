public class JavaMain {
    public static void main(String[] args) {
        int type = 1;
        if (args.length > 0) {
            type = Integer.parseInt(args[0]);
        }

        Animal animal = new Animal();
        String sound = animal.makeSound(type);

        System.out.println("Java calling Kotlin: Animal type " + type + " says '" + sound + "'");
    }
}
