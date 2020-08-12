
public class Example {
    void func1() {
        String s = """
the quick
brown fox
jumped
over 
the lazy dog.
""";
        System.out.println("TRACER: " + s);
    }

    void func2(Object obj) {
        if (obj instanceof String s && (! s.isEmpty())) {
            String msg = String.format("TRACER obj is string: '%s'", s);
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        var example = new Example();
        example.func1();    
        example.func2("hello");    
        System.out.println("TRACER: ready.");
    }
}
