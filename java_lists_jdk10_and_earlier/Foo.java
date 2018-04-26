
import java.util.*;

public class Foo {
    public static void main(String[] args) {
        List jdk_14 = new ArrayList();

        List<Foo> jdk_5 = new ArrayList<Foo>();

        List<Foo> jdk_7 = new ArrayList<>();

        var jdk_10 = new ArrayList<Foo>(); 

        System.out.println("Ready.");
    }
}
