
package net.codetojoy;

public class FooDAO {

    public static Foo getFoo(int id) {
        Foo foo = new Foo();
        foo.id = id;
        return foo;
    }

    public static Bar getBar(int id) {
        Bar bar = new Bar();
        bar.id = id;
        bar.timestamp = new java.util.Date().toString();
        return bar;
    }
}
